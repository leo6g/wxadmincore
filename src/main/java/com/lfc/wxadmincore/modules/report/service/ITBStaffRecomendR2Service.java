package com.lfc.wxadmincore.modules.report.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface ITBStaffRecomendR2Service {
	
	/**
	 * 分页查询劳动竞赛地区排名	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 查询劳动竞赛地区排名	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getListAll(InputObject inputObject, OutputObject outputObject) throws Exception;
}
