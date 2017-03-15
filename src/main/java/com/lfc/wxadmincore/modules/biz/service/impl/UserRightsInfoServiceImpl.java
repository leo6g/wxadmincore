package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IUserRightsInfoService ;

public class UserRightsInfoServiceImpl extends BaseServiceImpl implements IUserRightsInfoService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询客户权益信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("UserRightsInfoMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("UserRightsInfoMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("UserRightsInfoMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("UserRightsInfoMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertUserRightsInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("id",StringUtil.getSquence());
			return getBaseDao().insert("UserRightsInfoMapper.insert", inputObject.getParams());
	}
	
	@Override
	public void batchInsertUserRightsInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//获取权益参数
		Integer daiJia = (Integer) inputObject.getParams().get("daiJia");
		Integer guiBin = (Integer) inputObject.getParams().get("guiBin");
		Integer diDi = (Integer) inputObject.getParams().get("diDi");
		Integer jiChang = (Integer) inputObject.getParams().get("jiChang");
		String customerLevel = (String) inputObject.getParams().get("customerLevel");
		String createUser = (String) inputObject.getParams().get("createUser");
		//查询对应的用户
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLevel", customerLevel);
		List<Map<String, Object>> userList = getBaseDao().queryForList("WXUserMapper.getAll", map);
		//设置批量删除参数
		Map<String, Object> map1 = new HashMap<String, Object>();//代驾
		Map<String, Object> map2 = new HashMap<String, Object>();//贵宾
		Map<String, Object> map3 = new HashMap<String, Object>();//滴滴
		Map<String, Object> map4 = new HashMap<String, Object>();//机场接送
		
		for (Map<String, Object> usermap : userList) {
			map1.clear();
			map2.clear();
			map3.clear();
			map4.clear();
			
			map1.put("id", StringUtil.getSquence());
			map1.put("wxUserId", usermap.get("wxUserId"));
			map1.put("serviceCode", "daijia");
			map1.put("customerLevel", customerLevel);
			map1.put("initCnt", daiJia);
			map1.put("usedCnt", 0);
			map1.put("remainCnt", 0);
			map1.put("deleteFlag", "0");
			map1.put("createUser", createUser);
			map1.put("createTime", new Date());
			
			map2.put("id", StringUtil.getSquence());
			map2.put("wxUserId", usermap.get("wxUserId"));
			map2.put("serviceCode", "guibin");
			map2.put("customerLevel", customerLevel);
			map2.put("initCnt", guiBin);
			map2.put("usedCnt", 0);
			map2.put("remainCnt", 0);
			map2.put("deleteFlag", "0");
			map2.put("createUser", createUser);
			map2.put("createTime", new Date());
			
			map3.put("id", StringUtil.getSquence());
			map3.put("wxUserId", usermap.get("wxUserId"));
			map3.put("serviceCode", "didi");
			map3.put("customerLevel", customerLevel);
			map3.put("initCnt", diDi);
			map3.put("usedCnt", 0);
			map3.put("remainCnt", 0);
			map3.put("deleteFlag", "0");
			map3.put("createUser", createUser);
			map3.put("createTime", new Date());
			
			map4.put("id", StringUtil.getSquence());
			map4.put("wxUserId", usermap.get("wxUserId"));
			map4.put("serviceCode", "jichang");
			map4.put("customerLevel", customerLevel);
			map4.put("initCnt", jiChang);
			map4.put("usedCnt", 0);
			map4.put("remainCnt", 0);
			map4.put("deleteFlag", "0");
			map4.put("createUser", createUser);
			map4.put("createTime", new Date());
				
			list.add(map1);
			list.add(map2);
			list.add(map3);
			list.add(map4);
		}
		
		getBaseDao().batchInsert("UserRightsInfoMapper.insert", list);
	}
	
	
	@Override
	public void batchupdateUserRightsInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> deleteList = new ArrayList<Map<String,Object>>();
		//获取权益参数
		Integer daiJia = (Integer) inputObject.getParams().get("daiJia");
		Integer guiBin = (Integer) inputObject.getParams().get("guiBin");
		Integer diDi = (Integer) inputObject.getParams().get("diDi");
		Integer jiChang = (Integer) inputObject.getParams().get("jiChang");
		String customerLevel = (String) inputObject.getParams().get("customerLevel");
		String createUser = (String) inputObject.getParams().get("createUser");
		//查询对应的用户
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLevel", customerLevel);
		List<Map<String, Object>> userList = getBaseDao().queryForList("WXUserMapper.getAll", map);
		//设置批量删除参数
		Map<String, Object> deleteMap = new HashMap<String, Object>();//批量删除wxUserId
		Map<String, Object> map1 = new HashMap<String, Object>();//代驾
		Map<String, Object> map2 = new HashMap<String, Object>();//贵宾
		Map<String, Object> map3 = new HashMap<String, Object>();//滴滴
		Map<String, Object> map4 = new HashMap<String, Object>();//机场接送
		
		for (Map<String, Object> usermap : userList) {
			map1.clear();
			map2.clear();
			map3.clear();
			map4.clear();
			
			deleteMap.put("wxUserId", usermap.get("wxUserId"));
			
			map1.put("id", StringUtil.getSquence());
			map1.put("wxUserId", usermap.get("wxUserId"));
			map1.put("serviceCode", "daijia");
			map1.put("customerLevel", customerLevel);
			map1.put("initCnt", daiJia);
			map1.put("usedCnt", 0);
			map1.put("remainCnt", 0);
			map1.put("deleteFlag", "0");
			map1.put("createUser", createUser);
			map1.put("createTime", new Date());
			
			map2.put("id", StringUtil.getSquence());
			map2.put("wxUserId", usermap.get("wxUserId"));
			map2.put("serviceCode", "guibin");
			map2.put("customerLevel", customerLevel);
			map2.put("initCnt", guiBin);
			map2.put("usedCnt", 0);
			map2.put("remainCnt", 0);
			map2.put("deleteFlag", "0");
			map2.put("createUser", createUser);
			map2.put("createTime", new Date());
			
			map3.put("id", StringUtil.getSquence());
			map3.put("wxUserId", usermap.get("wxUserId"));
			map3.put("serviceCode", "didi");
			map3.put("customerLevel", customerLevel);
			map3.put("initCnt", diDi);
			map3.put("usedCnt", 0);
			map3.put("remainCnt", 0);
			map3.put("deleteFlag", "0");
			map3.put("createUser", createUser);
			map3.put("createTime", new Date());
			
			map4.put("id", StringUtil.getSquence());
			map4.put("wxUserId", usermap.get("wxUserId"));
			map4.put("serviceCode", "jichang");
			map4.put("customerLevel", customerLevel);
			map4.put("initCnt", jiChang);
			map4.put("usedCnt", 0);
			map4.put("remainCnt", 0);
			map4.put("deleteFlag", "0");
			map4.put("createUser", createUser);
			map4.put("createTime", new Date());
			
			list.add(map1);
			list.add(map2);
			list.add(map3);
			list.add(map4);
			
			deleteList.add(deleteMap);
		}
		
		getBaseDao().batchInsert("UserRightsInfoMapper.deleteforWXUserId", deleteList);
		
		getBaseDao().batchInsert("UserRightsInfoMapper.insert", list);
	}

	@Override
	public int updateUserRightsInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询客户权益是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("UserRightsInfoMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("UserRightsInfoMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("客户权益已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteUserRightsInfo(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("UserRightsInfoMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteUserRightsInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("UserRightsInfoMapper.logicDelete", inputObject.getParams());

	}
	

}
