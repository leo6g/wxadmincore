package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IAtmLocationService {

	/**
	 * 分页查询列表
	 * 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject)
			throws Exception;

	/**
	 * 根据ID查询实体
	 * 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject, OutputObject outputObject)
			throws Exception;

	/**
	 * 查询所有实体
	 * 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject)
			throws Exception;

	/**
	 * 插入一条新的实体
	 * 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertObj(InputObject inputObject,
			OutputObject outputObject) throws Exception;

	/**
	 * 更新实体信息
	 * 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateObj(InputObject inputObject,
			OutputObject outputObject) throws Exception;

	/**
	 * 逻辑删除实体
	 * 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDelObj(InputObject inputObject,
			OutputObject outputObject) throws Exception;

	/**
	 * 更新ATM位置基本信息
	 * 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateAtmLocation(InputObject inputObject, OutputObject outputObject)
			throws Exception;
	/**
	 * 查询附近的ATM的信息列表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getNearbyAtmList(InputObject inputObject, OutputObject outputObject)
			throws Exception ;
	/**
	 * 导入Atm位置信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void importAtmLocationInfo(InputObject inputObject, OutputObject outputObject)
			throws Exception ;
}
