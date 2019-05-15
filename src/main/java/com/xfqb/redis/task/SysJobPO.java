package com.xfqb.redis.task;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 描述
 * 
 * @author Mloong
 * 
 * @Date 2019年5月15日 上午10:23:57
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysJobPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 任务ID
	 */
	private Integer jobId;
	/**
	 * bean名称
	 */
	private String beanName;
	/**
	 * 方法名称
	 */
	private String methodName;
	/**
	 * 方法参数
	 */
	private String methodParams;
	/**
	 * cron表达式
	 */
	private String cronExpression;
	/**
	 * 状态（1正常 0暂停）
	 */
	private Integer jobStatus;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
