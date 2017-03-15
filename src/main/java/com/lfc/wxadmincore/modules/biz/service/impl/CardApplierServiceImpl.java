package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import java.util.HashMap;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.ICardApplierService ;

public class CardApplierServiceImpl extends BaseServiceImpl implements ICardApplierService   {
	
	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询信用卡申办信息表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("CardApplierMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("CardApplierMapper.countAll", inputObject.getParams());
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
	public void getListByRole(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//获得所属机构id
		String depart = "";
		String roleCode = (String)inputObject.getParams().get("roleCode");//角色编码
		if("CARD_ZG".equals(roleCode) || "LOAN_ZG".equals(roleCode)){//角色为主管级别进入
			logger.debug("---------角色编码----------"+roleCode);
			String departCode = (String)inputObject.getParams().get("departCode");//部门号
			inputObject.getParams().put("parentId", departCode);
			List<Map<String, Object>> departList = getBaseDao().queryForList("ManagerMapper.getInfoByParentId", 
					inputObject.getParams());
			for (Map<String, Object> map2 : departList) {//拼接-该用户管辖内-机构号
				if(map2.get("id").toString().contains("N")){//含N的客户经理--在二支--不含N的在一支
					inputObject.getParams().put("parentId", map2.get("id"));
					List<Map<String, Object>> departTwo = getBaseDao().queryForList("ManagerMapper.getInfoByParentId", 
							inputObject.getParams());
					for (Map<String, Object> map3 : departTwo) {
						depart += map3.get("id").toString().trim() + ",";
					}
				}else{
					depart += map2.get("id").toString().trim() + ",";
				}
			}
			depart = depart.substring(0,depart.lastIndexOf(","));
			logger.debug("---------所属机构----------"+depart);
			inputObject.getParams().put("depart", depart.split(","));
		}

		logger.debug(">>>>>>>>>>>>>>筛选结构>>>>>>>>>>>>>>>"+inputObject.getParams().get("depart"));
		//分页查询信用卡申办信息表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("CardApplierMapper.getListByRole", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("CardApplierMapper.countAllByRole", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("CardApplierMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("CardApplierMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertCardApplier(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询信用卡申办信息表是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("CardApplierMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("applierId", StringUtil.getSquence());
			return getBaseDao().insert("CardApplierMapper.insert", inputObject.getParams());
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("信用卡申办信息表已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateCardApplier(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询信用卡申办信息表是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("CardApplierMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("CardApplierMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("信用卡申办信息表已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteCardApplier(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("CardApplierMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteCardApplier(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("CardApplierMapper.logicDelete", inputObject.getParams());

	}
	@Override
	public void getListByRoleAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询的时候加上角色限制  beg
		// 获得所属机构id
		String depart = "";
		String roleCode = (String) inputObject.getParams().get("roleCode");// 角色编码
		if ("CARD_ZG".equals(roleCode) || "LOAN_ZG".equals(roleCode)) {// 角色为主管级别进入
			logger.debug("---------角色编码----------" + roleCode);
			String departCode = (String) inputObject.getParams().get("departCode");// 部门号
			inputObject.getParams().put("parentId", departCode);
			List<Map<String, Object>> departList = getBaseDao().queryForList("ManagerMapper.getInfoByParentId",
					inputObject.getParams());
			for (Map<String, Object> map2 : departList) {// 拼接-该用户管辖内-机构号
				if (map2.get("id").toString().contains("N")) {// 含N的客户经理--在二支--不含N的在一支
					inputObject.getParams().put("parentId", map2.get("id"));
					List<Map<String, Object>> departTwo = getBaseDao().queryForList("ManagerMapper.getInfoByParentId",
							inputObject.getParams());
					for (Map<String, Object> map3 : departTwo) {
						depart += map3.get("id").toString().trim() + ",";
					}
				} else {
					depart += map2.get("id").toString().trim() + ",";
				}
			}
			depart = depart.substring(0, depart.lastIndexOf(","));
			inputObject.getParams().put("depart", depart.split(","));
		}
		//查询的时候加上角色限制  end

		// 查询信用卡申办信息表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("CardApplierMapper.getListByRoleAll",
				inputObject.getParams());
		outputObject.setBeans(list);

	}
	
	@Override
	public void getListByCardInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// 查询信用卡申办信息表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("CardApplierMapper.getListByCardInfo",
				inputObject.getParams());
		outputObject.setBeans(list);

	}
}
