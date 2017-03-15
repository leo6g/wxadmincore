package com.lfc.wxadmincore.modules.biz.recommend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.recommend.service.IStaffRecomendService ;

public class StaffRecomendServiceImpl extends BaseServiceImpl implements IStaffRecomendService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询员工推荐信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("StaffRecomendMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("StaffRecomendMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("StaffRecomendMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("StaffRecomendMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertStaffRecomend(InputObject inputObject,
			OutputObject outputObject) throws Exception {
//			inputObject.getParams().put("deleteFlag", 0);
//			inputObject.getParams().put("createTime", new Date());
			//更新用户表
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("wxUserId", StringUtil.getSquence());
			inputObject.getParams().put("gender", inputObject.getParams().get("sex").toString());
			inputObject.getParams().put("wxPropertyId", StringUtil.getSquence());
			inputObject.getParams().put("subscribeTime",new java.sql.Timestamp((Long) (inputObject.getParams().get("subscribeTime"))* 1000));
			getBaseDao().insert("WXUserMapper.insertSync", inputObject.getParams());
			getBaseDao().insert("WXUserMapper.insertProperty", inputObject.getParams());
			//更新细吸粉表
			inputObject.getParams().put("id",StringUtil.getSquence());
			return getBaseDao().insert("StaffRecomendMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateStaffRecomend(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		/*if("F".equals(inputObject.getParams().get("curState"))){
			//取消关注删除该用户
			getBaseDao().delete("WXUserMapper.delete", inputObject.getParams());
		}*/
		inputObject.getParams().put("resubTime", new Date());
		return getBaseDao().update("StaffRecomendMapper.update", inputObject.getParams());
	}
	@Override
	public int deleteStaffRecomend(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("StaffRecomendMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteStaffRecomend(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("StaffRecomendMapper.logicDelete", inputObject.getParams());

	}
	

}
