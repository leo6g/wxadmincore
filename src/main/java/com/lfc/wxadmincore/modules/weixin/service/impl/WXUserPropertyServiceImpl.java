package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.modules.weixin.service.IWXUserPropertyService;

public class WXUserPropertyServiceImpl extends BaseServiceImpl implements
		IWXUserPropertyService {

	@Override
	public void getKeyIndicatorsOfYesterday(InputObject inputObject,
			OutputObject outputObject) {
		// TODO ...
		List<Map<String, Object>> list = getBaseDao().queryForList("WXUserMapper.getKeyIndicatorsOfYesterday", inputObject.getParams());
		outputObject.setBeans(list);
	}

	@Override
	public void getSubscriberInfosOfYesterday(InputObject inputObject,
			OutputObject outputObject) {
		// TODO ...
				List<Map<String, Object>> list = getBaseDao().queryForList("WXUserMapper.getSubscriberInfosOfYesterday", inputObject.getParams());
				outputObject.setBeans(list);
				
	}


	@Override
	public void getUserProperty(InputObject inputObject,
			OutputObject outputObject) {
		// TODO 
		List<Map<String, Object>> list = getBaseDao().queryForList("WXUserPropertyMapper.getUserProperty", inputObject.getParams());
		outputObject.setBeans(list);
	}


	
	
	
	
	
}
