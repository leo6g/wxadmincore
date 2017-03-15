package com.lfc.wxadmincore.modules.weiweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weiweb.service.IModulesService ;

public class ModulesServiceImpl extends BaseServiceImpl implements IModulesService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微网站模块信息管理信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("ModulesMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("ModulesMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("ModulesMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("ModulesMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	//bootstrap-treeView 使用 父级 子级 分开查询 然后封装一起
	@Override
	public void queryModuleTree(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询父级菜单列表
		inputObject.getParams().put("levels", "1");
		inputObject.getParams().put("deleteFlag", 0);
		List<Map<String,Object>> pmodulelist = getBaseDao().queryForList("ModulesMapper.getAll", inputObject.getParams());
		//将数据查询结果的数据转换为树状数据
		List<Map<String, Object>> treeDatas = convertToTreeData(pmodulelist);
		//查询子级菜单列表
		List<Map<String,Object>> cmodulelist=getSubModuleTree(treeDatas);
		outputObject.setBeans(cmodulelist);

	}
	//jquery-treetable 使用 父级 子级一起查询 
	@Override
	public void queryModulesTree(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询父级菜单列表
		inputObject.getParams().put("levels", "1");
		inputObject.getParams().put("deleteFlag", 0);
		List<Map<String,Object>> pmodulelist = getBaseDao().queryForList("ModulesMapper.getAll", inputObject.getParams());
		//查询子级菜单列表
		List<Map<String,Object>> modulelist=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> moduleObj:pmodulelist){
			modulelist=getSubModulesTree(moduleObj,modulelist);
		}
		outputObject.setBeans(modulelist);
	}
	/**
	 * 查询微网站模块信息
	 */
	public List<Map<String, Object>>  getSubModuleTree(List<Map<String, Object>> modules) throws Exception {
		//查询菜单子集信息
		for (Map<String, Object> moduleObj : modules) {
			String objId = (String) ((Map<String, Object>)moduleObj.get("tags")).get("moduleId");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentId", objId);
			params.put("deleteFlag", 0);
			//查询是否有子集
			int total = getBaseDao().getTotalCount("ModulesMapper.getModulesCountByParentId", params);
			if(total > 0){
				//查询子集
				List<Map<String, Object>> subModuleList = getBaseDao().queryForList("ModulesMapper.getModuleListByParentId", params);
				//将数据查询结果的数据转换为树状数据
				List<Map<String, Object>> subModuleTreeData = convertToTreeData(subModuleList);
				moduleObj.put("nodes", subModuleTreeData);
				//循环子集
				getSubModuleTree(subModuleTreeData);
			}
		}
		
		if(CollectionUtils.isNotEmpty(modules)){
			return modules;
		}else{
			return null;
		}
	}

	/**
	 * 查询微网站模块子集信息-父级子级在一个list 按照父级 子级 的格式
	 */
	public List<Map<String, Object>>  getSubModulesTree(Map<String, Object> moduleObj,List<Map<String, Object>> newList) throws Exception {
		//查询菜单子集信息
			String objId = (String) moduleObj.get("moduleId");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentId", objId);
			//params.put("moduleId", objId);
			params.put("deleteFlag", 0);
			//只有顶级菜单才会在这加入
			if(moduleObj.get("levels") !=null && "1".equals(moduleObj.get("levels").toString())){
				newList.add(moduleObj);
			}
			int total = getBaseDao().getTotalCount("ModulesMapper.getModulesCountByParentId", params);
			if(total > 0){
				//查询子集
				List<Map<String, Object>> submoduleList = getBaseDao().queryForList("ModulesMapper.getAll", params);
				for(Map<String, Object> submodule : submoduleList){
					newList.add(submodule);				
					//循环子集
					getSubModulesTree(submodule,newList);
				}
			}
			
			if(CollectionUtils.isNotEmpty(newList)){
				return newList;
			}else{
				return null;
			}
	}
	
	@Override
	public int insertWWModules(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("moduleId",StringUtil.getSquence());
			return getBaseDao().insert("ModulesMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateWWModules(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微网站模块信息管理是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("ModulesMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("ModulesMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("微网站模块信息管理已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteWWModules(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("ModulesMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWWModules(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("ModulesMapper.logicDelete", inputObject.getParams());

	}
	/**
	 * 将数据查询结果的数据转换为树状数据
	 */
	private List<Map<String, Object>> convertToTreeData(List<Map<String, Object>> moduleLevels){
		List<Map<String, Object>> treeDatas = new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isNotEmpty(moduleLevels)){
			for (Map<String, Object> moduleObj : moduleLevels) {
				Map<String, Object> treeData = new HashMap<String, Object>();
				treeData.put("text", moduleObj.get("name"));
				treeData.put("href", "javascript:");
				treeData.put("tags", moduleObj);
				treeDatas.add(treeData);
			}
		}
		return treeDatas;
	}

}
