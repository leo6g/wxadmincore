package com.lfc.wxadmincore.modules.system.service;

import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IMenuService {
	/**
	 * 查询所有的菜单信息tree
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void queryMenuTree(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 查询所有的菜单信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void queryMenuList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的菜单信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertMenuInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新菜单信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateMenuInfoById(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除菜单信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void deletetMenuInfoById(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询菜单信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void selectByPrimaryKey(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 查询所有的菜单信息tree2
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void queryMenusTree(InputObject inputObject,OutputObject outputObject) throws Exception; 
	/**
	 * 查询用户的菜单信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryMenuTreeForUser(InputObject inputObject,OutputObject outputObject) throws Exception;
}
