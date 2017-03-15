package com.lfc.wxadmincore.modules.system.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface ICustomerLevelInfoService {
	
	/**
	 * 分页查询邮储个金部客户等级信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询邮储个金部客户等级信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有邮储个金部客户等级信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的邮储个金部客户等级信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertCustomerLevelInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新邮储个金部客户等级信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateCustomerLevelInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除邮储个金部客户等级信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteCustomerLevelInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除邮储个金部客户等级信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteCustomerLevelInfo(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 导入邮储个金部客户等级信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int importCustomerLevelInfo(InputObject inputObject, OutputObject outputObject) throws Exception;


}
