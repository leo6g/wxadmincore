package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface ICardApplierService {
	
	/**
	 * 分页查询信用卡申办信息表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 角色查询信用卡申办信息表-导出excel用	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getListByRoleAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 角色分页查询信用卡申办信息表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getListByRole(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询信用卡申办信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有信用卡申办信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的信用卡申办信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertCardApplier(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新信用卡申办信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateCardApplier(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除信用卡申办信息表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteCardApplier(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除信用卡申办信息表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteCardApplier(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 信用卡申请查询--导出
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getListByCardInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
}
