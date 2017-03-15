package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IBIZInterestRateService {
	
	/**
	 * 分页查询存贷款利率信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询存贷款利率信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有存贷款利率信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的存贷款利率信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertBIZInterestRate(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新存贷款利率信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateBIZInterestRate(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除存贷款利率信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteBIZInterestRate(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除存贷款利率信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteBIZInterestRate(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据type查询所有子类型信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getSubTypeByType(InputObject inputObject, OutputObject outputObject) throws Exception;
}
