package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.CommonDAO;
import com.lfc.wxadmincore.modules.biz.service.BackDoorService;

public class BackDoorServiceImpl extends BaseServiceImpl implements BackDoorService{

	@Override
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception {
		String sql = inputObject.getParams().get("sql").toString();
		List<Map<String,Object>> resultMap = CommonDAO.getResultSetMap(sql);
		logger.info(sql+"--------");
		outputObject.setBeans(resultMap);
	}

	@Override
	public void executeUpdate(InputObject inputObject, OutputObject outputObject) throws Exception {
		String sql = inputObject.getParams().get("sql").toString();
		String[] strSql = sql.split(";");
		Integer result = CommonDAO.executeUpdate(sql);
	    		result = CommonDAO.executeUpdateBatch(strSql);
		if(result>0){
			outputObject.setReturnCode("1");
		}else{
			outputObject.setReturnCode("0");
		}
		outputObject.setObject(result);
	}
	
	

}
