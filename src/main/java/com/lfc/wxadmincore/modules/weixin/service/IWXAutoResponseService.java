package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXAutoResponseService {
	
	/**
	 * 分页查询微信自动回复信息模版	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信自动回复信息模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信自动回复信息模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信自动回复信息模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXAutoResponse(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信自动回复信息模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXAutoResponse(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信自动回复信息模版(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXAutoResponse(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信自动回复信息模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXAutoResponse(InputObject inputObject, OutputObject outputObject) throws Exception;
}
