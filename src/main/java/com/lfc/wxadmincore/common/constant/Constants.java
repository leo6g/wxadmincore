package com.lfc.wxadmincore.common.constant;

/**
 * 系统常量类
 */
public final class Constants {
	
	public interface SYSTEM_PROP_CONFIG {
		String REDIS_ADDR_CFG = "REDIS_ADDR_CFG";// Redis IP地址配置，各ip之间以半角逗号","分隔
		String REDIS_CFG_SPLIT = ",";// 分隔符
		String APP_ARRAY_UNION_URL = "APP_ARRAY_UNION_URL";// ecpcore层地址配置		
		String REDIS_MAX_TOTAL = "REDIS_MAX_TOTAL"; // 最大连接数key值，默认8个
		String REDIS_MAX_IDLE = "REDIS_MAX_IDLE"; // 最大空闲连接数key值，默认8个
		String REDIS_MIN_IDLE = "REDIS_MIN_IDLE"; // 最小空闲连接数key值，默认8个
	
	}
	
	/**
	 * 用户登录后返回状态
	 * @author Administrator
	 *
	 */
	public interface SYSTEM_LOGIN_STATE {
		String STATE_0 = "0"; // 登录成功
		String STATE_1 = "1"; // 验证码错误
		String STATE_N_1 = "-1"; //用户名或密码错误
		String STATE_2 = "2"; // 用户账号已经被冻结
	}
	
}
