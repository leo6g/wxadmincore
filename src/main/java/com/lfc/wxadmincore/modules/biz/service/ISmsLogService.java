package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface ISmsLogService {
	
	/**
	 * 分页查询短信发送日志	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询短信发送日志
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有短信发送日志
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的短信发送日志
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertSmsLog(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新短信发送日志
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateSmsLog(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除短信发送日志(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteSmsLog(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除短信发送日志
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteSmsLog(InputObject inputObject, OutputObject outputObject) throws Exception;
}
