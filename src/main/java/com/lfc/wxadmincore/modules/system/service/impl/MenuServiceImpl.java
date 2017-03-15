package com.lfc.wxadmincore.modules.system.service.impl;
import java.text.SimpleDateFormat;
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
import com.lfc.wxadmincore.modules.system.service.IMenuService;

public class MenuServiceImpl extends BaseServiceImpl implements IMenuService  {

	//bootstrap-treeView 使用 父级 子级 分开查询 然后封装一起
	@Override
	public void queryMenuTree(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询父级菜单列表
		List<Map<String,Object>> pmenulist = getBaseDao().queryForList("MenuMapper.selectMenuInfoTree", inputObject.getParams());
		//将数据查询结果的数据转换为树状数据
		List<Map<String, Object>> treeDatas = convertToTreeData(pmenulist);
		//查询子级菜单列表
		List<Map<String,Object>> cmenulist=getSubMenuTree(treeDatas);
		outputObject.setBeans(cmenulist);

	}
	
	//查询用户的菜单tree
	@Override
	public List<Map<String,Object>> queryMenuTreeForUser(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询父级菜单列表
		List<Map<String,Object>> pmenulist = getBaseDao().queryForList("MenuMapper.selectMenuInfoByUser", inputObject.getParams());
		//查询子级菜单列表
		List<Map<String,Object>> cmenulist=getSubMenuTreeForUser(pmenulist,inputObject);
		return cmenulist;

	}
	
	//jquery-treetable 使用 父级 子级一起查询 
	@Override
	public void queryMenusTree(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String roleId=null;
		if(inputObject.getParams().get("roleId") !=null && inputObject.getParams().get("roleId")!=""){
			roleId= inputObject.getParams().get("roleId").toString();
		}
		//查询父级菜单列表
		List<Map<String,Object>> pmenulist = getBaseDao().queryForList("MenuMapper.selectMenuInfoTree", inputObject.getParams());
		//查询子级菜单列表
		List<Map<String,Object>> menulist=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> menuObj:pmenulist){
		menulist=getSubMenusTree(menuObj,menulist,roleId);
		}
		outputObject.setBeans(menulist);
	}

	@Override
	public void insertMenuInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("id", StringUtil.getSquence());
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createTime=sf.parse(sf.format(new Date()));
		inputObject.getParams().put("createTime", createTime);
		int flag=getBaseDao().insert("MenuMapper.insertMenuInfo", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("添加菜单信息成功");		
		}else{
			  outputObject.setReturnMessage("添加菜单信息失败");
		}

	}

	@Override
	public void updateMenuInfoById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createTime=sf.parse(sf.format(new Date()));
		inputObject.getParams().put("updateTime", createTime);
		int flag=getBaseDao().update("MenuMapper.updateMenuInfoById", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("更新菜单信息成功");		
		}else{
			  outputObject.setReturnMessage("更新菜单信息失败");
		}

	}

	@Override
	public void deletetMenuInfoById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("menuId", inputObject.getParams().get("id"));
		inputObject.getParams().put("parentId", inputObject.getParams().get("id"));
		int flag=getBaseDao().delete("MenuMapper.deletetMenuInfoById", inputObject.getParams());
		if(flag>0){
			//删除对应的按钮信息
			getBaseDao().delete("ButtonOperMapper.deletetButtonInfoByMenuId", inputObject.getParams());
			//查询菜单的子菜单
			List<Map<String,Object>> submenulist=getBaseDao().queryForList("MenuMapper.getMenuListByParentId", inputObject.getParams());
			//删除子菜单的按钮信息
			for(Map<String,Object> submenu:submenulist){
				submenu.put("menuId", submenu.get("id"));
				getBaseDao().delete("ButtonOperMapper.deletetButtonInfoByMenuId",submenu);
			}
			//删除子类
			getBaseDao().delete("MenuMapper.deletetMenuInfoByPId", inputObject.getParams());

              outputObject.setReturnMessage("删除菜单信息成功");		
		}else{
			  outputObject.setReturnMessage("删除菜单信息失败");
		}

	}
	
	@Override
	public void selectByPrimaryKey(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("MenuMapper.selectByPrimaryKey", inputObject.getParams());
		inputObject.getParams().put("menuId", inputObject.getParams().get("id"));
		List<Map<String,Object>> list = getBaseDao().queryForList("ButtonOperMapper.selectButtonInfoList", inputObject.getParams());
		outputObject.setObject(object);
		outputObject.setBeans(list);

	}
	/**
	 * 查询菜单子集信息
	 */
	public List<Map<String, Object>>  getSubMenuTree(List<Map<String, Object>> menus) throws Exception {
		//查询菜单子集信息
		for (Map<String, Object> menuObj : menus) {
			String objId = (String) ((Map<String, Object>)menuObj.get("tags")).get("id");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentId", objId);
			//查询是否有子集
			int total = getBaseDao().getTotalCount("MenuMapper.getMenuCountByParentId", params);
			if(total > 0){
				//查询子集
				List<Map<String, Object>> subMenuList = getBaseDao().queryForList("MenuMapper.getMenuListByParentId", params);
				//将数据查询结果的数据转换为树状数据
				List<Map<String, Object>> subMenuTreeData = convertToTreeData(subMenuList);
				menuObj.put("nodes", subMenuTreeData);
				//循环子集
				getSubMenuTree(subMenuTreeData);
			}
		}
		
		if(CollectionUtils.isNotEmpty(menus)){
			return menus;
		}else{
			return null;
		}
	}

	/**
	 * 查询菜单子集信息-子级加一个children属性-增加权限限制
	 */
	public List<Map<String, Object>>  getSubMenuTreeForUser(List<Map<String, Object>> menus,InputObject inputObject) throws Exception {
		//查询菜单子集信息
		for (Map<String, Object> menuObj : menus) {
			String objId = (String) menuObj.get("id");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentId", objId);
			params.put("menuId", objId);
			params.put("userId", inputObject.getParams().get("userId"));
			//查询按钮信息
			List<Map<String,Object>> buttonlist = getBaseDao().queryForList("ButtonOperMapper.selectBtnInfoByUser",params);
			//查询是否有子集
			menuObj.put("buttoninfo", buttonlist);
			int total = getBaseDao().getTotalCount("MenuMapper.selectSubMenuCountByUser", params);
			if(total > 0){
				//查询子集
				List<Map<String, Object>> subMenuList = getBaseDao().queryForList("MenuMapper.selectSubMenuInfoByUser", params);
				menuObj.put("children", subMenuList);
				//循环子集
				getSubMenuTreeForUser(subMenuList,inputObject);
			}
		}
		
		if(CollectionUtils.isNotEmpty(menus)){
			return menus;
		}else{
			return null;
		}
	}
	
	@Override
	public void queryMenuList(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("MenuMapper.selectMenuInfo", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("MenuMapper.selectMenuCount", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		//总数量
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
		
	}
	
	
	/**
	 * 查询菜单子集信息-父级子级在一个list 按照父级 子级 的格式
	 */
	public List<Map<String, Object>>  getSubMenusTree(Map<String, Object> menuObj,List<Map<String, Object>> newList,String roleId) throws Exception {
		//查询菜单子集信息
			String objId = (String) menuObj.get("id");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentId", objId);
			params.put("roleId", roleId);
			params.put("menuId", objId);
			//查询按钮信息
			List<Map<String,Object>> buttonlist = getBaseDao().queryForList("ButtonOperMapper.selectButtonInfoList",params);
			if(StringUtil.isNotEmpty(roleId)){
				for (Map<String, Object> btnObj : buttonlist) {
					params.put("buttonId", btnObj.get("id"));
					int countB = getBaseDao().getTotalCount("ButtonOperMapper.queryPrivInfoByRoleId", params);
					if(countB>0){
						btnObj.put("isRelate", 1);
					}
				}
			}
			menuObj.put("buttoninfo", buttonlist);
			//是否与当前角色关联 有关联值为1
			if(StringUtil.isNotEmpty(roleId)){
				int countM = getBaseDao().getTotalCount("MenuMapper.queryPrivInfoByRoleId", params);
				if(countM>0){
					menuObj.put("isRelate", 1);
				}
			}
			//只有顶级菜单才会在这加入
			if(menuObj.get("menuLevel") !=null && "1".equals(menuObj.get("menuLevel").toString())){
				newList.add(menuObj);
			}
			int total = getBaseDao().getTotalCount("MenuMapper.getMenuCountByParentId", params);
			if(total > 0){
				//查询子集
				List<Map<String, Object>> subMenuList = getBaseDao().queryForList("MenuMapper.getMenuListByParentId", params);
				for(Map<String, Object> submenu : subMenuList){
					//是否与当前角色关联 有关联值为1
					if(StringUtil.isNotEmpty(roleId)){
						Map<String, Object> subpar = new HashMap<String, Object>();
						subpar.put("menuId", submenu.get("id"));
						int countM = getBaseDao().getTotalCount("MenuMapper.queryPrivInfoByRoleId", subpar);
						if(countM>0){
							submenu.put("isRelate", 1);
						}

					}
					newList.add(submenu);
					getSubMenusTree(submenu,newList,roleId);
				}				
				//循环子集
			}
		
		
		if(CollectionUtils.isNotEmpty(newList)){
			return newList;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 将数据查询结果的数据转换为树状数据
	 */
	private List<Map<String, Object>> convertToTreeData(List<Map<String, Object>> menuLevels){
		List<Map<String, Object>> treeDatas = new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isNotEmpty(menuLevels)){
			for (Map<String, Object> menuObj : menuLevels) {
				Map<String, Object> treeData = new HashMap<String, Object>();
				treeData.put("text", menuObj.get("menuName"));
				treeData.put("href", "javascript:");
				treeData.put("tags", menuObj);
				treeDatas.add(treeData);
			}
		}
		return treeDatas;
	}

}
