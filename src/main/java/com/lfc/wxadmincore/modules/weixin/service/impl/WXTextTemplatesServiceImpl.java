package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXTextTemplatesService ;

public class WXTextTemplatesServiceImpl extends BaseServiceImpl implements IWXTextTemplatesService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信文本模版信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXTextTemplatesMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXTextTemplatesMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("WXTextTemplatesMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("WXTextTemplatesMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXTextTemplates(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信文本模版是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXTextTemplatesMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("textTempId", StringUtil.getSquence());
			return getBaseDao().insert("WXTextTemplatesMapper.insert", inputObject.getParams());
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("微信文本模版已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateWXTextTemplates(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信文本模版是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXTextTemplatesMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("WXTextTemplatesMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("微信文本模版已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteWXTextTemplates(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("WXTextTemplatesMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXTextTemplates(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXTextTemplatesMapper.logicDelete", inputObject.getParams());

	}
	

}
