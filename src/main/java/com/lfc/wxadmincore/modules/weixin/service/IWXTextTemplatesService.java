package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXTextTemplatesService {
	
	/**
	 * 分页查询微信文本模版	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信文本模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信文本模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信文本模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXTextTemplates(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信文本模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXTextTemplates(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信文本模版(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXTextTemplates(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信文本模版
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXTextTemplates(InputObject inputObject, OutputObject outputObject) throws Exception;
}
