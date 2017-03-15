package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface ICustomAccountService {
	
	/**
	 * 分页查询微信客服管理	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信客服管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信客服管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信客服管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertCustomAccount(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信客服管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateCustomAccount(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信客服管理(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteCustomAccount(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信客服管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteCustomAccount(InputObject inputObject, OutputObject outputObject) throws Exception;
}
