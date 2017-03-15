package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.modules.weixin.service.IWXArticlesStatisticsService;

public class WXArticlesStatisticsServiceImpl extends BaseServiceImpl implements
		IWXArticlesStatisticsService {

	@Override
	public void getArticlesStatistics(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = getBaseDao().queryForList("WXArticlesStatisticsMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
	}

	@Override
	public void getArticlesListByDay(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> list = getBaseDao().queryForList("WXArticlesStatisticsMapper.getListByDay", inputObject.getParams());
		outputObject.setBeans(list);
		
	}

	@Override
	public void getArticlesListByHour(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> list = getBaseDao().queryForList("WXArticlesStatisticsMapper.getListByHour", inputObject.getParams());
		outputObject.setBeans(list);
		
	}

	@Override
	public void getArticlesListByYesterday(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> list = getBaseDao().queryForList("WXArticlesStatisticsMapper.getTotalCountByYesterday", inputObject.getParams());
		outputObject.setBeans(list);
		
	}

	

}
