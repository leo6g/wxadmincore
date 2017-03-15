package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IFinanceProdService {
	
	/**
	 * 分页查询理财产品管理	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询理财产品管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有理财产品管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的理财产品管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertFinanceProd(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新理财产品管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateFinanceProd(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除理财产品管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDelFinanceProd(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 更新理财产品管理基本信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateObj(InputObject inputObject, OutputObject outputObject) throws Exception;
	
}
