package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IAdvertisementService {
	
	/**
	 * 分页查询文章植入广告管理	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询文章植入广告管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有文章植入广告管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的文章植入广告管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertAdvertisement(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新文章植入广告管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateAdvertisement(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除文章植入广告管理(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteAdvertisement(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除文章植入广告管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteAdvertisement(InputObject inputObject, OutputObject outputObject) throws Exception;
}
