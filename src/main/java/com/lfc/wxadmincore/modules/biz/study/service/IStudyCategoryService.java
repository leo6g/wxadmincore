package com.lfc.wxadmincore.modules.biz.study.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IStudyCategoryService {
	
	/**
	 * 分页查询邮学堂板块表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询邮学堂板块表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有邮学堂板块表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的邮学堂板块表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertStudyCategory(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新邮学堂板块表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateStudyCategory(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除邮学堂板块表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteStudyCategory(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除邮学堂板块表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteStudyCategory(InputObject inputObject, OutputObject outputObject) throws Exception;
}
