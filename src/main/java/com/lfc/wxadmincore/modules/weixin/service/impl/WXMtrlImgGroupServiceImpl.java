package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXMtrlImgGroupService ;

public class WXMtrlImgGroupServiceImpl extends BaseServiceImpl implements IWXMtrlImgGroupService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询图片素材分组表信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXMtrlImgGroupMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXMtrlImgGroupMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("WXMtrlImgGroupMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("WXMtrlImgGroupMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXMtrlImgGroup(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("groupId",StringUtil.getSquence());
			return getBaseDao().insert("WXMtrlImgGroupMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateWXMtrlImgGroup(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询图片素材分组表是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXMtrlImgGroupMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("WXMtrlImgGroupMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("图片素材分组表已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteWXMtrlImgGroup(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("WXMtrlImgGroupMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXMtrlImgGroup(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXMtrlImgGroupMapper.logicDelete", inputObject.getParams());

	}
	@Override
	public void getListInfoById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = getBaseDao().queryForList("WXMtrlImgGroupMapper.getListInfoById", inputObject.getParams());
		outputObject.setBeans(list);
	}
	

}
