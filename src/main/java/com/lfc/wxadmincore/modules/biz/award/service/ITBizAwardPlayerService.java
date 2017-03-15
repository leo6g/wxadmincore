package com.lfc.wxadmincore.modules.biz.award.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface ITBizAwardPlayerService {
	
	/**
	 * 分页查询抽奖人员资格信息表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询抽奖人员资格信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有抽奖人员资格信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的抽奖人员资格信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertTBizAwardPlayer(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新抽奖人员资格信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateTBizAwardPlayer(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除抽奖人员资格信息表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteTBizAwardPlayer(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除抽奖人员资格信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteTBizAwardPlayer(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 获取抽奖期次
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getSeriesNumList(InputObject inputObject,OutputObject outputObject) throws Exception; 
	/**
	 * 批量导入抽奖人员信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void importAwardPlayer(InputObject inputObject,OutputObject outputObject) throws Exception;
}
