package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXArticleMaterialService {
	
	/**
	 * 分页查询微信图文素材 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 根据ID查询微信图文素材
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	
	
	/**
	 * 根据微信图文MediaID，查询微信文章
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getArticlesByWxMediaId(InputObject inputObject,OutputObject outputObject)throws Exception;
	
	
	
	/**
	 * 查询所有微信图文素材
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 插入一条新的微信图文素材
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXMaterial(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 更新微信图文素材
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXMaterial(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 删除微信图文素材
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXMaterial(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	
	/**
	 * 同步微信图文素材
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateSyncNews(InputObject inputObject, OutputObject outputObject) throws Exception;

}
