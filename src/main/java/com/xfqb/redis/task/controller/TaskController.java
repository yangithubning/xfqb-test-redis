package com.xfqb.redis.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xfqb.redis.task.CronTaskRegistrar;
import com.xfqb.redis.task.SchedulingRunnable;
import com.xfqb.redis.task.SysJobPO;
import com.xfqb.redis.task.mapper.SysJobMapper;

/**
 * @Description 描述
 * 
 * @author yan ning
 * 
 * @Date 2019年5月15日 上午11:26:30
 * 
 */
@RestController
public class TaskController {

	@Autowired
	private CronTaskRegistrar cronTaskRegistrar;

	@Autowired
	private SysJobMapper sysJobMapper;

	/**
	 * 新增定时任务
	 * 
	 * @param sysJobPO
	 * @return
	 */
	@RequestMapping(value = "/addTask", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean addTask(@RequestBody SysJobPO sysJobPO) {
		System.err.println(sysJobPO);
		// 插入数据库
		Integer success = sysJobMapper.addSysJob(sysJobPO);
		if (null == success || 0 == success) {
			return false;
		} else {
			if (sysJobPO.getJobStatus().equals(1)) {
				SchedulingRunnable task = new SchedulingRunnable(sysJobPO.getBeanName(), sysJobPO.getMethodName(),
						sysJobPO.getMethodParams());
				cronTaskRegistrar.addCronTask(task, sysJobPO.getCronExpression());
			}
		}
		return true;
	}

	/**
	 * 修改定时任务，先移除原来的任务，再启动新任务
	 * 
	 * @param sysJobPO
	 * @return
	 */
	@RequestMapping(value = "/updateTask", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean updateTask(@RequestBody SysJobPO sysJob) {
		System.err.println(sysJob);
		Integer success = sysJobMapper.editSysJob(sysJob);
		System.err.println(success);
		if (null == success || 0 == success)
			return false;
		else {
			// 根据主键查询
			SysJobPO sysJobPO = sysJobMapper.getSysJobById(sysJob.getJobId());
			// 先移除再添加
			if (sysJobPO.getJobStatus().equals(1)) {
				SchedulingRunnable task = new SchedulingRunnable(sysJobPO.getBeanName(), sysJobPO.getMethodName(),
						sysJobPO.getMethodParams());
				cronTaskRegistrar.removeCronTask(task);
			}
			if (sysJobPO.getJobStatus().equals(1)) {
				SchedulingRunnable task = new SchedulingRunnable(sysJobPO.getBeanName(), sysJobPO.getMethodName(),
						sysJobPO.getMethodParams());
				cronTaskRegistrar.addCronTask(task, sysJobPO.getCronExpression());
			}
		}
		return true;
	}

	/**
	 * 删除定时任务
	 * 
	 * @param sysJobPO
	 * @return
	 */
	@RequestMapping(value = "/deleteTask", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean deleteTask(@RequestBody SysJobPO sysJobPO) {
		System.err.println(sysJobPO);
		SysJobPO sysJobById = sysJobMapper.getSysJobById(sysJobPO.getJobId());
		Integer result = sysJobMapper.deleteSysJobById(sysJobPO.getJobId());
		if (null == result || 0 == result)
			return false;
		else {
			if (sysJobById.getJobStatus().equals(1)) {
				SchedulingRunnable task = new SchedulingRunnable(sysJobById.getBeanName(), sysJobById.getMethodName(),
						sysJobById.getMethodParams());
				cronTaskRegistrar.removeCronTask(task);
			}
		}
		return true;
	}

	/**
	 * 定时任务启动/停止状态切换(状态毕传)
	 * 
	 * @param sysJobPO
	 * @return
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String updateStatus(@RequestBody SysJobPO existedSysJob) {
		System.err.println(existedSysJob);
		Integer editSysJob = sysJobMapper.editSysJob(existedSysJob);
		if (null == editSysJob || 0 == editSysJob) {
			return "修改失败";
		}
		SysJobPO sysJobById = sysJobMapper.getSysJobById(existedSysJob.getJobId());
		if (sysJobById.getJobStatus().equals(1)) {
			SchedulingRunnable task = new SchedulingRunnable(sysJobById.getBeanName(), sysJobById.getMethodName(),
					sysJobById.getMethodParams());
			cronTaskRegistrar.addCronTask(task, sysJobById.getCronExpression());
			return "添加成功";
		} else {
			SchedulingRunnable task = new SchedulingRunnable(sysJobById.getBeanName(), sysJobById.getMethodName(),
					sysJobById.getMethodParams());
			cronTaskRegistrar.removeCronTask(task);
			return "删除成功";
		}
	}

}
