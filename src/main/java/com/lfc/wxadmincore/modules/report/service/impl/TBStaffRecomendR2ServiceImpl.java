package com.lfc.wxadmincore.modules.report.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.modules.report.service.ITBStaffRecomendR2Service ;

public class TBStaffRecomendR2ServiceImpl extends BaseServiceImpl implements ITBStaffRecomendR2Service   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询劳动竞赛地区排名信息
		List<Map<String, Object>> list = getBaseDao().queryForList("TBStaffRecomendR2Mapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("TBStaffRecomendR2Mapper.countAll", inputObject.getParams());
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
	public void getListAll(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		List<Map<String, Object>> list = getBaseDao().queryForList("TBStaffRecomendR2Mapper.getListAll", inputObject.getParams());
		outputObject.setBeans(list);
		
	}

}
