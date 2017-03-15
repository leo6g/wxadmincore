package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXSubcribeMsgService {
	
	/**
	 * 分页查询微信关注语	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信关注语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信关注语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信关注语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXSubcribeMsg(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信关注语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXSubcribeMsg(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信关注语(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXSubcribeMsg(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信关注语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXSubcribeMsg(InputObject inputObject, OutputObject outputObject) throws Exception;
}
