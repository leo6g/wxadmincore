package com.lfc.wxadmincore.modules.biz.weiweb.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IModulesService {
	
	/**
	 * 分页查询微网站模块信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微网站模块信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微网站模块信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微网站模块信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertBizModules(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微网站模块信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateBizModules(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微网站模块信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteBizModules(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微网站模块信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteBizModules(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据code查询数据
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getModulesByCode(InputObject inputObject,OutputObject outputObject) throws Exception;
}
