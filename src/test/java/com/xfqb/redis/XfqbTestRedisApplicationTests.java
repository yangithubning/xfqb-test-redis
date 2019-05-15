package com.xfqb.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.xfqb.redis.task.SysJobPO;
import com.xfqb.redis.task.mapper.SysJobMapper;

@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@SpringBootTest
public class XfqbTestRedisApplicationTests {

	@Autowired
	private StringRedisTemplate redis;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private SysJobMapper sysJobMapper;

	@Test
	public void contextLoads() {
		SysJobPO sysJobPO = new SysJobPO();
		sysJobPO.setJobId(2);
		sysJobPO.setBeanName("demoTask");
		sysJobPO.setMethodName("taskNoParams");
		sysJobPO.setMethodParams(null);
		sysJobPO.setJobStatus(1);
		sysJobPO.setRemark("执行无参示例任务");
		sysJobPO.setCronExpression("0/5 * * * * ?");
		String jsonString = JSON.toJSONString(sysJobPO);
		System.out.println(jsonString);
	}

	/**
	 * 打印信息
	 * 
	 * @param object
	 */
	public void printInfo(Object object) {
		System.err.println("result ==>" + object);
	}
}
