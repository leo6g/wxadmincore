package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXSubscribeRespService {
	/**
	 * 保存回复语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int saveReply(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 查询回复语
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getReply(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除回复
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteReply(InputObject inputObject, OutputObject outputObject) throws Exception;
	
}
