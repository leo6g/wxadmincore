package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.sd4324530.fastweixin.api.CustomAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.CustomAccount;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.MD5Util;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.ICustomAccountService ;

public class CustomAccountServiceImpl extends BaseServiceImpl implements ICustomAccountService   {


	@Resource
	private ApiConfig config;
	
	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信客服管理信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("CustomAccountMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("CustomAccountMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("CustomAccountMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("CustomAccountMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertCustomAccount(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			int flag=0;
		   /**
			 * 代码逻辑说明:先通过调用微信提供的添加客服的添加方法，再插入数据库
			 * */
			//微信添加客户帐号    fastweixin.jar包
			//ApiConfig ApiConfig = new ApiConfig(String.valueOf(inputObject.getParams().get("appId")),String.valueOf(inputObject.getParams().get("secret")));
			CustomAPI customAPI  = new CustomAPI(config);
			CustomAccount customAccount =new CustomAccount();
			customAccount.setAccountName(inputObject.getParams().get("account").toString());//客户帐号
			customAccount.setNickName(inputObject.getParams().get("nickName").toString());//客户昵称
			customAccount.setPassword(MD5Util.getMD5(inputObject.getParams().get("pwd").toString()));//客服密码
			ResultType resultType = customAPI.addCustomAccount(customAccount);
			System.out.println(resultType+"----------------<><>");
			if ("65400".equals(resultType.getCode().toString())) {
				 inputObject.getParams().put("deleteFlag", 0);
				 inputObject.getParams().put("createTime", new Date());
				 inputObject.getParams().put("id",StringUtil.getSquence());
				 flag = getBaseDao().insert("CustomAccountMapper.insert", inputObject.getParams());
				 if (flag<0) {
					 outputObject.setReturnCode("-1");
				}
			}
			return flag;
	}

	@Override
	public int updateCustomAccount(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			int flag=0;
//			ApiConfig ApiConfig = new ApiConfig(String.valueOf(inputObject.getParams().get("appId")),String.valueOf(inputObject.getParams().get("secret")));
//			CustomAPI customAPI  = new CustomAPI(ApiConfig);
//			CustomAccount customAccount =new CustomAccount();
//			customAccount.setAccountName(inputObject.getParams().get("account").toString());//客户帐号
//			customAccount.setNickName(inputObject.getParams().get("nickName").toString());//客户昵称
//			customAccount.setPassword(MD5Util.getMD5(inputObject.getParams().get("pwd").toString()));//客服密码
//			ResultType resultType = customAPI.updateCustomAccount(customAccount);
			
			if (true) {
				flag  = getBaseDao().update("CustomAccountMapper.update", inputObject.getParams());
				if (flag<0) {
					 outputObject.setReturnCode("-1");
				}
			}
			return flag;

	}
	@Override
	public int deleteCustomAccount(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		int flag=0;
//		ApiConfig ApiConfig = new ApiConfig(String.valueOf(inputObject.getParams().get("appId")),String.valueOf(inputObject.getParams().get("secret")));
//		CustomAPI customAPI  = new CustomAPI(ApiConfig);
//		ResultType resultType = customAPI.deleteCustomAccount(inputObject.getParams().get("account").toString());
		if (true) {
			flag = getBaseDao().delete("CustomAccountMapper.delete", inputObject.getParams());
			if (flag<0) {
				outputObject.setReturnCode("-1");
			}
		}
		
		return flag;
	}
	
	@Override
	public int logicDeleteCustomAccount(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("CustomAccountMapper.logicDelete", inputObject.getParams());

	}
	

}
