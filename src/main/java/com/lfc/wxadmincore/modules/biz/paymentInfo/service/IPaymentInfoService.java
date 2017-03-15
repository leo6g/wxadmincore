package com.lfc.wxadmincore.modules.biz.paymentInfo.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IPaymentInfoService {
	
	/**
	 * 分页查询缴费数据信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询缴费数据信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有缴费数据信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的缴费数据信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertPaymentInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新缴费数据信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updatePaymentInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除缴费数据信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deletePaymentInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除缴费数据信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeletePaymentInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	public void deleteAll(InputObject inputObject, OutputObject outputObject) throws Exception;
}
