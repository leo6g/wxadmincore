package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXAutoResponseService ;

public class WXAutoResponseServiceImpl extends BaseServiceImpl implements IWXAutoResponseService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信自动回复信息模版信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXAutoResponseMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXAutoResponseMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("WXAutoResponseMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("WXAutoResponseMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXAutoResponse(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信自动回复信息模版是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXAutoResponseMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("responseId", StringUtil.getSquence());
			return getBaseDao().insert("WXAutoResponseMapper.insert", inputObject.getParams());
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("微信自动回复信息模版已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateWXAutoResponse(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信自动回复信息模版是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXAutoResponseMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("WXAutoResponseMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("微信自动回复信息模版已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteWXAutoResponse(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("WXAutoResponseMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXAutoResponse(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXAutoResponseMapper.logicDelete", inputObject.getParams());

	}
	

}
