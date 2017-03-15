package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IArticleRemarkService {
	
	/**
	 * 分页查询文章评论信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询文章评论信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有文章评论信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的文章评论信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXArticleRemark(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新文章评论信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXArticleRemark(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除文章评论信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXArticleRemark(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除文章评论信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXArticleRemark(InputObject inputObject, OutputObject outputObject) throws Exception;
}
