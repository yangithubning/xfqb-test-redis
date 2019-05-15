package com.xfqb.redis.task;

import org.springframework.stereotype.Component;

/**
 * @Description 描述
 * 
 * @author Mloong
 * 
 * @Date 2019年5月15日 上午10:23:07
 * 
 */
@Component("demoTask")
public class DemoTask {

	/**
	 * 执行有参示例任务
	 * 
	 * @param params
	 */
	public void taskWithParams(String params) {
		System.out.println("执行有参示例任务：" + params);
	}

	/**
	 * 执行无参示例任务
	 */
	public void taskNoParams() {
		System.out.println("执行无参示例任务");
	}
}
