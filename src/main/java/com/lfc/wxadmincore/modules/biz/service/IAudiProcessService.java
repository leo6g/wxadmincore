package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IAudiProcessService {
	
	/**
	 * 分页查询图文审批流程信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询图文审批流程信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有图文审批流程信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的图文审批流程信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertAudiProcess(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新图文审批流程信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateAudiProcess(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除图文审批流程信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteAudiProcess(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除图文审批流程信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteAudiProcess(InputObject inputObject, OutputObject outputObject) throws Exception;
	public void getViewById(InputObject inputObject, OutputObject outputObject) throws Exception;
}
