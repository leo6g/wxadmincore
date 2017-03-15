package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IUserRightsInfoService {
	
	/**
	 * 分页查询客户权益	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询客户权益
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有客户权益
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的客户权益
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertUserRightsInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新客户权益
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateUserRightsInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除客户权益(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteUserRightsInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除客户权益
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteUserRightsInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	public void batchInsertUserRightsInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	public void batchupdateUserRightsInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
}
