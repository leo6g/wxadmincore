package com.lfc.wxadmincore.modules.weixin.service;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXUserService {
	
	/**
	 * 分页查询微信关注用户表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信关注用户表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	
	/**
	 * 根据OpenId查询微信关注用户表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getByOpenId(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信关注用户表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信关注用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertWXUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新用户表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateWXUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信关注用户表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int setUserRemark(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信关注用户表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信关注用户表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新用户组groupId
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXUserGroup(InputObject inputObject,OutputObject outputObject) throws Exception ;
	
	/**
	 * 同步所有已关注的微信用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public OutputObject syncWXUsers(InputObject inputObject,OutputObject outputObject) throws Exception ;
	
	/**
	 * 批量给用户增加标签
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public OutputObject tagToUser(InputObject inputObject,OutputObject outputObject) throws Exception;
	
	/**
	 * 批量给用户取消标签
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public OutputObject deleteTagToUser(InputObject inputObject,OutputObject outputObject) throws Exception ;
	
	/**
	 * 获取黑名单列表
	 * @return
	 */
	public OutputObject getBlackList(InputObject inputObject,OutputObject outputObject) throws Exception;
	
	/**
	 * 添加黑名单列表
	 * @return
	 */
	public OutputObject insertBlackList(InputObject inputObject,OutputObject outputObject) throws Exception;
	
	/**
	 * 删除黑名单列表
	 * @return
	 */
	public OutputObject deleteBlackList(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 取消关注时，根据OpenId更新用户表
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	int updateByOpenId(InputObject inputObject, OutputObject outputObject)
			throws Exception;
	
	
	/**
	 * 根据OpenId查询微信关注用户表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getUserInfoByOpenId(InputObject inputObject,OutputObject outputObject)throws Exception;
}
