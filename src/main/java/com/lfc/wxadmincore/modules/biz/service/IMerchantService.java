package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IMerchantService {
	
	/**
	 * 分页查询特惠商户信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询特惠商户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exceptio
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有特惠商户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的特惠商户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertMerchant(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新特惠商户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateMerchant(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除特惠商户信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteMerchant(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除特惠商户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteMerchant(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 查询特惠商户类型信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getMerchantType(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 查询特惠商户状态信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getMerchantState(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 查询所有特惠商户详细信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getDetail(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 导入数据
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void importMerchant(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 根据名称查询特惠商户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception 
	 */
	public void getByDepartName(InputObject inputObject,OutputObject outputObject)throws Exception;
}
