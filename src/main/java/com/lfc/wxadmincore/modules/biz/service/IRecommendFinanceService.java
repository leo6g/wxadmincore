package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IRecommendFinanceService {
	
	/**
	 * 分页查询文章推荐	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询文章推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有文章推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的文章推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertRecommendFinance(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新文章推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateRecommendFinance(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除文章推荐(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteRecommendFinance(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除文章推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteRecommendFinance(InputObject inputObject, OutputObject outputObject) throws Exception;
}
