package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXArticlesStatisticsService {
	
	
	/**
	 * 单篇图文列表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getArticlesStatistics(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 日 图文分析
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getArticlesListByDay(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 时 图文分析
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getArticlesListByHour(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 昨日关键指标
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getArticlesListByYesterday(InputObject inputObject, OutputObject outputObject) throws Exception;
}
