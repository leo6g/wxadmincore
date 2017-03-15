package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IAccessTokenService {
	
	/**
	 * 分页查询微信获取token	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信获取token
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信获取token
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信获取token
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertAccessToken(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信获取token
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateAccessToken(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信获取token(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteAccessToken(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信获取token
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteAccessToken(InputObject inputObject, OutputObject outputObject) throws Exception;
}
