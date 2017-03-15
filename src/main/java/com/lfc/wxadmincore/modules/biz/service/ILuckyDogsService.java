package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface ILuckyDogsService {
	
	/**
	 * 分页查询中奖信息表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询中奖信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有中奖信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的中奖信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertLuckyDogs(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新中奖信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateLuckyDogs(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除中奖信息表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteLuckyDogs(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除中奖信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteLuckyDogs(InputObject inputObject, OutputObject outputObject) throws Exception;
}
