package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IProcessTaskService ;

public class ProcessTaskServiceImpl extends BaseServiceImpl implements IProcessTaskService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询图文审批流程任务信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("ProcessTaskMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("ProcessTaskMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("ProcessTaskMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("ProcessTaskMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertProcessTask(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("taskId",StringUtil.getSquence());
			return getBaseDao().insert("ProcessTaskMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateProcessTask(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询图文审批流程任务是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("ProcessTaskMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("ProcessTaskMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("图文审批流程任务已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteProcessTask(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("ProcessTaskMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteProcessTask(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("ProcessTaskMapper.logicDelete", inputObject.getParams());

	}
	

}
