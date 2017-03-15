package com.lfc.wxadmincore.modules.biz.study.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IStudyArticleService {
	
	/**
	 * 分页查询邮学堂文章表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询邮学堂文章表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有邮学堂文章表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的邮学堂文章表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertStudyArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新邮学堂文章表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateStudyArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除邮学堂文章表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteStudyArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除邮学堂文章表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteStudyArticle(InputObject inputObject, OutputObject outputObject) throws Exception;
}
