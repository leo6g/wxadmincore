package com.lfc.wxadmincore.modules.biz.weiweb.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IModulesArticleService {
	
	/**
	 * 分页查询微网站文章信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微网站文章信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微网站文章信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微网站文章信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertBizModulesArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微网站文章信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateBizModulesArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微网站文章信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteBizModulesArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微网站文章信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteBizModulesArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
}
