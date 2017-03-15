package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXKeywordsRespService {
	
	/**
	 * 分页查询关键字回复语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据MaterialID查询
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getByMaterialID(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 根据ID查询微信关注语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有关键字回复语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入关键字回复语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXKeywordsResp(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新关键字回复语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXKeywordsResp(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信关注语(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXSubcribeMsg(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信关注语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXSubcribeMsg(InputObject inputObject, OutputObject outputObject) throws Exception;
}
