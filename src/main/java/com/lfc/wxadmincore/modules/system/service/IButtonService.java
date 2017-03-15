package com.lfc.wxadmincore.modules.system.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IButtonService {
	/**
	 * 查询所有的按钮信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void queryButtonList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的按钮信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertButtonInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新按钮信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateButtonInfoById(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除按钮信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void deletetButtonInfoById(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询按钮信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void selectByPrimaryKey(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 根据菜单ID删除按钮信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void deletetButtonInfoByMenuId(InputObject inputObject,OutputObject outputObject) throws Exception;
}
