package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXUserGroupService {
	
	/**
	 * 分页查询微信用户组	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信用户组
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信用户组
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信用户组
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信用户组
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信用户组(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除所有用户分组数据
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteAllWXUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信用户组
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
}
