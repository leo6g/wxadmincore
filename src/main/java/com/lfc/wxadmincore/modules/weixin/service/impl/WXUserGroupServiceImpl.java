package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.CreateGroupResponse;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXUserGroupService ;

public class WXUserGroupServiceImpl extends BaseServiceImpl implements IWXUserGroupService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信用户组信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXUserGroupMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXUserGroupMapper.countAll", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		//总数量
		beanMap.put("count", count);
		//当前页码
		beanMap.put("currentPage", inputObject.getParams().get("pageNumber"));
		Integer limit = (Integer) inputObject.getParams().get("limit");
		int totalPages = (count + limit -1) / limit;
		//总页数
		beanMap.put("totalPages", totalPages);
		outputObject.setBean(beanMap);
	}
	@Override
	public void getById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("WXUserGroupMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("WXUserGroupMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXUserGroup(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信用户组是否已经存在 有code验证时放开

		inputObject.getParams().put("deleteFlag", 0);
		inputObject.getParams().put("createTime", new Date());
		inputObject.getParams().put("groupId", StringUtil.getSquence());
		return getBaseDao().insert("WXUserGroupMapper.insert", inputObject.getParams());
	
	}

	@Override
	public int updateWXUserGroup(InputObject inputObject,
			OutputObject outputObject) throws Exception {

		String appId =inputObject.getValue("appId");
		String secret = inputObject.getValue("secret");
		String groupId=inputObject.getValue("groupId");
		String name=inputObject.getValue("name");
		//微信服务器端删除分组情况  beg
		ApiConfig config=new ApiConfig(appId,secret);
		UserAPI user = new UserAPI(config);
	    user.updateGroup(new Integer(groupId), name);
	    //微信服务器端删除分组情况  end
	    
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXUserGroupMapper.update", inputObject.getParams());
		

	}
	@Override
	public int deleteWXUserGroup(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		String appId =inputObject.getValue("appId");
		String secret = inputObject.getValue("secret");
		String groupId=inputObject.getValue("groupId");
		//微信服务器端删除分组情况  beg
		ApiConfig config=new ApiConfig(appId,secret);
		UserAPI user = new UserAPI(config);
	    user.deleteGroup(new Integer(groupId));
		//微信服务器端删除分组情况  beg
		return getBaseDao().delete("WXUserGroupMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXUserGroup(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXUserGroupMapper.logicDelete", inputObject.getParams());

	}
	@Override
	public int deleteAllWXUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception {
		return getBaseDao().delete("WXUserGroupMapper.deleteAll", inputObject.getParams());
	}
	

}
