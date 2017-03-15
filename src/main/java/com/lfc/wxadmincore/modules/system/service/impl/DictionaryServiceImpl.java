package com.lfc.wxadmincore.modules.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.DateUtil;
import com.lfc.wxadmincore.common.utils.MD5Util;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.system.service.IDictionaryService;

public class DictionaryServiceImpl extends BaseServiceImpl implements IDictionaryService {

	
	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("DictionaryMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("DictionaryMapper.countTotalDictionary", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		//总数量
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
	}
	

	@Override
	public int insertDicGroup(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//查询编码是否存在
		int count = getBaseDao().getTotalCount("DictionaryMapper.countDicGroupByCode", inputObject.getParams());
		if(count==0){
			inputObject.getParams().put("id", StringUtil.getSquence());
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			//新增信息
			return getBaseDao().insert("DictionaryMapper.insertDicGroup", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("编码已经存在，请修改!");
			return 0;
		}
	}

	
	@Override
	public void getDicGroupById(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		Object dictionary = getBaseDao().queryForObject("DictionaryMapper.getDicGroupById", inputObject.getParams());
		outputObject.setObject(dictionary);
	}
	
	
	@Override
	public int updateDicGroup(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询编码是否存在
		int count = getBaseDao().getTotalCount("DictionaryMapper.countDicGroupByCode", inputObject.getParams());
		if(count==0){
			inputObject.getParams().put("updateTime", new Date());
			//修改信息
			return getBaseDao().update("DictionaryMapper.updateDicGroup", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("编码已经存在，请修改!");
			return 0;
		}
	}

	@Override
	public int deleteDicGroup(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		return getBaseDao().update("DictionaryMapper.updateDicGroup", inputObject.getParams());
	}


	@Override
	public void getDicById(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		Object dictionary = getBaseDao().queryForObject("DictionaryMapper.getDicById", inputObject.getParams());
		outputObject.setObject(dictionary);
	}


	@Override
	public void getDicTree(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//查询一级字典信息
		List<Map<String, Object>> dicLevel1 = getBaseDao().queryForList("DictionaryMapper.getDicsLevel_1", inputObject.getParams());
		//将数据查询结果的数据转换为树状数据
		List<Map<String, Object>> treeDatas = convertToTreeData(dicLevel1);
		//循环查询子集字典信息
		List<Map<String, Object>> result = getSubDicTree(treeDatas);
		outputObject.setBeans(result);
	}
	
	
	/**
	 * 查询数据字典子集信息
	 */
	private List<Map<String, Object>> getSubDicTree(List<Map<String, Object>> treeDatas) throws Exception {
		// TODO Auto-generated method stub
		//查询子集组织机构信息
		if(CollectionUtils.isNotEmpty(treeDatas)){
			for (Map<String, Object> treeData : treeDatas) {
				String objId = (String) ((Map<String, Object>)treeData.get("tags")).get("id");
				String dicGroupId = (String) ((Map<String, Object>)treeData.get("tags")).get("dicGroupId");
				//查询是否有子集
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("parentId", objId);
				params.put("dicGroupId", dicGroupId);
				int total = getBaseDao().getTotalCount("DictionaryMapper.getDicCountByParentId", params);
				if(total > 0){
					//查询子集
					List<Map<String, Object>> subDepartList = getBaseDao().queryForList("DictionaryMapper.getDicListByParentId", params);
					//将数据查询结果的数据转换为树状数据
					List<Map<String, Object>> subTreeDatas = convertToTreeData(subDepartList);
					treeData.put("nodes", subTreeDatas);
					//循环子集
					getSubDicTree(subTreeDatas);
				}
			}
		}
		if(CollectionUtils.isNotEmpty(treeDatas)){
			return treeDatas;
		}else{
			return null;
		}
	}
	
	
	
	/**
	 * 将数据查询结果的数据转换为树状数据
	 */
	private List<Map<String, Object>> convertToTreeData(List<Map<String, Object>> departLevels){
		List<Map<String, Object>> treeDatas = new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isNotEmpty(departLevels)){
			for (Map<String, Object> depObj : departLevels) {
				Map<String, Object> treeData = new HashMap<String, Object>();
				treeData.put("text", depObj.get("dicName"));
				treeData.put("href", "javascript:");
				treeData.put("tags", depObj);
				treeDatas.add(treeData);
			}
		}
		return treeDatas;
	}


	@Override
	public int insertDic(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//查询编码是否存在
		int count = getBaseDao().getTotalCount("DictionaryMapper.countDicByCode", inputObject.getParams());
		if(count==0){
			inputObject.getParams().put("id", StringUtil.getSquence());
			inputObject.getParams().put("deleteFlag", 0);
			//新增信息
			return getBaseDao().insert("DictionaryMapper.insertDic", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("编码已经存在，请修改!");
			return 0;
		}
	}


	@Override
	public int updateDic(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//查询编码是否存在
		int count = getBaseDao().getTotalCount("DictionaryMapper.countDicByCode", inputObject.getParams());
		if(count==0){
			inputObject.getParams().put("updateTime", new Date());
			//修改信息
			return getBaseDao().update("DictionaryMapper.updateDic", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("编码已经存在，请修改!");
			return 0;
		}
	}


	@Override
	public void queryDicsByGCode(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("DictionaryMapper.queryDicsByGCode", inputObject.getParams());
		outputObject.setBeans(list);
	}


	@Override
	public void getDicDetailByDicCode(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String,Object> map = (Map<String, Object>) getBaseDao().queryForObject("DictionaryMapper.getDicDetailByDicCode", inputObject.getParams());
		outputObject.setBean(map);
	}


}
