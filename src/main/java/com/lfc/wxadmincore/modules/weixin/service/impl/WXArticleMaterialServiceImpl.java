package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.sd4324530.fastweixin.api.MaterialAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Article;
import com.github.sd4324530.fastweixin.api.entity.ArticleUpdate;
import com.github.sd4324530.fastweixin.api.enums.MaterialType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.DownloadMaterialResponse;
import com.github.sd4324530.fastweixin.api.response.GetMaterialListResponse;
import com.github.sd4324530.fastweixin.api.response.UploadMaterialResponse;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.DateUtil;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXArticleMaterialService;

public class WXArticleMaterialServiceImpl extends BaseServiceImpl implements IWXArticleMaterialService   {

	
	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信素材表信息
		//图文素材标识->news
		inputObject.getParams().put("category", "news");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXMaterialMapper.getList", inputObject.getParams());
		if(CollectionUtils.isNotEmpty(list)){
			for (Map<String, Object> map : list) {
				//根据素材ID,查询微信文章列表
				inputObject.getParams().put("materialId", map.get("materialId").toString());
				List<Map<String, Object>> articles = getBaseDao().queryForList("WXArticleMaterialMapper.getByMaterialId", inputObject.getParams());
				map.put("articles", articles);
			}
		}
		outputObject.setBeans(list);
		//查询总数量
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("category", "news");
		params.put("keyword", inputObject.getParams().get("keyword"));
		int count = getBaseDao().getTotalCount("WXMaterialMapper.countAll", params);
		Map<String, Object> beanMap = new HashMap<String, Object>();
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
	}
	
	
	
	@Override
	public void getById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//根据ID,查询图文素材信息
		Map<String, Object> mtlObj = (Map<String, Object>) getBaseDao().queryForObject("WXMaterialMapper.getById", inputObject.getParams());
		//根据素材ID,查询微信文章列表
		List<Map<String, Object>> articles = getBaseDao().queryForList("WXArticleMaterialMapper.getByMaterialId", inputObject.getParams());
		if(mtlObj!=null && CollectionUtils.isNotEmpty(articles)){
			mtlObj.put("articles", articles);
		}
		outputObject.setObject(mtlObj);
	}
	
	
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//图文素材标识->news
		inputObject.getParams().put("category", "news");
		List<Map<String,Object>> list = getBaseDao().queryForList("WXMaterialMapper.getAll", inputObject.getParams());
		if(CollectionUtils.isNotEmpty(list)){
			for (Map<String, Object> map : list) {
				//根据素材ID,查询微信文章列表
				inputObject.getParams().put("materialId", map.get("materialId").toString());
				List<Map<String, Object>> articles = getBaseDao().queryForList("WXArticleMaterialMapper.getByMaterialId", inputObject.getParams());
				map.put("articles", articles);
			}
		}
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXMaterialMapper.countAll", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		//总数量
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
	}
	
	
	@Override
	public int insertWXMaterial(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//获取微信文章列表数据，首先将图文信息上传微信服务器
		List<Map<String, Object>> articles = (List<Map<String, Object>>) inputObject.getParams().get("articles");
		//微信公众号appId和secret
		String appId = inputObject.getParams().get("appId").toString();
		String secret = inputObject.getParams().get("secret").toString();
		ApiConfig config = new ApiConfig(appId, secret);
		MaterialAPI materialAPI = new MaterialAPI(config);
		//图文素材MediaId
		String mediaId = null;
		UploadMaterialResponse response = null;
		if(CollectionUtils.isNotEmpty(articles)){
			List<Article> artList = new ArrayList<Article>();
			for (Map<String, Object> map : articles) {
				//默认图文消息不展示封面图片
				Article article = new Article(map.get("thumbMediaId").toString(), map.get("author").toString(), map.get("title").toString(), map.get("sourceUrl").toString(), map.get("content").toString(), map.get("digest").toString(), 0);
				artList.add(article);
			}
			response = materialAPI.uploadMaterialNews(artList);
			mediaId = response.getMediaId();
		}else{
			outputObject.setReturnCode("-9999");
        	outputObject.setReturnMessage("新增图文素材失败，原因：文章素材参数为空.");
			return 0;
		}
		//图文素材微信服务器上传成功，开始保存本地数据库中
		//先定义微信素材ID主键，用来保存微信文章表的素材ID
		String materialId = StringUtil.getSquence();
        if(StringUtils.isNotEmpty(mediaId)){
        	//图文素材上传成功后，需要再下载该素材内容，要获取文章的url地址
        	DownloadMaterialResponse downResponse = materialAPI.downloadMaterial(mediaId, MaterialType.NEWS);
        	if(downResponse!=null && CollectionUtils.isNotEmpty(downResponse.getNews())){
        		//首先，保存微信文章表数据
        		if(CollectionUtils.isNotEmpty(articles)){
        			int indexNum = 0;
        			for (Map<String, Object> map : articles) {
        				String articleId = StringUtil.getSquence();
        				map.put("articleId", articleId);
        				map.put("materialId", materialId);
        				map.put("indexNum", indexNum);
        				map.put("remoteUrl", downResponse.getNews().get(indexNum).getUrl());
        				indexNum++;
        				int flag =  getBaseDao().insert("WXArticleMaterialMapper.insert", map);
        			}
        		}
        		//其次，保存微信素材表数据
        		inputObject.getParams().put("materialId", materialId);
        		inputObject.getParams().put("name", "图文素材");
        		inputObject.getParams().put("mediaId", mediaId);
        		inputObject.getParams().put("category", "news");
        		inputObject.getParams().put("createTime", new Date());
        		return getBaseDao().insert("WXMaterialMapper.insert", inputObject.getParams());
        	}else{
        		outputObject.setReturnCode("-9999");
            	outputObject.setReturnMessage("新增图文素材失败，原因：微信服务器图文素材下载失败."+downResponse.getErrmsg());
        	}
        }else{
        	outputObject.setReturnCode("-9999");
        	outputObject.setReturnMessage("新增图文素材失败，原因："+response.getErrmsg());
        }
        return 0;
	}

	
	@Override
	public int updateWXMaterial(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//获取要修改的微信文章数据，首先将图文信息上传微信服务器
		List<Map<String, Object>> articles = (List<Map<String, Object>>) inputObject.getParams().get("updatls");
		//微信公众号appId和secret
		String appId = inputObject.getParams().get("appId").toString();
		String secret = inputObject.getParams().get("secret").toString();
		ApiConfig config = new ApiConfig(appId, secret);
		MaterialAPI materialAPI = new MaterialAPI(config);
		boolean updSuccFlag = true;
		StringBuffer updStr = new StringBuffer();
		if(CollectionUtils.isNotEmpty(articles)){
			for (Map<String, Object> artObj : articles) {
				//解析每一个要修改的图文素材，请求微信服务器修改素材
				Map<String, Object> map = (Map<String, Object>) artObj.get("article");
				ArticleUpdate articleUpd = new ArticleUpdate();
				articleUpd.setMediaId(artObj.get("mediaId").toString());
				articleUpd.setIndex(Integer.valueOf(artObj.get("index").toString()));
				//默认图文消息不展示封面图片
				Article article = new Article(map.get("thumbMediaId").toString(), map.get("author").toString(), map.get("title").toString(), map.get("sourceUrl").toString(), map.get("content").toString(), map.get("digest").toString(), 0);
				articleUpd.setArticle(article);
				UploadMaterialResponse response = materialAPI.updateMaterialNews(articleUpd);
				String repCode = response.getErrcode();
				if(StringUtils.isNotEmpty(repCode) && repCode.equals("0")){
					//如果微信服务器修改成功，本地数据库表修改文章信息
					map.put("indexNum", artObj.get("index"));
					getBaseDao().update("WXArticleMaterialMapper.update", map);
				}else{
					updSuccFlag = false;
					updStr.append("异常:"+response.getErrmsg()+";"+"["+map.get("title").toString()+"]，文章微信服务器修改失败...");
				}
			}
		}else{
			outputObject.setReturnCode("-9999");
        	outputObject.setReturnMessage("修改图文素材失败，原因：文章素材参数为空.");
			return 0;
		}
		if(!updSuccFlag){
			outputObject.setReturnCode("-9999");
        	outputObject.setReturnMessage("有图文素材修改失败，详情："+updStr.toString());
			return 0;
		}
        return 0;
	}
	
	
	@Override
	public int deleteWXMaterial(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//首先，删除微信服务器图文素材信息
		String mediaId = inputObject.getParams().get("mediaId").toString();
		//微信公众号appId和secret
		String appId = inputObject.getParams().get("appId").toString();
		String secret = inputObject.getParams().get("secret").toString();
		ApiConfig config = new ApiConfig(appId, secret);
		MaterialAPI materialAPI = new MaterialAPI(config);
		int result = materialAPI.deleteMaterial(mediaId).getCode();
		if(result==0){
			//删除成功，开始删除数据库数据
			//根据素材ID，删除微信素材表信息
			int flag = getBaseDao().delete("WXMaterialMapper.delete", inputObject.getParams());
			if(flag>=0){
				//根据素材ID，删除微信文章表信息
				return getBaseDao().delete("WXArticleMaterialMapper.deleteByMaterialId", inputObject.getParams());
			}
		}else{
			outputObject.setReturnCode("-9999");
			outputObject.setReturnMessage("微信服务器删除图文消息失败,errorCode="+result);
		}
		return 0;
	}



	@Override
	public void updateSyncNews(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//微信公众号appId和secret
		String appId = inputObject.getParams().get("appId").toString();
		String secret = inputObject.getParams().get("secret").toString();
		ApiConfig config = new ApiConfig(appId, secret);
		MaterialAPI materialAPI = new MaterialAPI(config);
		//首先，获取微信服务器图文素材的总数量
		int newsCount = materialAPI.countMaterial().getNews();
		System.out.println("微信服务器图文素材总数量为：=========="+newsCount+"==============");
		//查询数据库所有图文素材的mediaId
		List<Map<String, Object>> mediaIdList = getBaseDao().queryForList("WXMaterialMapper.getAllNewsMediaId", inputObject.getParams());
		System.out.println("本地数据库图文素材总数量:======"+mediaIdList.size()+"=======");
		List<String> mediaIdContains = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(mediaIdList)){
			for (Map<String, Object> map : mediaIdList) {
				mediaIdContains.add(map.get("MEDIAID").toString());
			}
		}
		int itemCount = 1;
		//分页查询图文素材列表
		for(int k=0;k<newsCount;k++){
			GetMaterialListResponse response = materialAPI.batchGetMaterial(MaterialType.NEWS, k, 20);
			if(CollectionUtils.isNotEmpty(response.getItems())){
				for(Map<String, Object> item : response.getItems()){
					System.out.println("media_id : " + item.get("media_id"));
					System.out.println("content : " + item.get("content"));
					//图文素材媒体ID
					String mediaId = item.get("media_id").toString();
					boolean existFlag = mediaIdContains.contains(mediaId);
					System.out.println("微信服务器返回素材结果("+itemCount+")-----"+item);
					//如果存在，忽略；不存在保存到本地数据库中
					System.out.println("第：("+itemCount+")个素材，数据库是否存在-----"+existFlag);
					if(!existFlag){
						//图文素材文章内容
						Map<String, Object> cotObj = (Map<String, Object>) item.get("content");
						//文章列表信息
						List<Map<String, Object>> newsItems = (List<Map<String, Object>>) cotObj.get("news_item");
						//生成图文素材主键ID-materialId
						String materialId = StringUtil.getSquence();
						//首先，保存微信文章表数据
						Map<String, Object> params = new HashMap<String, Object>();
						if(CollectionUtils.isNotEmpty(newsItems)){
							List<Map<String, Object>> artParams = new ArrayList<Map<String,Object>>();
							int indexNum = 0;
							for (Map<String, Object> map : newsItems) {
								String articleId = StringUtil.getSquence();
								map.put("articleId", articleId);
								map.put("materialId", materialId);
								//标题、作者、文章摘要、内容、map中已经存在
								map.put("thumbMediaId", map.get("thumb_media_id").toString());
								map.put("sourceUrl", map.get("content_source_url").toString());
								map.put("showCoverPic", map.get("show_cover_pic").toString());
								map.put("indexNum", indexNum);
								map.put("remoteUrl", map.get("url").toString());
								indexNum++;
								artParams.add(map);
								System.out.println("入参==============");
								System.out.println(map);
								System.out.println("入参==============***********************结束");
								int flag = getBaseDao().insert("WXArticleMaterialMapper.insert", map);
							}
							params.put("articles", artParams);
							System.out.println("文章插入成功======("+itemCount+")=====");
							//保存微信素材表数据
							inputObject.getParams().put("materialId", materialId);
							inputObject.getParams().put("name", "图文素材");
							inputObject.getParams().put("mediaId", mediaId);
							inputObject.getParams().put("category", "news");
							inputObject.getParams().put("createTime", new Date());
							getBaseDao().insert("WXMaterialMapper.insert", inputObject.getParams());
							System.out.println("素材插入成功======("+itemCount+")=====");
						}
					}
					itemCount++;
				}
			}else{
				outputObject.setReturnCode("-9999");
				outputObject.setReturnMessage("查询微信图文素材列表失败,原因："+response.getErrmsg());
			}
			k+=20;
		}
		
		
	}



	@Override
	public void getArticlesByWxMediaId(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		//根据微信图文素材MediaId，查询微信文章列表
		List<Map<String, Object>> articles = getBaseDao().queryForList("WXArticleMaterialMapper.getArticlesByWxMediaId", inputObject.getParams());
		if(CollectionUtils.isNotEmpty(articles)){
			outputObject.setBeans(articles);
		}
	}
	
	
}
