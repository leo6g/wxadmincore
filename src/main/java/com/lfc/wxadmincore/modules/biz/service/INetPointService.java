package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface INetPointService {
	
	/**
	 * 分页查询网点信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询网点信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有网点信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的网点信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertNetPoint(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新网点信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateNetPoint(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除网点信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteNetPoint(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除网点信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteNetPoint(InputObject inputObject, OutputObject outputObject) throws Exception;
}
