package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXConfigService {
	
	/**
	 * 分页查询微信账号配置信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信账号配置信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信账号配置信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信账号配置信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXConfig(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信账号配置信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXConfig(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信账号配置信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXConfig(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信账号配置信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXConfig(InputObject inputObject, OutputObject outputObject) throws Exception;
}
