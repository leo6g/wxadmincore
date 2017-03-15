package com.lfc.wxadmincore.modules.weiweb.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IArticleService {
	
	/**
	 * 分页查询微网站文章信息管理	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微网站文章信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微网站文章信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微网站文章信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWWArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微网站文章信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWWArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微网站文章信息管理(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWWArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微网站文章信息管理
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWWArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
}
