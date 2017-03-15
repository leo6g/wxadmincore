package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IActSignService ;

public class ActSignServiceImpl extends BaseServiceImpl implements IActSignService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询活动报名信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("ActSignMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("ActSignMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("ActSignMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("ActSignMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertActSign(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("id",StringUtil.getSquence());
			return getBaseDao().insert("ActSignMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateActSign(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询活动报名是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("ActSignMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("ActSignMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("活动报名已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteActSign(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("ActSignMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteActSign(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("ActSignMapper.logicDelete", inputObject.getParams());

	}
	@Override
	public void getByOpenId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("ActSignMapper.getByOpenId", inputObject.getParams());
		outputObject.setObject(object);
	}
	
	@Override
	public void countAll(InputObject inputObject, OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("actType", "metal");
		int count = getBaseDao().getTotalCount("ActSignMapper.countAll", map);
		outputObject.setObject(count);

	}

}
