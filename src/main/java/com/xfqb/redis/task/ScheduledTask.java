package com.xfqb.redis.task;

import java.util.concurrent.ScheduledFuture;

/**
 * @Description 描述
 * 
 * @author Mloong
 * 
 * @Date 2019年5月15日 上午10:19:12
 * 
 */
public final class ScheduledTask {

	volatile ScheduledFuture<?> future;

	/**
	 * 取消定时任务
	 */
	public void cancel() {
		ScheduledFuture<?> future = this.future;
		if (future != null) {
			future.cancel(true);
		}
	}
}
