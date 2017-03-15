package com.lfc.wxadmincore.modules.biz.award.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IAwardActivityService {
	
	/**
	 * 分页查询微信抽奖活动	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信抽奖活动
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信抽奖活动
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信抽奖活动
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertAwardActivity(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信抽奖活动
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateAwardActivity(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信抽奖活动(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteAwardActivity(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信抽奖活动
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteAwardActivity(InputObject inputObject, OutputObject outputObject) throws Exception;
	
}
