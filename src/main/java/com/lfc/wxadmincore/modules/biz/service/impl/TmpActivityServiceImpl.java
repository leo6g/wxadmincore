package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.ITmpActivityService ;

public class TmpActivityServiceImpl extends BaseServiceImpl implements ITmpActivityService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询预约中奖信息设置信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("TmpActivityMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("TmpActivityMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("TmpActivityMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("TmpActivityMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertTmpActivity(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("id",StringUtil.getSquence());
			return getBaseDao().insert("TmpActivityMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateTmpActivity(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询预约中奖信息设置是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("TmpActivityMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("TmpActivityMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("预约中奖信息设置已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteTmpActivity(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("TmpActivityMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteTmpActivity(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("TmpActivityMapper.logicDelete", inputObject.getParams());

	}
	

}
