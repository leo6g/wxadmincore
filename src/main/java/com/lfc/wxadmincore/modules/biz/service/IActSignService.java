package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IActSignService {
	
	/**
	 * 分页查询活动报名	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询活动报名
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有活动报名
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的活动报名
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertActSign(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新活动报名
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateActSign(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除活动报名(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteActSign(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除活动报名
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteActSign(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 根据openId查询活动报名
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getByOpenId(InputObject inputObject,OutputObject outputObject)throws Exception;
	
	public void countAll(InputObject inputObject, OutputObject outputObject) throws Exception;
}
