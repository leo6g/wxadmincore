package com.lfc.wxadmincore.modules.system.service;

import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IManagerService {
	
	
	/**
	 * 登录
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int login(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 分页查询所有的用户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 新增用户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertManager(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 根据ID，查询用户所有信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	
	/**
	 * 修改用户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateManager(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	
	/**
	 * 修改用户基本信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateManagerObj(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 判断用户密码是否正确
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void checkPassword(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 修改密码
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void editPassWord(InputObject inputObject,OutputObject outputObject) throws Exception;
	
	
	/**
	 * 根据用户的OPENID获取对应的角色编码  多个角色之前用,进行分隔 zy
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 * */
	public OutputObject getUserRoleCodesByOpenId(InputObject inputObject,OutputObject outputObject) throws Exception;
	
	/**
	 * 根据用户的OPENID获取对应的机构编码 zy
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 * */
	public OutputObject getUserDepartCodeByOpenId(InputObject inputObject,OutputObject outputObject) throws Exception;

	
	/**
	 * 根据用户ID保存用户的二维码图片路径
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 * */
	public int updateManagerCodeImg(InputObject inputObject, OutputObject outputObject) throws Exception;

	
	/**
	 * 批量插入员工信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public OutputObject importUserInfo(InputObject inputObject,OutputObject outputObject) throws Exception;
	



	public void getUserDepartCodeByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 获得客户经理列表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getTask(InputObject inputObject,OutputObject outputObject) throws Exception;


	public void getUserByOpenId(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据条件，查询用户信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getUserInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据用户id获取该用户所拥有点的角色
	 * @param inputObject
	 * @param outputObject
	 */
	public void getRolesByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 根据角色删除角色用户关系表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void deleteRUById(InputObject inputObject,OutputObject outputObject) throws Exception;
	
	/**
	 * 向用户角色关联表插入信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertRoleUserInfo(InputObject inputObject, OutputObject outputObject) throws Exception;
}
