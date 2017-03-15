package com.lfc.wxadmincore.modules.weixin.service;

import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWeixinService {
	
	/**
	 * 获取微信自动回复信息列表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getWXAutoReplyRespMsgList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 创建用户分组
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void createUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 创建用户分组
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 删除用户分组
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void deleteUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 获取所有分组信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getGroups(InputObject inputObject, OutputObject outputObject) throws Exception;
	 /**
     * 移动关注者所在分组
     */
	public ResultType moveGroupUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 获取图文信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	void getMaterial(InputObject inputObject, OutputObject outputObject)
			throws Exception;
	/**
	 * 获取回复类型
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	void getReType(InputObject inputObject, OutputObject outputObject)
			throws Exception;
}
