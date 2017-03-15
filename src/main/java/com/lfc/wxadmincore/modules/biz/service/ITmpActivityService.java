package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface ITmpActivityService {
	
	/**
	 * 分页查询预约中奖信息设置	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询预约中奖信息设置
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有预约中奖信息设置
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的预约中奖信息设置
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertTmpActivity(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新预约中奖信息设置
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateTmpActivity(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除预约中奖信息设置(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteTmpActivity(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除预约中奖信息设置
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteTmpActivity(InputObject inputObject, OutputObject outputObject) throws Exception;
}
