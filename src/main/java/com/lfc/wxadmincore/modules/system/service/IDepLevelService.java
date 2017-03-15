package com.lfc.wxadmincore.modules.system.service;


import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IDepLevelService {
	
	
	/**
	 * 分业查询机构级别信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getDepLevelListByPage(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	
	/**
	 * 查询所有机构级别信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAllDepLevel(InputObject inputObject, OutputObject outputObject) throws Exception;

	/**
	 * 根据机构级别ID,查询级别信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getDepLevelById(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 新增机构级别信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int insertDepLevel(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 编辑结构级别信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int updateDepLevel(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	
	/**
	 * 删除机构级别信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int delDepLevel(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	
	/**
	 * 逻辑删除机构级别信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int logicDelDepLevel(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
}
