package com.lfc.wxadmincore.modules.system.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.system.service.IPositionService;

/**
 * 职位服务类
 * @author helei
 * @date 2016-09-23
 */
public class PositionServiceImpl extends BaseServiceImpl implements IPositionService {

	
	@Override
	public void getPositionListByPage(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分业查询职位信息
		List<Map<String, Object>> depLevels = getBaseDao().queryForList("PositionMapper.getPositionListByPage", inputObject.getParams());
		outputObject.setBeans(depLevels);
		int count = getBaseDao().getTotalCount("PositionMapper.countAllPosition", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		//总数量
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
	}
	
	@Override
	public void getAllPosition(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询所有职位信息
		List<Map<String, Object>> depLevels = getBaseDao().queryForList("PositionMapper.getAllPosition", inputObject.getParams());
		outputObject.setBeans(depLevels);
		
	}

	@Override
	public void getPositionById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		//根据职位ID,查询职位信息
		Object depart = getBaseDao().queryForObject("PositionMapper.getPositionById", inputObject.getParams());
		outputObject.setObject(depart);
		
	}

	@Override
	public int insertPosition(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		//查询职位编码是否存在
		Object deplevel = getBaseDao().queryForObject("PositionMapper.getPositionByCode", inputObject.getParams());
		if(deplevel==null){
			inputObject.getParams().put("id", StringUtil.getSquence());
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			return getBaseDao().insert("PositionMapper.insertPosition", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("职位编码已经存在，请修改!");
			return -1;
		}
	}

	@Override
	public int updatePosition(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		//查询职位编码是否存在
		Object deplevel = getBaseDao().queryForObject("PositionMapper.getPositionByCode", inputObject.getParams());
		if(deplevel==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("PositionMapper.updatePosition", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("职位编码已经存在，请修改!");
			return -1;
		}
	}

	@Override
	public int delPosition(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logicDelPosition(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("PositionMapper.logicDelPosition", inputObject.getParams());
	}


}
