package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IBIZAuthInfoService {
	
	/**
	 * 分页查询审核意见信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询审核意见信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有审核意见信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的审核意见信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertBIZAuthInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新审核意见信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateBIZAuthInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除审核意见信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteBIZAuthInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除审核意见信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteBIZAuthInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
}
