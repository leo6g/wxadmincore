package com.lfc.wxadmincore.modules.biz.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IMerchantService ;

public class MerchantServiceImpl extends BaseServiceImpl implements IMerchantService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询特惠商户信息信息
		List<Map<String, Object>> list = getBaseDao().queryForList("MerchantMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("MerchantMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("MerchantMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getDetail(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("MerchantMapper.getDetailforView", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("MerchantMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertMerchant(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询特惠商户信息是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("MerchantMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			return getBaseDao().insert("MerchantMapper.insert", inputObject.getParams());
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("特惠商户信息已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateMerchant(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询特惠商户信息是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("MerchantMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("MerchantMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("特惠商户信息已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteMerchant(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("MerchantMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteMerchant(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("MerchantMapper.logicDelete", inputObject.getParams());

	}
	
	@Override
	public void getMerchantType(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("MerchantMapper.getMerchantType", inputObject.getParams());
		outputObject.setBeans(list);
	}
	@Override
	public void getMerchantState(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("MerchantMapper.getMerchantState", inputObject.getParams());
		outputObject.setBeans(list);

	}	
	@Override
	public void importMerchant(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> merchantList= (List<Map<String, Object>>) inputObject.getParams().get("merchantList"); 
		//List<Map<String,Object>> listAll = getBaseDao().queryForList("MerchantMapper.getAll", inputObject.getParams());
		for (int i = 0; i < merchantList.size(); i++) {
			merchantList.get(i).put("deleteFlag", 0);
			merchantList.get(i).put("createTime", new Date());
			getBaseDao().insert("MerchantMapper.insert", merchantList.get(i));
			/*for (int j = 0; j < listAll.size(); j++) {
				if(merchantList.get(i).get("code").equals(listAll.get(j).get("code"))){
					inputObject.getParams().put("updateTime", new Date());
					getBaseDao().update("MerchantMapper.updateByCode", merchantList.get(i));
					break;
				}
				if(j==listAll.size()-1&&!merchantList.get(i).get("code").equals(listAll.get(j).get("code"))){
					merchantList.get(i).put("deleteFlag", 0);
					merchantList.get(i).put("createTime", new Date());
					getBaseDao().insert("MerchantMapper.insert", merchantList.get(i));
				}
			}*/
		}
	}
	@Override 
	public void getByDepartName(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("MerchantMapper.getByDepartName", inputObject.getParams());
		outputObject.setObject(object);

	}
}
