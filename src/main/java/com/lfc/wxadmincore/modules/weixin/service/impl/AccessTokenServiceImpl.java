package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IAccessTokenService ;

public class AccessTokenServiceImpl extends BaseServiceImpl implements IAccessTokenService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信获取token信息
		List<Map<String, Object>> list = getBaseDao().queryForList("AccessTokenMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("AccessTokenMapper.countAll", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		//总数量
		beanMap.put("count", count);
		//当前页码
		beanMap.put("currentPage", inputObject.getParams().get("pageNumber"));
		Integer limit = (Integer) inputObject.getParams().get("limit");
		if(limit != null){
			int totalPages = (count + limit -1) / limit;
			//总页数
			beanMap.put("totalPages", totalPages);
		}
		outputObject.setBean(beanMap);
	}
	@Override
	public void getById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("AccessTokenMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("AccessTokenMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertAccessToken(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("id",StringUtil.getSquence());
			return getBaseDao().insert("AccessTokenMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateAccessToken(InputObject inputObject,
		OutputObject outputObject) throws Exception {
		inputObject.getParams().put("id", "1");
		inputObject.getParams().put("expiresIb", 3600);
		return getBaseDao().update("AccessTokenMapper.update", inputObject.getParams());

	}
	@Override
	public int deleteAccessToken(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("AccessTokenMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteAccessToken(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("AccessTokenMapper.logicDelete", inputObject.getParams());

	}
	

}
