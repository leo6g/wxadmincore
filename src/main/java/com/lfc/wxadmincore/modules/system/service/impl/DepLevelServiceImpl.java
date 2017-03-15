package com.lfc.wxadmincore.modules.system.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.system.service.IDepLevelService;

/**
 * 机构级别服务类
 * @author helei
 * @date 2016-09-18
 */
public class DepLevelServiceImpl extends BaseServiceImpl implements IDepLevelService {

	
	@Override
	public void getDepLevelListByPage(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分业查询机构级别信息
		List<Map<String, Object>> depLevels = getBaseDao().queryForList("DepartLevelMapper.getDepLevelListByPage", inputObject.getParams());
		outputObject.setBeans(depLevels);
		int count = getBaseDao().getTotalCount("DepartLevelMapper.countAllDepLevel", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		//总数量
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
	}
	
	@Override
	public void getAllDepLevel(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询所有机构级别信息
		List<Map<String, Object>> depLevels = getBaseDao().queryForList("DepartLevelMapper.getAllDepLevel", inputObject.getParams());
		outputObject.setBeans(depLevels);
		
	}

	@Override
	public void getDepLevelById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		//根据机构级别ID,查询机构级别信息
		Object depart = getBaseDao().queryForObject("DepartLevelMapper.getDepLevelById", inputObject.getParams());
		outputObject.setObject(depart);
		
	}

	@Override
	public int insertDepLevel(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		//查询级别编码是否存在
		Object deplevel = getBaseDao().queryForObject("DepartLevelMapper.getDepLevelByCode", inputObject.getParams());
		if(deplevel==null){
			inputObject.getParams().put("id", StringUtil.getSquence());
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			return getBaseDao().insert("DepartLevelMapper.insertDepLevel", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("级别编码已经存在，请修改!");
			return -1;
		}
	}

	@Override
	public int updateDepLevel(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		//查询级别编码是否存在
		Object deplevel = getBaseDao().queryForObject("DepartLevelMapper.getDepLevelByCode", inputObject.getParams());
		if(deplevel==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("DepartLevelMapper.updateDepLevel", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("级别编码已经存在，请修改!");
			return -1;
		}
	}

	@Override
	public int delDepLevel(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logicDelDepLevel(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("DepartLevelMapper.logicDelDepLevel", inputObject.getParams());
	}


}
