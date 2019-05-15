package com.xfqb.redis.task.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xfqb.redis.task.SysJobPO;

/**
 * @Description 描述
 * 
 * @author Mloong
 * 
 * @Date 2019年5月15日 上午10:33:21
 * 
 */
@Mapper
public interface SysJobMapper {

	/**
	 * 初始加载数据库里状态为正常的定时任务
	 * 
	 * @param i
	 * @return
	 */
	List<SysJobPO> getSysJobListByStatus(@Param("status") Integer i);

	/**
	 * 新建
	 * 
	 * @param sysJobPO
	 * @return
	 */
	Integer addSysJob(SysJobPO sysJobPO);

	/**
	 * 修改
	 * 
	 * @param sysJob
	 * @return
	 */
	Integer editSysJob(SysJobPO sysJob);

	/**
	 * 删除
	 * 
	 * @param jobId
	 * @return
	 */
	Integer deleteSysJobById(@Param("jobId") Integer jobId);

	/**
	 * 根据主键查询
	 * 
	 * @param jobId
	 * @return
	 */
	SysJobPO getSysJobById(@Param("jobId") Integer jobId);

}
