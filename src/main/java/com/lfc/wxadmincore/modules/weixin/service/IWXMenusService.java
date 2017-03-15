package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXMenusService {
	
	/**
	 * 分页查询微信菜单	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getWXMenusListByPage(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 查询所有微信菜单
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAllWXMenus(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信菜单
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getWXMenusById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 插入一条新的微信菜单
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXMenus(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信菜单
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXMenus(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信菜单(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int delWXMenus(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信菜单
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDelWXMenus(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 把菜单同步到微信平台
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public void synchrWXMenu(InputObject inputObject, OutputObject outputObject) throws Exception;
	void getMenusTree(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 判断menu_key是否存在
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void checkMenuKey(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 根据菜单KEY,查询菜单信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getWXMenuByKey(InputObject inputObject,OutputObject outputObject)throws Exception;
	
	
}
