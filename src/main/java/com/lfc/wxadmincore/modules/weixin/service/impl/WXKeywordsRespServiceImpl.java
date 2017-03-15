package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXKeywordsRespService;
import com.lfc.wxadmincore.modules.weixin.service.IWXSubcribeMsgService ;

public class WXKeywordsRespServiceImpl extends BaseServiceImpl implements IWXKeywordsRespService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询关键词回复规则
		List<Map<String, Object>> list = getBaseDao().queryForList("WXKeywordsRespMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXKeywordsRespMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("WXKeywordsRespMapper.getById", inputObject.getParams());
		outputObject.setObject(object);
	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("WXKeywordsRespMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	}
	@Override
	public int insertWXKeywordsResp(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("keywordsId", StringUtil.getSquence());
			return getBaseDao().insert("WXKeywordsRespMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateWXKeywordsResp(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			return getBaseDao().update("WXKeywordsRespMapper.update", inputObject.getParams());
	}
	@Override
	public void getByMaterialID(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int deleteWXSubcribeMsg(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int logicDeleteWXSubcribeMsg(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
