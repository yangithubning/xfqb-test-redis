package com.xfqb.redis.util;

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
 * @Date 2019年5月15日 上午9:45:48
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Date createTime;

	private Date updateTime;

	private Integer age;

}
