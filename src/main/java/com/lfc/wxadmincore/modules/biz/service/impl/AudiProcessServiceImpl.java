package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IAudiProcessService ;

public class AudiProcessServiceImpl extends BaseServiceImpl implements IAudiProcessService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询图文审批流程信息信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("AudiProcessMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("AudiProcessMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("AudiProcessMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("AudiProcessMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertAudiProcess(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			Map<String,Object> taskMap = new HashMap<String,Object>();
			Map<String,Object> logsMap = new HashMap<String,Object>();
			String taskId = StringUtil.getSquence();
			String logId = StringUtil.getSquence();
			String processId = StringUtil.getSquence();
			String materialId = inputObject.getParams().get("materialId").toString();
			String taskerId = inputObject.getParams().get("initiatorId").toString();
			
			taskMap.put("taskId", taskId);
			taskMap.put("processId", processId);
			taskMap.put("articleIds", materialId);
			taskMap.put("taskerId", taskerId);
			taskMap.put("type", "series");
			taskMap.put("state", "unprocessed");
			taskMap.put("createTime", new Date());
			taskMap.put("executeTime", new Date());
			getBaseDao().insert("ProcessTaskMapper.insert", taskMap);
			
			logsMap.put("logId", logId);
			logsMap.put("taskId", taskId);
			logsMap.put("processId", processId);
			logsMap.put("procResult", "陆鹰运维人员创建图文审核流程");
			logsMap.put("createTime", new Date());
			getBaseDao().insert("ProcessLogsMapper.insert", logsMap);
			
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("processId",processId);
			return getBaseDao().insert("AudiProcessMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateAudiProcess(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询图文审批流程信息是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("AudiProcessMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("AudiProcessMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("图文审批流程信息已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteAudiProcess(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		 		getBaseDao().delete("ProcessTaskMapper.delete", inputObject.getParams());
		 		getBaseDao().delete("ProcessLogsMapper.delete", inputObject.getParams());
		return  getBaseDao().delete("AudiProcessMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteAudiProcess(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("AudiProcessMapper.logicDelete", inputObject.getParams());

	}
	
	@Override
	public void getViewById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("AudiProcessMapper.getViewById", inputObject.getParams());
		outputObject.setObject(object);

	}
	

}
