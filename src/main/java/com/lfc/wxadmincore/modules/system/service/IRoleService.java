package com.lfc.wxadmincore.modules.system.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IRoleService {
	/**
	 * 查询所有的角色信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void queryRoleList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertRoleInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateRoleInfoById(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void delRoleLogic(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void selectByPrimaryKey(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 根据部门查询用户列表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void selectUserByDepartId(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 向用户角色关联表插入信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertRoleUserInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据角色删除角色用户关系表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void deletetRUById(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 根据角色查询角色用户关系表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */	
	public void selectRUById(InputObject inputObject,OutputObject outputObject) throws Exception;
	
	/**
	 * 向角色权限关联表插入信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertRolePrivInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据角色删除角色权限关系表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void deletetRPById(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 根据code查询
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void selectRoleByCode(InputObject inputObject,OutputObject outputObject)throws Exception;
	
	/**
	 * 获取所有角色
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void selectAllRoles(InputObject inputObject,OutputObject outputObject) throws Exception;
	
}
