package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IArticlePraiseService {
	
	/**
	 * 分页查询文章点赞记录	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询文章点赞记录
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有文章点赞记录
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的文章点赞记录
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXArticlePraise(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新文章点赞记录
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXArticlePraise(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除文章点赞记录(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXArticlePraise(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除文章点赞记录
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXArticlePraise(InputObject inputObject, OutputObject outputObject) throws Exception;
}
