package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXKeywordsResponseService;

public class WXKeywordsResponseServiceImpl extends BaseServiceImpl implements IWXKeywordsResponseService   {

	public void getList(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		int count=0;
		Integer limit = (Integer) inputObject.getParams().get("limit");
		int totalPages = 1;
		String contextPath = (String) inputObject.getParams()
				.get("contextPath");
		String queryKey = (String) inputObject.getParams().get("queryKey");
		List<Map<String, Object>> list = getBaseDao().queryForList(
				"WXKeywordResponseMapper.getRuleList", inputObject.getParams());
		//querykey为关键字时
		boolean fromkey=false;
		if(queryKey!=null&&!"".equals(queryKey)){
			List<Map<String, Object>> listkeyend=new ArrayList<>();
			Map<String, Object> mapQK = new HashMap<>();
			mapQK.put("queryKey", queryKey);
			List<Map<String, Object>> listfromkey = getBaseDao().queryForList(
					"WXKeywordResponseMapper.getByKey", mapQK);
			if (listfromkey.size() > 0) {// queryKey与关键字有匹配信息
			this.getByQueryKey(listkeyend,listfromkey,list);
			count=list.size();
			fromkey=true;
			}
		}
		if(fromkey==false){
			count = getBaseDao().getTotalCount(
					"WXKeywordResponseMapper.countAll", inputObject.getParams());
			totalPages=(count + limit - 1) / limit;
		}
		// 封装需要的返回类型
		List<Map<String, Object>> listResult = new ArrayList<>();
		for (Map<String, Object> map : list) {
			String ruleId = (String) map.get("ruleId");
			InputObject inputObjectKey = new InputObject();
			inputObjectKey.getParams().put("ruleId", ruleId);
			List<Map<String, Object>> keyWords = getBaseDao().queryForList(
					"WXKeywordResponseMapper.getKeyInfo",
					inputObjectKey.getParams());
			List<Map<String, Object>> keywords = new ArrayList<>();
			for (Map<String, Object> map0 : keyWords) {
				keywords.add(map0);
			}
			map.put("txtNum", 0);
			map.put("imageNum", 0);
			map.put("voiceNum", 0);
			map.put("videoNum", 0);
			map.put("articleNum", 0);
			String type = (String) map.get("responseType");
			if ("text".equals(type)) {
				map.put("txtNum", 1);
			} else if ("image".equals(type)) {
				map.put("imageNum", 1);
			} else if ("voice".equals(type)) {
				map.put("voiceNum", 1);
			} else if ("video".equals(type)) {
				map.put("videoNum", 1);
			} else if ("article".equals(type)) {
				map.put("articleNum", 1);
			} else {

			}
			map.put("keywords", keywords);
			this.getByMediaId(map,contextPath);// 根据mediaId查询回复详情
			listResult.add(map);
		}
		outputObject.setBeans(listResult);
		//分页
		Map<String, Object> beanMap = new HashMap<String, Object>();
		// 总数量
		beanMap.put("count", count);
		// 当前页码
		beanMap.put("currentPage", inputObject.getParams().get("pageNumber"));
		// 总页数
		beanMap.put("totalPages", totalPages);
		outputObject.setBean(beanMap);
	}
	// 根据mediaId查询回复详情
	private void getByMediaId(Map<String, Object> map, String contextPath){
		  String mediaId = (String) map.get("materialId");
			InputObject inputObjectMedaId = new InputObject();
			inputObjectMedaId.getParams().put("mediaId", mediaId);
		if ("article".equals(map.get("responseType"))) {
			// 根据微信图文素材MediaId，查询微信文章列表
			List<Map<String, Object>> articles = getBaseDao().queryForList(
					"WXArticleMaterialMapper.getArticlesByWxMediaId",
					inputObjectMedaId.getParams());
			if (CollectionUtils.isNotEmpty(articles)) {
				map.put("articleContent", articles);
			}
		} else if (!"text".equals(map.get("responseType"))) {
			// 图片、音频、视频根据mediaId查询
			Map<String, Object> maptuyinshi = (Map<String, Object>) getBaseDao()
					.queryForObject("WXMaterialMapper.getByMediaId",
							inputObjectMedaId.getParams());
			if (maptuyinshi != null) {
				String localUrl = null;// 拼接后的新路径
				String localUrl0 = (String) maptuyinshi.get("localUrl");// 原路径
				String category = (String) maptuyinshi.get("category");// 媒体类型
				if ("image".equals(category)) {
					if (localUrl0 == null) {
						localUrl = contextPath
								+ "/viewImage/viewImage?imgPath=defaultf29b7fcd44314b7b7540e45d5f37a.jpg";
					} else {
						localUrl = contextPath
								+ "/viewImage/viewImage?imgPath=material/"
								+ localUrl0;
					}
				}else if("voice".equals(category)){
					localUrl=contextPath+"/viewVideo/viewVideo?videoPath=material/"+ localUrl0 + "&videoName=" + localUrl0;
				}else if("video".equals(category)){
				localUrl=contextPath+"/viewVideo/viewVideo?videoPath=material/"+localUrl0 + "&videoName="+localUrl0;
				}
				map.put("localUrl", localUrl);
			}
		}
	}
	private void getByQueryKey(List<Map<String, Object>> listkeyend, List<Map<String, Object>> listfromkey, List<Map<String, Object>> list){
				if (list.size() > 0) {// queryKey与规则名也有匹配信息
					for (Map<String, Object> mapqueryKey : listfromkey) {
						int isAdd = 1;
						for (Map<String, Object> mapqueryKeyRule : list) {
							if (mapqueryKey.get("ruleId").equals(// 二者查出的信息间ruleID去重
									mapqueryKeyRule.get("ruleId"))) {
								isAdd = 0;
							}
						}
						if (isAdd == 1) {
							listkeyend.add(mapqueryKey);
						}
					}
					list.addAll(listkeyend);
				} else {
					list.addAll(listfromkey);
				}
			}
	
		@Override
	public void getById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			Object object=getBaseDao().queryForObject("WXKeywordResponseMapper.getById", inputObject.getParams());
			/*Map<String,Object> map=new HashMap<>();
			for(Map<String,Object> map0:list){
				Map<String,Object> mapTmp=new HashMap<>();
				mapTmp.put("keywordId", map0.get("keywordId"));
				mapTmp.put("keyword", map0.get("keyword"));
				mapTmp.put("isAllmatch", map0.get("isAllmatch"));
				map.put("ruleId", map0.get("ruleId"));
				map.put("ruleName", map0.get("ruleName"));
				map.put("responseType", map0.get("responseType"));
				map.put("txtContent", map0.get("txtContent"));
				map.put("materialId", map0.get("materialId"));
				map.put("keyWord",mapTmp);
		}
		*/outputObject.setObject(object);
	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("WXKeywordResponseMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	}
//插入关键字
	private void insertKeyWords(String word,String ruleId) {
		if(word!=null &&!"".equals(word)){
		String[] words = word.split(",");
		for (String keyword : words) {// 存入关键字
			int isAllmatch = 0;
			String[] keys = keyword.split("_");
			if ("1".equals(keys[1])) {
				isAllmatch = 1;
			}
			InputObject objectWords = new InputObject();
			objectWords.getParams().put("keywordId", StringUtil.getSquence());
			objectWords.getParams().put("keyword", keys[0]);
			objectWords.getParams().put("isAllmatch", isAllmatch);
			objectWords.getParams().put("ruleId", ruleId);
			getBaseDao().insert("WXKeywordResponseMapper.insertKeywords",
					objectWords.getParams());
		}
	  }
	}
	@Override
	public int insertWXKeywordsResp(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		    String ruleId=StringUtil.getSquence();
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("ruleId", ruleId);
			String word=(String) inputObject.getParams().get("words");
			insertKeyWords(word,ruleId);
			return getBaseDao().insert("WXKeywordResponseMapper.insertRule", inputObject.getParams());
	}


	@Override
	public int updateWXKeywordsResp(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String ruleId=(String)inputObject.getParams().get("ruleId");
		String word=(String) inputObject.getParams().get("words");
		//删除该规则的关键字
		Map<String,Object> map=new HashMap<>();
		map.put("ruleId",ruleId);
		getBaseDao().delete("WXKeywordResponseMapper.deleteKeywords",map);
		//重新插入
		insertKeyWords(word,ruleId);
		//更新规则 
		return getBaseDao().update("WXKeywordResponseMapper.updateRule", inputObject.getParams());
	}

	@Override
	public  int deleteRule(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		getBaseDao().delete("WXKeywordResponseMapper.deleteRule", inputObject.getParams());
		return getBaseDao().delete("WXKeywordResponseMapper.deleteKeywords", inputObject.getParams());
	}
	
	public void getByNews(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("WXKeywordResponseMapper.getByNews", inputObject.getParams());
		outputObject.setBeans(list);
	}
	
}
