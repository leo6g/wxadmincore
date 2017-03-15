package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.sd4324530.fastweixin.api.CustomAPI;
import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.CustomAccount;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.SpringContextHolder;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXMenusService;

public class WXMenusServiceImpl extends BaseServiceImpl implements IWXMenusService   {
	
	
	//private ApiConfig config;
	@Override
	public void getMenusTree(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询一级组织机构信息
		List<Map<String, Object>> menusLevels = getBaseDao().queryForList("WXMenusMapper.getMenusLevel_1", inputObject.getParams());
		List<Map<String, Object>> treeDatas = new ArrayList<Map<String,Object>>();
		convertToTreeData(menusLevels, treeDatas);
		//循环查询子集组织机构信息
		getSubMenusTree(treeDatas);
		outputObject.setBeans(treeDatas);
	}
	
	
	/**
	 * 将数据查询结果的数据转换为树状数据
	 */
	public void convertToTreeData(List<Map<String, Object>> menusLevels, List<Map<String, Object>> treeDatas){
		if(CollectionUtils.isNotEmpty(menusLevels)){
			for (Map<String, Object> menusObj : menusLevels) {
				Map<String, Object> treeData = new HashMap<String, Object>();
				treeData.put("text", menusObj.get("name"));
				treeData.put("href", "javascript:");
				treeData.put("tags", menusObj);
				treeDatas.add(treeData);
			}
		}
	}

	/**
	 * 查询组织机构子集信息
	 */
	public void getSubMenusTree(List<Map<String, Object>> treeDatas) throws Exception {
		// TODO Auto-generated method stub
		//查询子集组织机构信息
		for (Map<String, Object> treeData : treeDatas) {
			String objId = (String) ((Map<String, Object>)treeData.get("tags")).get("menuId");
			//查询是否有子集
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentId", objId);
			int total = getBaseDao().getTotalCount("WXMenusMapper.getMenuCountByParentId", params);
			if(total > 0){
				//查询子集
				List<Map<String, Object>> subDepartList = getBaseDao().queryForList("WXMenusMapper.getWXMenusListByPage", params);
				List<Map<String, Object>> subTreeDatas = new ArrayList<Map<String,Object>>();
				convertToTreeData(subDepartList, subTreeDatas);
				treeData.put("nodes", subTreeDatas);
				//循环子集
				getSubMenusTree(subTreeDatas);
			}
		}
	}
	@Override
	public void getAllWXMenus(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("WXMenusMapper.getAllWXMenus", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public void getWXMenusListByPage(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信菜单信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXMenusMapper.getWXMenusListByPage", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXMenusMapper.countAllWXMenus", inputObject.getParams());
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
	public void getWXMenusById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("WXMenusMapper.getWXMenusById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public int insertWXMenus(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信菜单是否已经存在 有code验证时放开
		
		InputObject tempInputObject = new InputObject();
		Date createTime = new Date();
		String tempId = null;
		if(inputObject.getParams().get("levels").toString().equals("2")){
			tempId = StringUtil.getSquence();
			tempInputObject.getParams().put("newsTempId", tempId);
			tempInputObject.getParams().put("type", "menu");
			tempInputObject.getParams().put("name", inputObject.getParams().get("name"));
			
			tempInputObject.getParams().put("createUser", inputObject.getParams().get("createUser"));
			tempInputObject.getParams().put("createTime", createTime);
			getBaseDao().insert("WXNewsTemplatesMapper.insert", tempInputObject.getParams());
		}
		

		inputObject.getParams().put("deleteFlag", 0);
		inputObject.getParams().put("createTime", createTime);
		inputObject.getParams().put("menuId", StringUtil.getSquence());
		inputObject.getParams().put("templateId", tempId);
		
		return getBaseDao().insert("WXMenusMapper.insertWXMenus", inputObject.getParams());
	

	}

	@Override
	public int updateWXMenus(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		
			InputObject tempInputObject = new InputObject();
			Date createTime = new Date();
		if(inputObject.getParams().get("levels").toString().equals("2")){
			tempInputObject.getParams().put("newsTempId", inputObject.getParams().get("templateId"));
			tempInputObject.getParams().put("name", inputObject.getParams().get("name"));
			tempInputObject.getParams().put("createUser", inputObject.getParams().get("createUser"));
			tempInputObject.getParams().put("createTime", createTime);
			getBaseDao().update("WXNewsTemplatesMapper.update", tempInputObject.getParams());
		}
		
			inputObject.getParams().put("updateTime", createTime);
			return getBaseDao().update("WXMenusMapper.updateWXMenus", inputObject.getParams());

	}
	@Override
	public int delWXMenus(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		InputObject tempInputObject = new InputObject();
		if(inputObject.getParams().get("levels").toString().equals("2")){
			tempInputObject.getParams().put("newsTempId", inputObject.getParams().get("templateId"));
			//getBaseDao().delete("WXNewsTemplatesMapper.deleteNewsItems", tempInputObject.getParams());
			//getBaseDao().delete("WXNewsTemplatesMapper.delete", tempInputObject.getParams());
			
		}
		
		return getBaseDao().delete("WXMenusMapper.delWXMenus", inputObject.getParams());
	}
	
	@Override
	public int logicDelWXMenus(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXMenusMapper.logicDelWXMenus", inputObject.getParams());

	}
	
	@Override
	public void synchrWXMenu(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		String site="${site}";
		String appid = inputObject.getValue("appid");
        String secret = inputObject.getValue("secret");
        String siteDomain_name = inputObject.getValue("siteDomain_name");
        
        ApiConfig config = new ApiConfig(appid, secret);
        config = SpringContextHolder.getBean("config");
        MenuAPI menuAPI = new MenuAPI(config);
        //先删除之前的菜单
        menuAPI.deleteMenu();
        Menu request = new Menu();
        
        //查询一级菜单
        List<MenuButton> mainBtns = new ArrayList<MenuButton>();
        inputObject.getParams().clear();
        inputObject.getParams().put("deleteFlag","0");
        inputObject.getParams().put("parentId","0");
        inputObject.getParams().put("orderByClause","SORT");
        List<Map<String,Object>> roots = getBaseDao().queryForList("WXMenusMapper.getAllWXMenus", inputObject.getParams());
        for(Map<String,Object> map:roots) {
        	//准备一级主菜单
            MenuButton main = new MenuButton();
            main.setKey((String) map.get("menuKey"));
            main.setName((String) map.get("name"));
            String type = (String) map.get("type");
            
            if("click".equals(type)) {
            	main.setType(MenuType.CLICK);
            } else if("view".equals(type)) {
            	main.setType(MenuType.VIEW);
            	main.setUrl((String) map.get("url"));
            }
            
            //准备子菜单 往微信服务器更新数据
            inputObject.getParams().put("parentId",(String) map.get("menuId"));
            List<Map<String,Object>> leafs = getBaseDao().queryForList("WXMenusMapper.getAllWXMenus", inputObject.getParams());
            if(leafs != null && leafs.size() > 0) {
            	List<MenuButton> leafBtns = new ArrayList<MenuButton>();
            	for(Map<String,Object> lf:leafs) {
            		MenuButton sub = new MenuButton();
            		sub.setKey((String) lf.get("menuKey"));
            		sub.setName((String) lf.get("name"));
            		String leafType = (String) lf.get("type");
                    if("click".equals(leafType)) {
                    	sub.setType(MenuType.CLICK);
                    } else if("view".equals(leafType)) {
                    	sub.setType(MenuType.VIEW);
                    	
                    	//替换映射地址操作 begin  zhaoyan
                    	String url = String.valueOf(lf.get("url"));
                    	if (null!=url && url.contains(siteDomain_name)) {
                    		
                    		lf.put("url", url.replace(siteDomain_name, site));
        					getBaseDao().update("WXMenusMapper.updateWXMenus", lf);
        				}
                    	//替换映射地址操作 end  zhaoyan
                    	
                    	
                    	
                    	
                    	//把保存在数据库的${site}这样的字符串，替换成对应的地址发布到微信服务器   begin
                    	String wxUrl=String.valueOf(lf.get("url"));
                    	if (wxUrl.contains(site)) {
                    		wxUrl = wxUrl.replace(site, siteDomain_name);
                    		sub.setUrl(wxUrl);
						}else {
							sub.setUrl((String) lf.get("url"));
						}
                    	//把保存在数据库的${site}这样的字符串，替换成对应的地址发布到微信服务器   end
//                    	sub.setUrl((String) lf.get("url"));
                    	
                    	
                    	
                    	
                    }
                    leafBtns.add(sub);
            	}
            	main.setSubButton(leafBtns);
            }
            mainBtns.add(main);
        }
        request.setButton(mainBtns);
        //创建菜单
        ResultType resultType = menuAPI.createMenu(request);
        
        outputObject.setReturnCode(String.valueOf(resultType.getCode()));
        outputObject.setReturnMessage(resultType.getDescription());
	}


	@Override
	public void checkMenuKey(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		int count = getBaseDao().getTotalCount("WXMenusMapper.checkMenuKey", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
		
	}


	@Override
	public void getWXMenuByKey(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("WXMenusMapper.getWXMenuByKey", inputObject.getParams());
		outputObject.setObject(object);

	}
	

}
