package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.util.CollectionUtils;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IAtmLocationService;

public class AtmLocationServiceImpl extends BaseServiceImpl implements
		IAtmLocationService {

	@Override
	public void getList(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// 分页查询列表信息
		inputObject.getParams().put("deleteFlag", "0");
		List<Map<String, Object>> list = getBaseDao().queryForList("AtmLocationMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("AtmLocationMapper.countAll", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		// 总数量
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
	}

	@Override
	public void getById(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		Object object = getBaseDao().queryForObject(
				"AtmLocationMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}

	@Override
	public void getAll(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		inputObject.getParams().put("deleteFlag", "0");
		List<Map<String, Object>> list = getBaseDao().queryForList(
				"AtmLocationMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);

	}

	@Override
	public int insertObj(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("atmId", StringUtil.getSquence());
		inputObject.getParams().put("deleteFlag", 0);
		inputObject.getParams().put("createTime", new Date());
		return getBaseDao().insert("AtmLocationMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateObj(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		return getBaseDao().insert("AtmLocationMapper.update", inputObject.getParams());
	}

	@Override
	public int logicDelObj(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("AtmLocationMapper.logicDelete", inputObject.getParams());

	}

	@Override
	public int updateAtmLocation(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().insert("AtmLocationMapper.update", inputObject.getParams());
	}
	
	@Override
	public void getNearbyAtmList(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// 分页查询列表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("AtmLocationMapper.getNearbyAtmList", inputObject.getParams());
		outputObject.setBeans(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void importAtmLocationInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> atmL=new ArrayList<Map<String,Object>>();
		atmL=(List<Map<String, Object>>) inputObject.getParams().get("atmList");
		if(!CollectionUtils.isEmpty(atmL)){
			for (Map<String,Object> atmMap:atmL) {
				Map<String,Object> object=(Map<String, Object>) getBaseDao().queryForObject("AtmLocationMapper.getByDepartName", atmMap);
				if(!"".equals(object.get("departId")) && object.get("departId")!=null){
					atmMap.put("netPointId",object.get("departId").toString());
				}
				Map<String,Object> objectN=(Map<String, Object>) getBaseDao().queryForObject("AtmLocationMapper.countAtmByAddr",atmMap);
				if(!"".equals(objectN.get("atmId")) && objectN.get("atmId")!=null){
					atmMap.put("atmId",objectN.get("atmId").toString());
					atmMap.put("updateTime", new Date());
					getBaseDao().update("AtmLocationMapper.update", atmMap);
				}else{
					atmMap.put("atmId", StringUtil.getSquence());
					getBaseDao().insert("AtmLocationMapper.insert", atmMap);	
				}
				
		}

		}

	}

}
