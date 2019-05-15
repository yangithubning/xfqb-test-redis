package com.xfqb.redis.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Description 描述
 * 
 * @author Mloong
 * 
 * @Date 2019年5月15日 上午10:18:11
 * 
 */
@Configuration
public class SchedulingConfig {
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		// 定时任务执行线程池核心线程数
		taskScheduler.setPoolSize(4);
		taskScheduler.setRemoveOnCancelPolicy(true);
		taskScheduler.setThreadNamePrefix("TaskSchedulerThreadPool-");
		return taskScheduler;
	}
}
