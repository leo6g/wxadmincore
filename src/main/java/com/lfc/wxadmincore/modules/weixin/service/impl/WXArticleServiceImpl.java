package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.DateUtil;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXArticleService ;

public class WXArticleServiceImpl extends BaseServiceImpl implements IWXArticleService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信文章内容信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXArticleMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXArticleMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("WXArticleMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("WXArticleMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXArticle(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信文章内容是否已经存在 有code验证时放开
		int count  = getBaseDao().getTotalCount("WXArticleMapper.countBySome", inputObject.getParams());
		if(count==0){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("newsTempId", StringUtil.getSquence());
			//设置发送状态为未发送
			inputObject.getParams().put("sendState", "F");
			/*String publishDate =(String)inputObject.getParams().get("publishDate");
			if(publishDate != null && !("".equals(publishDate))){
				inputObject.getParams().put("publishDate", DateUtil.string2Date2(publishDate));
			}*/
			int num = getBaseDao().insert("WXArticleMapper.insert", inputObject.getParams());
			//获取新增模板主键
			if(num > 0){
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("newsTempId", inputObject.getParams().get("newsTempId"));
				outputObject.setBean(m);
			}
			return num;
		}else{
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("微信文章内容已经存在，请修改!");
			return -1;
		}
	}

	@Override
	public int updateWXArticle(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		return getBaseDao().update("WXArticleMapper.update", inputObject.getParams());
		
	}
	@Override
	public int deleteWXArticle(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("WXArticleMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXArticle(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXArticleMapper.logicDelete", inputObject.getParams());

	}
	

}
