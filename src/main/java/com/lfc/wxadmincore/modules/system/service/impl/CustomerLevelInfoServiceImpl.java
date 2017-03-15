package com.lfc.wxadmincore.modules.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.system.service.ICustomerLevelInfoService ;

public class CustomerLevelInfoServiceImpl extends BaseServiceImpl implements ICustomerLevelInfoService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询邮储个金部客户等级信息信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("CustomerLevelInfoMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("CustomerLevelInfoMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("CustomerLevelInfoMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("CustomerLevelInfoMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertCustomerLevelInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("id",StringUtil.getSquence());
			return getBaseDao().insert("CustomerLevelInfoMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateCustomerLevelInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询邮储个金部客户等级信息是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("CustomerLevelInfoMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("CustomerLevelInfoMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("邮储个金部客户等级信息已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteCustomerLevelInfo(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		System.out.println(inputObject.getParams()+"------------------>");
		return getBaseDao().delete("CustomerLevelInfoMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteCustomerLevelInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("CustomerLevelInfoMapper.logicDelete", inputObject.getParams());

	}
	@Override
	public int importCustomerLevelInfo(InputObject inputObject, OutputObject outputObject) throws Exception {
		int flag=0;
		
		List<Map<String, Object>> customerLevelList= (List<Map<String, Object>>) inputObject.getParams().get("customerLevelList");
		if (customerLevelList.size()>0) {
			//如果有数据先要进行清空，然后再进行插入
			getBaseDao().delete("CustomerLevelInfoMapper.deleteAll", null);
		}
		//循环遍历进行插入
		for (int i = 0; i < customerLevelList.size(); i++) {
			Map<String,Object> paraMap = customerLevelList.get(i);
			paraMap.put("id", StringUtil.getSquence());
			flag = getBaseDao().insert("CustomerLevelInfoMapper.insert", paraMap);
		}
		return flag;

	}
	

}
