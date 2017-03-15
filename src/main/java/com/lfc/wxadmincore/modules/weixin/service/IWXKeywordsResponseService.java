package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXKeywordsResponseService {
	/**
	 * 分页查询规则详情
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据规则ID查询
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有规则详情
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;

	/**
	 * 更新规则
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXKeywordsResp(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除规则
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteRule(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入规则信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXKeywordsResp(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据所发送信息模糊查询
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getByNews(InputObject inputObject, OutputObject outputObject) throws Exception;
	
}
