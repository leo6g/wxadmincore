package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXNewsItemsService {
	
	/**
	 * 分页查询微信图文详情	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信图文详情
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信图文详情
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信图文详情
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXNewsItems(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信图文详情
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXNewsItems(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信图文详情(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXNewsItems(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信图文详情
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXNewsItems(InputObject inputObject, OutputObject outputObject) throws Exception;
}
