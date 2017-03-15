package com.lfc.wxadmincore.modules.system.service;


import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IPositionService {
	
	
	/**
	 * 分业查询职位信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getPositionListByPage(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	
	/**
	 * 查询所有职位信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAllPosition(InputObject inputObject, OutputObject outputObject) throws Exception;

	/**
	 * 根据职位ID,查询职位信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getPositionById(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 新增职位信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int insertPosition(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 编辑职位信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int updatePosition(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	
	/**
	 * 删除职位信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int delPosition(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	
	/**
	 * 逻辑删除职位信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int logicDelPosition(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
}
