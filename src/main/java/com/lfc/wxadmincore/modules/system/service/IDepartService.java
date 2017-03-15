package com.lfc.wxadmincore.modules.system.service;


import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IDepartService {
	
	/**
	 * 查询所有组织机构信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getDepartTree(InputObject inputObject, OutputObject outputObject) throws Exception;

	/**
	 * 根据组织机构ID,查询组织机构信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getDepartById(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 新增组织机构信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int insertDepart(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 编辑组织机构信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int updateDepart(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 保存组织机构信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int saveDepart(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 删除组织机构信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int delDepart(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 逻辑删除组织机构信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int logicDelDepart(InputObject inputObject, OutputObject outputObject) throws Exception;

	
}
