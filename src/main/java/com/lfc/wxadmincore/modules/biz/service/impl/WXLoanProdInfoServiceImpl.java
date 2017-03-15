package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IWXLoanProdInfoService;


public class WXLoanProdInfoServiceImpl extends BaseServiceImpl implements IWXLoanProdInfoService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信贷款信息信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXLoanProdInfoMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXLoanProdInfoMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("WXLoanProdInfoMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("WXLoanProdInfoMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXLoanProdInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信贷款信息是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXLoanProdInfoMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("prodId",StringUtil.getSquence());
			int count = getBaseDao().insert("WXLoanProdInfoMapper.insert", inputObject.getParams());
			if(count > 0){
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("prodId", inputObject.getParams().get("prodId"));
				outputObject.setBean(m);
			}
			return count;
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("微信贷款信息已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateWXLoanProdInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信贷款信息是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXLoanProdInfoMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("WXLoanProdInfoMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("微信贷款信息已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteWXLoanProdInfo(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("WXLoanProdInfoMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXLoanProdInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXLoanProdInfoMapper.logicDelete", inputObject.getParams());

	}
	

}
