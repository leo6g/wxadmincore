package com.lfc.wxadmincore.modules.system.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.system.service.IRoleService;

public  class RoleServiceImpl extends BaseServiceImpl implements IRoleService  {

	@Override
	public void queryRoleList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("RoleMapper.selectRoleInfoList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("RoleMapper.selectRoleCount", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		//总数量
		beanMap.put("count", count);
		outputObject.setBean(beanMap);

	}
	@Override
	public void selectByPrimaryKey(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("RoleMapper.selectByPrimaryKey", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void insertRoleInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("id", StringUtil.getSquence());
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date createTime=sf.parse(sf.format(new Date()));
			inputObject.getParams().put("createTime", createTime);
			inputObject.getParams().put("deleteFlag", "0");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int flag=getBaseDao().insert("RoleMapper.insertRoleInfo", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("添加成功");		
		}else{
			  outputObject.setReturnMessage("添加失败");
		}

	}

	@Override
	public void updateRoleInfoById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date createTime=sf.parse(sf.format(new Date()));
			inputObject.getParams().put("updateTime", createTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int flag=getBaseDao().update("RoleMapper.updateRoleInfoById", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("更新成功");		
		}else{
			  outputObject.setReturnMessage("更新失败");
		}

	}

	@Override
	public void delRoleLogic(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		int flag=getBaseDao().update("RoleMapper.delRoleLogic", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("删除成功");		
		}else{
			  outputObject.setReturnMessage("删除失败");
		}

	}
	@Override
	public void selectUserByDepartId(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			List<Map<String,Object>> list = getBaseDao().queryForList("RoleMapper.selectUserByDepartId", inputObject.getParams());
			outputObject.setBeans(list);
			int count = getBaseDao().getTotalCount("RoleMapper.selectUserByDepartIdCount", inputObject.getParams());
			Map<String, Object> beanMap = new HashMap<String, Object>();
			//总数量
			beanMap.put("count", count);
			outputObject.setBean(beanMap);
		
	}
	@Override
	public void insertRoleUserInfo(InputObject inputObject,OutputObject outputObject) throws Exception {
		//传来的list包括之前的一关联的用户，所以插入的时候需要先删除再插入
		this.deletetRUById(inputObject, outputObject);
		int flag=getBaseDao().insert("RoleMapper.insertRoleUserInfo", inputObject.getParams().get("roleUserForm"));
		if(flag>0){
              outputObject.setReturnMessage("添加成功");		
		}else{
			  outputObject.setReturnMessage("添加失败");
		}
		
	}
	
	
	@Override
	public void deletetRUById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		int flag=getBaseDao().delete("RoleMapper.deletetRUById", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("删除成功");		
		}else{
			  outputObject.setReturnMessage("删除失败");
		}

	}
	
	
	@Override
	public void selectRUById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			List<Map<String,Object>> list = getBaseDao().queryForList("RoleMapper.selectRUById", inputObject.getParams());
			outputObject.setBeans(list);
		
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void insertRolePrivInfo(InputObject inputObject,OutputObject outputObject) throws Exception {
		//传来的list包括之前的一关联的权限，所以插入的时候需要先删除再插入
		this.deletetRPById(inputObject, outputObject);
		int flag = 1;
		if(((List)inputObject.getParams().get("rolePrivForm")).size() > 0) {
			flag=getBaseDao().insert("RoleMapper.insertRolePrivInfo", inputObject.getParams().get("rolePrivForm"));
		}
		if(flag>0){
              outputObject.setReturnMessage("添加成功");		
		}else{
			  outputObject.setReturnMessage("添加失败");
		}
		
	}
	@Override
	public void deletetRPById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		int flag=getBaseDao().delete("RoleMapper.deletetRPById", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("删除成功");		
		}else{
			  outputObject.setReturnMessage("删除失败");
		}

	}
	
	@Override
	public void selectRoleByCode(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("RoleMapper.selectRoleByCode", inputObject.getParams());
		outputObject.setObject(object);
		
	}
	@Override
	public void selectAllRoles(InputObject inputObject, OutputObject outputObject) throws Exception {
		List<Map<String, Object>> list = getBaseDao().queryForList("RoleMapper.selectAllRoles",
				inputObject.getParams());
		outputObject.setBeans(list);
	}
}
