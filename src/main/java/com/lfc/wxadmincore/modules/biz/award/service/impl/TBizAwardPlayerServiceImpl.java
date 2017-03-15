package com.lfc.wxadmincore.modules.biz.award.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.github.sd4324530.fastweixin.util.CollectionUtil;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.award.service.ITBizAwardPlayerService ;

public class TBizAwardPlayerServiceImpl extends BaseServiceImpl implements ITBizAwardPlayerService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询抽奖人员资格信息表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("TBizAwardPlayerMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("TBizAwardPlayerMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("TBizAwardPlayerMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("TBizAwardPlayerMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertTBizAwardPlayer(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("id",StringUtil.getSquence());
			return getBaseDao().insert("TBizAwardPlayerMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateTBizAwardPlayer(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询抽奖人员资格信息表是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("TBizAwardPlayerMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("TBizAwardPlayerMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("抽奖人员资格信息表已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteTBizAwardPlayer(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("TBizAwardPlayerMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteTBizAwardPlayer(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("TBizAwardPlayerMapper.logicDelete", inputObject.getParams());

	}
	
	
	@Override
	public void getSeriesNumList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询抽奖人员资格信息表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("TBizAwardPlayerMapper.getSeriesNumList", inputObject.getParams());
		outputObject.setBeans(list);
	}
	@Override
	public void importAwardPlayer(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> awardList= (List<Map<String, Object>>) inputObject.getParams().get("awardList"); 
		if(CollectionUtil.isNotEmpty(awardList)){
			for (int i = 0; i < awardList.size(); i++) {
				awardList.get(i).put("id",StringUtil.getSquence());
				getBaseDao().insert("TBizAwardPlayerMapper.insert", awardList.get(i));
			}
		}

	}

}
