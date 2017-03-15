package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXNewsItemsService ;

import oracle.sql.CLOB;

public class WXNewsItemsServiceImpl extends BaseServiceImpl implements IWXNewsItemsService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信图文详情信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXNewsItemsMapper.getList", inputObject.getParams());
		/*
		for(Map<String, Object> map: list){
			if(map.get("content") != null){
				String content = StringUtil.ClobToString((CLOB)map.get("content"));
				map.put("content", content);
				System.out.println("-------------->"+content);
			}
		}
		*/
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXNewsItemsMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("WXNewsItemsMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("WXNewsItemsMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXNewsItems(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信图文详情是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXNewsItemsMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("itemId", StringUtil.getSquence());
			
			int count = getBaseDao().insert("WXNewsItemsMapper.insert", inputObject.getParams());
			if(count > 0){
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("itemId", inputObject.getParams().get("itemId"));
				outputObject.setBean(m);
			}
			
			return count;
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("微信图文详情已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateWXNewsItems(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信图文详情是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXNewsItemsMapper.getByCode", inputObject.getParams());
		//if(object==null){
		//	inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXNewsItemsMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("微信图文详情已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteWXNewsItems(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("WXNewsItemsMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXNewsItems(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXNewsItemsMapper.logicDelete", inputObject.getParams());

	}
	

}
