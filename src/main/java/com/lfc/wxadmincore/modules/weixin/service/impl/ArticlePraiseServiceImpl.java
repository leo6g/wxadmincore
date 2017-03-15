package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IArticlePraiseService;

public class ArticlePraiseServiceImpl extends BaseServiceImpl implements IArticlePraiseService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询文章点赞记录信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("ArticlePraiseMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("ArticlePraiseMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("ArticlePraiseMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("ArticlePraiseMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXArticlePraise(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			//同步积分表
			inputObject.getParams().put("ficId",StringUtil.getSquence());
			getBaseDao().insert("FicMoneyMapper.insert", inputObject.getParams());
			
			//inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("id",StringUtil.getSquence());
			return getBaseDao().insert("ArticlePraiseMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateWXArticlePraise(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询文章点赞记录是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("ArticlePraiseMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("ArticlePraiseMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("文章点赞记录已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteWXArticlePraise(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("ArticlePraiseMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXArticlePraise(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("ArticlePraiseMapper.logicDelete", inputObject.getParams());

	}
	

}
