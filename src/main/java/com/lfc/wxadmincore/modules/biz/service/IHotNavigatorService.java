package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IHotNavigatorService {
	
	/**
	 * 分页查询热点导航	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询热点导航
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有热点导航
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的热点导航
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertHotNavigator(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新热点导航
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateHotNavigator(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除热点导航(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteHotNavigator(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除热点导航
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteHotNavigator(InputObject inputObject, OutputObject outputObject) throws Exception;
}
