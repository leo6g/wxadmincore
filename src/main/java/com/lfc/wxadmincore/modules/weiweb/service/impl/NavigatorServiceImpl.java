package com.lfc.wxadmincore.modules.weiweb.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.modules.weiweb.service.INavigatorService ;

public class NavigatorServiceImpl extends BaseServiceImpl implements INavigatorService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询导航菜单管理信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("NavigatorMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("NavigatorMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("NavigatorMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("NavigatorMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	
	@Override
	public int insertNavigator(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询导航菜单管理是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("NavigatorMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			return getBaseDao().insert("NavigatorMapper.insert", inputObject.getParams());
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("导航菜单管理已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateNavigator(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询导航菜单管理是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("NavigatorMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("NavigatorMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("导航菜单管理已经存在，请修改!");
		//	return -1;
		//}

	}
	
	@Override
	public int deleteNavigator(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("NavigatorMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteNavigator(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("NavigatorMapper.logicDelete", inputObject.getParams());

	}
	

}
