package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IAdvertisementService ;

public class AdvertisementServiceImpl extends BaseServiceImpl implements IAdvertisementService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询文章植入广告管理信息
		List<Map<String, Object>> list = getBaseDao().queryForList("AdvertisementMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("AdvertisementMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("AdvertisementMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("AdvertisementMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertAdvertisement(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询文章植入广告管理是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("AdvertisementMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			return getBaseDao().insert("AdvertisementMapper.insert", inputObject.getParams());
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("文章植入广告管理已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateAdvertisement(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			return getBaseDao().update("AdvertisementMapper.update", inputObject.getParams());
	}
	@Override
	public int deleteAdvertisement(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("AdvertisementMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteAdvertisement(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("AdvertisementMapper.logicDelete", inputObject.getParams());

	}
	

}
