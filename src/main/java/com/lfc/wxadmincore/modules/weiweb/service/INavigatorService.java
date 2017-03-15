package com.lfc.wxadmincore.modules.weiweb.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface INavigatorService {
	
	/**
	 * 分页查询导航菜单管理	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 根据ID查询导航菜单管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	
	/**
	 * 查询所有导航菜单管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 插入一条新的导航菜单管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertNavigator(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 更新导航菜单管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateNavigator(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 删除导航菜单管理(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteNavigator(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 逻辑删除导航菜单管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteNavigator(InputObject inputObject, OutputObject outputObject) throws Exception;

}
