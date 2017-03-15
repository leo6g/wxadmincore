package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IMerchApplierService {
	
	/**
	 * 分页查询特惠商户申请信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 角色分页查询特惠商户申请信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getListByRole(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询特惠商户申请信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有特惠商户申请信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的特惠商户申请信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertMerchApplier(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新特惠商户申请信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateMerchApplier(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除特惠商户申请信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteMerchApplier(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除特惠商户申请信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteMerchApplier(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 查询所有特惠商户详细信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getDetail(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 根据查询条件导出所有的申请信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getListByRoleAll(InputObject inputObject,OutputObject outputObject) throws Exception;
}
