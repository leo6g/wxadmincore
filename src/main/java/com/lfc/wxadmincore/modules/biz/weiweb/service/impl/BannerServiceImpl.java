package com.lfc.wxadmincore.modules.biz.weiweb.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.weiweb.service.IBannerService;

public class BannerServiceImpl extends BaseServiceImpl implements IBannerService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微网站广告栏信息信息
		inputObject.getParams().put("deleteFlag","0");
		//inputObject.getParams().put("orderByClause","CREATE_TIME");
		List<Map<String, Object>> list = getBaseDao().queryForList("BizBannerMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("BizBannerMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("BizBannerMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		//inputObject.getParams().put("orderByClause","CREATE_TIME");
		List<Map<String,Object>> list = getBaseDao().queryForList("BizBannerMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertBizBanner(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("bannerId",StringUtil.getSquence());
			return getBaseDao().insert("BizBannerMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateBizBanner(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微网站广告栏信息是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("BannerMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("BizBannerMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("微网站广告栏信息已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteBizBanner(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("BizBannerMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteBizBanner(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("BizBannerMapper.logicDelete", inputObject.getParams());

	}
	

}
