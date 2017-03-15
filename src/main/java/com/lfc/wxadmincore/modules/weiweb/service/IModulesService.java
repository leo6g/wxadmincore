package com.lfc.wxadmincore.modules.weiweb.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IModulesService {
	
	/**
	 * 分页查询微网站模块信息管理	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微网站模块信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微网站模块信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微网站模块信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWWModules(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微网站模块信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWWModules(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微网站模块信息管理(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWWModules(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微网站模块信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWWModules(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 查询所有的微网站模块信息tree
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void queryModuleTree(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 查询所有的微网站模块信息tree2
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void queryModulesTree(InputObject inputObject,OutputObject outputObject) throws Exception; 
}
