package com.lfc.wxadmincore.modules.biz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.core.util.BeanUtil;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.service.IFinanceProdService;

public class FinanceProdServiceImpl extends BaseServiceImpl implements IFinanceProdService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询理财产品管理信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("FinanceProdMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("FinanceProdMapper.countAll", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		//总数量
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
	}
	@Override
	public void getById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("FinanceProdMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("FinanceProdMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertFinanceProd(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询产品代码是否已经存在
//		int count = getBaseDao().getTotalCount("FinanceProdMapper.countByCode", inputObject.getParams());
//		if(count==0){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			String itemId = StringUtil.getSquence();
			inputObject.getParams().put("fincId", StringUtil.getSquence());
			inputObject.getParams().put("articleId", itemId);
			
			Map<String, Object> map = (Map<String, Object>)inputObject.getParams().get("itemFormMap");
			map.put("itemId", itemId);
			getBaseDao().insert("WXNewsItemsMapper.insert", map);
			
			//审核状态（-1：审核失败，0：待审核，1：审核通过），默认待审核
			//inputObject.getParams().put("authState", "0");
			int num = getBaseDao().insert("FinanceProdMapper.insert", inputObject.getParams());
			if(num > 0){
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("fincId", inputObject.getParams().get("fincId"));
				outputObject.setBean(m);
			}
			return num;
//		}else{
//			outputObject.setReturnCode("-1");
//			outputObject.setReturnMessage("产品代码已经存在，请修改!");
//			return -1;
//		}
	}

	@Override
	public int updateFinanceProd(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询产品代码是否已经存在
		/*int count = getBaseDao().getTotalCount("FinanceProdMapper.countByCode", inputObject.getParams());
		if(count==0){*/
			Map<String, Object> map = (Map<String, Object>)inputObject.getParams().get("itemFormMap");
			if(map != null){
				getBaseDao().update("WXNewsItemsMapper.update", map);
			}
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().insert("FinanceProdMapper.update", inputObject.getParams());
		/*}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("产品代码已经存在，请修改!");
			return -1;
		}	*/
	}
	
	@Override
	public int logicDelFinanceProd(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("FinanceProdMapper.logicDelete", inputObject.getParams());

	}
	@Override
	public int updateObj(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().insert("FinanceProdMapper.update", inputObject.getParams());
	}
	

}
