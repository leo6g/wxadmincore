package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.DateUtil;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IBIZInterestRateService ;

public class BIZInterestRateServiceImpl extends BaseServiceImpl implements IBIZInterestRateService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询存贷款利率信息信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("BIZInterestRateMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("BIZInterestRateMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("BIZInterestRateMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("BIZInterestRateMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertBIZInterestRate(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		int count  = getBaseDao().getTotalCount("BIZInterestRateMapper.countBySome", inputObject.getParams());
		if(count==0){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("rateId",StringUtil.getSquence());
			String rate =inputObject.getParams().get("rate").toString();
			if(rate != null && !("".equals(rate))){
				inputObject.getParams().put("rate", Double.parseDouble(rate));
			}
			return getBaseDao().insert("BIZInterestRateMapper.insert", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("该存贷款利率信息已经存在，请修改!");
			return -1;
		}
	}

	@Override
	public int updateBIZInterestRate(InputObject inputObject,
		OutputObject outputObject) throws Exception {
		String rate =inputObject.getParams().get("rate").toString();
		if(rate != null && !("".equals(rate))){
			inputObject.getParams().put("rate", Double.parseDouble(rate));
		}
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("BIZInterestRateMapper.update", inputObject.getParams());
		
	}
	@Override
	public int deleteBIZInterestRate(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("BIZInterestRateMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteBIZInterestRate(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("BIZInterestRateMapper.logicDelete", inputObject.getParams());

	}
	@Override
	public void getSubTypeByType(InputObject inputObject, 
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("BIZInterestRateMapper.getSubTypeByType", inputObject.getParams());
		outputObject.setBeans(list);
	}
	

}
