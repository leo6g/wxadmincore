package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IArticleRemarkService;

public class ArticleRemarkServiceImpl extends BaseServiceImpl implements IArticleRemarkService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询文章评论信息信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("ArticleRemarkMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("ArticleRemarkMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("ArticleRemarkMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("ArticleRemarkMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXArticleRemark(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("remarkId",StringUtil.getSquence());
			return getBaseDao().insert("ArticleRemarkMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateWXArticleRemark(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			//同步积分表
			inputObject.getParams().put("ficId",StringUtil.getSquence());
			getBaseDao().insert("FicMoneyMapper.insert", inputObject.getParams());
			//修改评论信息
			inputObject.getParams().put("authTime", new Date());
			return getBaseDao().update("ArticleRemarkMapper.update", inputObject.getParams());
	}
	@Override
	public int deleteWXArticleRemark(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("ArticleRemarkMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXArticleRemark(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("ArticleRemarkMapper.logicDelete", inputObject.getParams());

	}
	

}
