package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXUserPropertyService {
	
	
	
	
	/**
	 * 获取昨日关键指标（直接在动态sql语句中实现，代码中不做处理了）
	 * @param inputObject
	 * @param outputObject
	 */
	public void getKeyIndicatorsOfYesterday(InputObject inputObject, OutputObject outputObject);
	
	
	/**
	 * 关键指标详解
	 * @param inputObject
	 * @param outputObject
	 */
	public void getSubscriberInfosOfYesterday(InputObject inputObject, OutputObject outputObject);
	
	
	/**
	 * 用户属性
	 * @param inputObject
	 * @param outputObject
	 */
	public void getUserProperty(InputObject inputObject, OutputObject outputObject);
	
}
