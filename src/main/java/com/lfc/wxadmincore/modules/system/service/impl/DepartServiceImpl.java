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
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.system.service.IDepartService;

/**
 * 组织机构服务类
 * @author helei
 * @date 2016-09-14
 */
public class DepartServiceImpl extends BaseServiceImpl implements IDepartService {

	@Override
	public void getDepartTree(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询一级组织机构信息
		List<Map<String, Object>> departLevels = getBaseDao().queryForList("DepartMapper.getDepartsLevel_1", inputObject.getParams());
		//将数据查询结果的数据转换为树状数据
		List<Map<String, Object>> treeDatas = convertToTreeData(departLevels);
		//循环查询子集组织机构信息
		List<Map<String, Object>> result = getSubDepartTree(treeDatas);
		outputObject.setBeans(result);
	}
	
	
	/**
	 * 将数据查询结果的数据转换为树状数据
	 */
	private List<Map<String, Object>> convertToTreeData(List<Map<String, Object>> departLevels){
		List<Map<String, Object>> treeDatas = new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isNotEmpty(departLevels)){
			for (Map<String, Object> depObj : departLevels) {
				Map<String, Object> treeData = new HashMap<String, Object>();
				treeData.put("text", depObj.get("departName"));
				treeData.put("href", "javascript:");
				treeData.put("tags", depObj);
				treeDatas.add(treeData);
			}
		}
		return treeDatas;
	}

	/**
	 * 查询组织机构子集信息
	 */
	private List<Map<String, Object>> getSubDepartTree(List<Map<String, Object>> treeDatas) throws Exception {
		// TODO Auto-generated method stub
		//查询子集组织机构信息
		if(CollectionUtils.isNotEmpty(treeDatas)){
			for (Map<String, Object> treeData : treeDatas) {
				String objId = (String) ((Map<String, Object>)treeData.get("tags")).get("id");
				//查询是否有子集
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("parentId", objId);
				int total = getBaseDao().getTotalCount("DepartMapper.getDepartCountByParentId", params);
				if(total > 0){
					//查询子集
					List<Map<String, Object>> subDepartList = getBaseDao().queryForList("DepartMapper.getDepartListByParentId", params);
					//将数据查询结果的数据转换为树状数据
					List<Map<String, Object>> subTreeDatas = convertToTreeData(subDepartList);
					treeData.put("nodes", subTreeDatas);
					//循环子集
					getSubDepartTree(subTreeDatas);
				}
			}
		}
		if(CollectionUtils.isNotEmpty(treeDatas)){
			return treeDatas;
		}else{
			return null;
		}
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public void getDepartById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//根据组织机构ID,查询组织机构信息
		Object depart = getBaseDao().queryForObject("DepartMapper.getDepartById", inputObject.getParams());
		//查询上级机构名称
		Object parentId = ((Map<String, Object>)depart).get("parentId");
		if(parentId!=null && !parentId.toString().equals("0")){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", parentId);
			Object parentDepart = getBaseDao().queryForObject("DepartMapper.getDepartById", params);
			((Map<String, Object>)depart).put("parentDepName", ((Map<String, Object>)parentDepart).get("departName"));
		}else{
			((Map<String, Object>)depart).put("parentDepName", "---");
		}
		outputObject.setObject(depart);
	}

	@Override
	public int insertDepart(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		//查询机构编码是否存在
		int count = getBaseDao().getTotalCount("DepartMapper.countDepartByCode", inputObject.getParams());
		if(count==0){
			inputObject.getParams().put("id", StringUtil.getSquence());
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			int result = getBaseDao().insert("DepartMapper.insertDepart", inputObject.getParams());
//			if(result > 0){
//				inputObject.getParams().put("departId", inputObject.getParams().get("id"));
//				return getBaseDao().insert("DepartMapper.insertDepLevelRel", inputObject.getParams());
//			}
			return 0;
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("机构编码已经存在，请修改!");
			return -1;
		}
	}

	@Override
	public int updateDepart(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		//查询机构编码是否存在
		int count = getBaseDao().getTotalCount("DepartMapper.countDepartByCode", inputObject.getParams());
		if(count==0){
			inputObject.getParams().put("updateTime", new Date());
			int result = getBaseDao().update("DepartMapper.updateDepart", inputObject.getParams());
//			if(result > 0){
//				inputObject.getParams().put("departId", inputObject.getParams().get("id"));
//				return getBaseDao().update("DepartMapper.updateDepLevelRel", inputObject.getParams());
//			}
			return 0;
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("机构编码已经存在，请修改!");
			return -1;
		}
	}

	@Override
	public int saveDepart(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delDepart(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logicDelDepart(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		inputObject.getParams().put("parentId", inputObject.getParams().get("id"));
		int total = getBaseDao().getTotalCount("DepartMapper.getDepartCountByParentId", inputObject.getParams());
		if(total==0){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("DepartMapper.logicDelDepart", inputObject.getParams());
		}else{
			outputObject.setReturnCode("-2");
			outputObject.setReturnMessage("选择的机构下面有子机构，请先删除子机构!");
			return -2;
		}
	}



}
