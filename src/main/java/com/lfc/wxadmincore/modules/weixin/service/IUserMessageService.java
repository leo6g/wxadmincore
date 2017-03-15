package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IUserMessageService {
	
	/**
	 * 分页查询微信会员用户留言表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信会员用户留言表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信会员用户留言表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信会员用户留言表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertUserMessage(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信会员用户留言表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateUserMessage(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信会员用户留言表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteUserMessage(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信会员用户留言表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteUserMessage(InputObject inputObject, OutputObject outputObject) throws Exception;
}
