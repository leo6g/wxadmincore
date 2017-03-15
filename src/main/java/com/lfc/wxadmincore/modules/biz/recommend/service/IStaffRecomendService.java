package com.lfc.wxadmincore.modules.biz.recommend.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IStaffRecomendService {
	
	/**
	 * 分页查询员工推荐	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询员工推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有员工推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的员工推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertStaffRecomend(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新员工推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateStaffRecomend(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除员工推荐(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteStaffRecomend(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除员工推荐
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteStaffRecomend(InputObject inputObject, OutputObject outputObject) throws Exception;
}
