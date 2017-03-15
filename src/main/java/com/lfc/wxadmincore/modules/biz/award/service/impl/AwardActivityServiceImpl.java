package com.lfc.wxadmincore.modules.biz.award.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.award.service.IAwardActivityService ;

public class AwardActivityServiceImpl extends BaseServiceImpl implements IAwardActivityService   {

	
	
	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信抽奖活动信息
		List<Map<String, Object>> list = getBaseDao().queryForList("AwardActivityMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("AwardActivityMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("AwardActivityMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("AwardActivityMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertAwardActivity(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("activityId",StringUtil.getSquence());
			int flag = getBaseDao().insert("AwardActivityMapper.insert", inputObject.getParams());
			if (flag>0) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) inputObject.getParams().get("list");
				//遍历插入活动奖品信息
				for (Map<String, Object> map : list) {
					map.put("activityId", inputObject.getParams().get("activityId"));
					map.put("awardsId", StringUtil.getSquence());
					flag = getBaseDao().insert("AwardActivityMapper.insertAwardsInfo", map);
				}
			}
			if (flag>0) {
				return flag;
			}else {
				outputObject.setReturnCode("-9999");
				outputObject.setReturnMessage("添加信息失败!");
				return 0;
			}
	}

	@Override
	public int updateAwardActivity(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信抽奖活动是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("AwardActivityMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("AwardActivityMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("微信抽奖活动已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteAwardActivity(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//先删除详细表
		int flag= getBaseDao().delete("AwardActivityMapper.deleteAwardsInfo", inputObject.getParams());
		
		//再删除主表
		flag  = getBaseDao().delete("AwardActivityMapper.delete", inputObject.getParams());
		
		return flag;
	}
	
	@Override
	public int logicDeleteAwardActivity(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("AwardActivityMapper.logicDelete", inputObject.getParams());

	}
}
