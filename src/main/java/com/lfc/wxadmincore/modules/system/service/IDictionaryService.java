package com.lfc.wxadmincore.modules.system.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IDictionaryService {
	
	
	
	/**
	 * 分页查询所有的数据字典组数据
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 新增数据字典组信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertDicGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 根据ID，查询数据字典组信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getDicGroupById(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	
	/**
	 * 修改数据字典组信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateDicGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	
	/**
	 * 逻辑删除数据字典组信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteDicGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 根据ID，查询数据字典信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getDicById(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 查询数据字典树状信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getDicTree(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 新增数据字典信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertDic(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	
	/**
	 * 修改数据字典信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateDic(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 根据数据字典组编码，查询子集数据字典信息(PS:目前只查询字典一级目录，如果要查询字典树数据，请自行扩展)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void queryDicsByGCode(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 根据字典表的code，查询对应的明细
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 * */
	public void getDicDetailByDicCode(InputObject inputObject, OutputObject outputObject) throws Exception;
	
}
