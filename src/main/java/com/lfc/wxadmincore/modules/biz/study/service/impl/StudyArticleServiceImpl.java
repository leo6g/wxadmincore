package com.lfc.wxadmincore.modules.biz.study.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.biz.study.service.IStudyArticleService;

public class StudyArticleServiceImpl extends BaseServiceImpl implements IStudyArticleService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询邮学堂文章表信息
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("StudyArticleMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("StudyArticleMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("StudyArticleMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("StudyArticleMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertStudyArticle(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("articleId",StringUtil.getSquence());
			return getBaseDao().insert("StudyArticleMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateStudyArticle(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询邮学堂文章表是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("StudyArticleMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("StudyArticleMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("邮学堂文章表已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteStudyArticle(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("StudyArticleMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteStudyArticle(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("StudyArticleMapper.logicDelete", inputObject.getParams());

	}
	

}
