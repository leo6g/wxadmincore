package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXCardInfoService {
	
	/**
	 * 分页查询信用卡信息发布表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询信用卡信息发布表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有信用卡信息发布表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的信用卡信息发布表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXCardInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新信用卡信息发布表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXCardInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除信用卡信息发布表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXCardInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除信用卡信息发布表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXCardInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
}
