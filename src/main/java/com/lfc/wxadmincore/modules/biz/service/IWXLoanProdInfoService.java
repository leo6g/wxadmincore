package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXLoanProdInfoService {
	
	/**
	 * 分页查询微信贷款信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信贷款信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信贷款信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信贷款信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXLoanProdInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信贷款信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXLoanProdInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信贷款信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXLoanProdInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信贷款信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXLoanProdInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
}
