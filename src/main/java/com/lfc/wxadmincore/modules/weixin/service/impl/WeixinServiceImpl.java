package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Group;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.CreateGroupResponse;
import com.github.sd4324530.fastweixin.api.response.GetGroupsResponse;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.modules.weixin.service.IWeixinService;
public class WeixinServiceImpl extends BaseServiceImpl implements IWeixinService {
	
	@Resource
	private ApiConfig config;

	@Override
	public void getWXAutoReplyRespMsgList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("WeixinMapper.getWeixinAutoReplyRespMsgList", inputObject.getParams());
		outputObject.setBeans(list);
	}
	@Override
	public void createUserGroup(InputObject inputObject, OutputObject outputObject){
		String appid = inputObject.getValue("appid");
        String secret = inputObject.getValue("secret");
        String name = inputObject.getValue("groupName");
       
        //向微信服务器端增加分组  beg
        ApiConfig config=new ApiConfig(appid,secret);
		UserAPI user = new UserAPI(config);
		CreateGroupResponse response = user.createGroup(name);
		//向微信服务器端增加分组  end
		
		Integer groupId = response.getGroup().getId();
		
		inputObject.getParams().put("groupId", groupId.toString());
		inputObject.getParams().put("deleteFlag", 0);
		inputObject.getParams().put("createTime", new Date());
		inputObject.getParams().put("name", response.getGroup().getName());
		getBaseDao().insert("WXUserGroupMapper.insert", inputObject.getParams());
	}
	
	
	
	
	
	@Override
	public void updateUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appid = inputObject.getValue("appid");
        String secret = inputObject.getValue("secret");
        String groupName = inputObject.getValue("groupName");
		UserAPI user = new UserAPI(config);
		Integer groupId = Integer.parseInt(inputObject.getValue("groupId"));
		
		user.updateGroup(groupId, groupName);

		inputObject.getParams().put("groupId", groupId.toString());
		inputObject.getParams().put("deleteFlag", 0);
		inputObject.getParams().put("createTime", new Date());
		inputObject.getParams().put("name", groupName);
		getBaseDao().insert("WXUserGroupMapper.update", inputObject.getParams());
		
	}
	@Override
	public void deleteUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appid = inputObject.getValue("appid");
        String secret = inputObject.getValue("secret");
        Integer groupId = Integer.parseInt(inputObject.getValue("groupId"));

        //微信端删除组
        ApiConfig config = new ApiConfig(appid, secret);
		UserAPI api = new UserAPI(config);
		api.deleteGroup(groupId);
		
		getBaseDao().delete("WXUserGroupMapper.delete", inputObject.getParams());
	}
	@Override
	public void getGroups(InputObject inputObject, OutputObject outputObject){
		String appid = inputObject.getValue("appid");
        String secret = inputObject.getValue("secret");
		//ApiConfig config = new ApiConfig(appid, secret);
		UserAPI user = new UserAPI(config);
		GetGroupsResponse response = user.getGroups();
		List<Group> list = response.getGroups();
		//先清除所有分组
		getBaseDao().delete("WXUserGroupMapper.deleteAll", inputObject.getParams());
		for(int i=0;i<list.size();i++){
			Group group = list.get(i);
			
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("name", group.getName());
			inputObject.getParams().put("groupId", group.getId());
		    getBaseDao().insert("WXUserGroupMapper.insert", inputObject.getParams());
		}
	}
	@Override
	public ResultType moveGroupUser(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appid = inputObject.getValue("appid");
        String secret = inputObject.getValue("secret");
		//ApiConfig config = new ApiConfig(appid, secret);
		UserAPI user = new UserAPI(config);
		String toGroupid = inputObject.getValue("groupId");
		String openid = inputObject.getValue("openIds");
		String[] openids = openid.split(",");
		return user.moveGroupUser(openids, toGroupid);
	}
	/**
	 * 获取图文信息
	 */
	@Override
	public void getMaterial(InputObject inputObject, OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list=getBaseDao().queryForList("WeixinMapper.getMaterial", inputObject.getParams());
		outputObject.setBeans(list);
	}
	
	/**
	 * 获取回复类型以及文本信息
	 */
	@Override
	public void getReType(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String,Object> map=(Map<String, Object>) getBaseDao().queryForObject("WeixinMapper.getReType", inputObject.getParams());
		outputObject.setBean(map);
	}
}
