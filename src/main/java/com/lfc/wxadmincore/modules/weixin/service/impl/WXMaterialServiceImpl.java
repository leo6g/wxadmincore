package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.sd4324530.fastweixin.api.MaterialAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.MaterialType;
import com.github.sd4324530.fastweixin.api.response.DownloadMaterialResponse;
import com.github.sd4324530.fastweixin.api.response.GetMaterialListResponse;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXMaterialService ;

public class WXMaterialServiceImpl extends BaseServiceImpl implements IWXMaterialService   {

	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信素材表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("WXMaterialMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXMaterialMapper.countAll", inputObject.getParams());
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
		Object object=getBaseDao().queryForObject("WXMaterialMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	
	
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,Object>> list = getBaseDao().queryForList("WXMaterialMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertWXMaterial(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信素材表是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXMaterialMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("materialId", StringUtil.getSquence());
			return getBaseDao().insert("WXMaterialMapper.insert", inputObject.getParams());
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("微信素材表已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateWXMaterial(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//查询微信素材表是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("WXMaterialMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("WXMaterialMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("微信素材表已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteWXMaterial(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("WXMaterialMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWXMaterial(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXMaterialMapper.logicDelete", inputObject.getParams());

	}
	@Override
	public int insertImage(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		inputObject.getParams().put("createTime", new Date());
		inputObject.getParams().put("materialId", StringUtil.getSquence());
		return getBaseDao().insert("WXMaterialMapper.insertImage", inputObject.getParams());
	}
	@Override
	public int insertVoice(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		inputObject.getParams().put("createTime", new Date());
		inputObject.getParams().put("materialId", StringUtil.getSquence());
		return getBaseDao().insert("WXMaterialMapper.insertVoice", inputObject.getParams());
	}
	@Override
	public int insertVideo(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		inputObject.getParams().put("createTime", new Date());
		inputObject.getParams().put("materialId", StringUtil.getSquence());
		return getBaseDao().insert("WXMaterialMapper.insertVideo", inputObject.getParams());
	}
	@Override
	public void getImageList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		//分页查询微信素材表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("WXMaterialMapper.getImageList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXMaterialMapper.countImageAll", inputObject.getParams());
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
	public int updateImageGroup(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			inputObject.getParams().put("updateTime", new Date());
			return getBaseDao().update("WXMaterialMapper.updateImageGroup", inputObject.getParams());


	}

	
	
	@Override
	public int deleteImageGroup(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("WXMaterialMapper.deleteImageGroup", inputObject.getParams());
	}
	

	@Override
	public void updateSyncImages(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		StringBuffer errorMess= new StringBuffer();
		//微信公众号appId和secret
		String appId = inputObject.getParams().get("appId").toString();
		String secret = inputObject.getParams().get("secret").toString();
		ApiConfig config = new ApiConfig(appId, secret);
		MaterialAPI materialAPI = new MaterialAPI(config);
		//首先，获取微信服务器图片素材的总数量
		int imageCount = materialAPI.countMaterial().getImage();
		System.out.println("微信服务器图片素材总数量为：=========="+imageCount+"==============");
		//查询数据库所有图片素材的mediaId
		List<Map<String, Object>> mediaIdList = getBaseDao().queryForList("WXMaterialMapper.getAllImageMediaId", inputObject.getParams());
		System.out.println("本地数据库图片素材总数量:======"+mediaIdList.size()+"=======");
		List<String> mediaIdContains = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(mediaIdList)){
			for (Map<String, Object> map : mediaIdList) {
				mediaIdContains.add(map.get("mediaId").toString());
			}
		}
		int itemCount = 1;
		//分页查询图片素材列表
		for(int k=0;k<imageCount;k++){
			GetMaterialListResponse response = materialAPI.batchGetMaterial(MaterialType.IMAGE, k, 20);
			if(CollectionUtils.isNotEmpty(response.getItems())){
				for(Map<String, Object> item : response.getItems()){
					System.out.println("media_id : " + item.get("media_id"));
					//图片素材媒体ID
					String mediaId = item.get("media_id").toString();
					String name = item.get("name").toString();
					String url = item.get("url").toString();
					boolean existFlag = mediaIdContains.contains(mediaId);
					System.out.println("微信服务器返回素材结果("+itemCount+")-----"+item);
					//如果存在，忽略；不存在保存到本地数据库中
					System.out.println("第：("+itemCount+")个素材，数据库是否存在-----"+existFlag);
					if(!existFlag){
						
						//下载到本地
						DownloadMaterialResponse response2 = materialAPI.downloadMaterial(mediaId, MaterialType.IMAGE);
		                String fileName=inputObject.getParams().get("path").toString()+"material/"+name;
				        try {	if(response2.getErrcode() ==null){
				        	    response2.writeTo(new FileOutputStream(new File(fileName)));
				                }else{
				                	errorMess.append(name+":"+response2.getErrmsg()+";");
				                }			        	
				        } catch (FileNotFoundException e) {
				        	logger.error("异常", e);
				        } catch (IOException e) {
				        	logger.error("异常", e);
				        }
						//生成图片素材主键ID-materialId
						   String materialId = StringUtil.getSquence();
							//保存微信素材表数据
							inputObject.getParams().put("materialId", materialId);
							inputObject.getParams().put("name", name);
							inputObject.getParams().put("mediaId", mediaId);
							inputObject.getParams().put("category", "image");
							inputObject.getParams().put("createTime", new Date());
							inputObject.getParams().put("remoteUrl", url);
							inputObject.getParams().put("localUrl", name);
							inputObject.getParams().put("groupId", "default");
							getBaseDao().insert("WXMaterialMapper.insertImage", inputObject.getParams());
							System.out.println("素材插入成功======("+itemCount+")=====");
						
					}
					itemCount++;
				}
			}
			k+=20;
		}
		
		if(StringUtil.isEmpty(errorMess.toString())){
			outputObject.setReturnMessage("图片素材同步成功!");
		}else{
			outputObject.setReturnMessage(errorMess.toString());
		}
	}
	
	
	

	@Override
	public void updateSyncVideos(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//微信公众号appId和secret
		String appId = inputObject.getParams().get("appId").toString();
		String secret = inputObject.getParams().get("secret").toString();
		ApiConfig config = new ApiConfig(appId, secret);
		MaterialAPI materialAPI = new MaterialAPI(config);
		//首先，获取微信服务器图片素材的总数量
		int videoCount = materialAPI.countMaterial().getVideo();
		System.out.println("微信服务器视频素材总数量为：=========="+videoCount+"==============");
		//查询数据库所有图片素材的mediaId
		List<Map<String, Object>> mediaIdList = getBaseDao().queryForList("WXMaterialMapper.getAllVideoMediaId", inputObject.getParams());
		System.out.println("本地数据库视频素材总数量:======"+mediaIdList.size()+"=======");
		List<String> mediaIdContains = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(mediaIdList)){
			for (Map<String, Object> map : mediaIdList) {
				mediaIdContains.add(map.get("mediaId").toString());
			}
		}
		int itemCount = 1;
		//分页查询图片素材列表
		for(int k=0;k<videoCount;k++){
			GetMaterialListResponse response = materialAPI.batchGetMaterial(MaterialType.VIDEO, k, 20);
			if(CollectionUtils.isNotEmpty(response.getItems())){
				for(Map<String, Object> item : response.getItems()){
					System.out.println("media_id : " + item.get("media_id"));
					//图片素材媒体ID
					String mediaId = item.get("media_id").toString();
					String name = item.get("name").toString();
					boolean existFlag = mediaIdContains.contains(mediaId);
					System.out.println("微信服务器返回素材结果("+itemCount+")-----"+item);
					//如果存在，忽略；不存在保存到本地数据库中
					System.out.println("第：("+itemCount+")个素材，数据库是否存在-----"+existFlag);
					if(!existFlag){
						
						//下载到本地
						DownloadMaterialResponse response2 = materialAPI.downloadMaterial(mediaId, MaterialType.VIDEO);
		                String fileName=inputObject.getParams().get("path").toString()+"material/"+name;
				        try {			     
					        	response2.writeTo(new FileOutputStream(new File(fileName)));
				        } catch (FileNotFoundException e) {
				        	logger.error("异常", e);
				        } catch (IOException e) {
				        	logger.error("异常", e);
				        }
						//生成视频素材主键ID-materialId
						   String materialId = StringUtil.getSquence();
							//保存微信素材表数据
							inputObject.getParams().put("materialId", materialId);
							inputObject.getParams().put("name", name);
							inputObject.getParams().put("mediaId", mediaId);
							inputObject.getParams().put("category", "video");
							inputObject.getParams().put("subType", "18");//18是广告
							inputObject.getParams().put("createTime", new Date());
							inputObject.getParams().put("localUrl", name);
							inputObject.getParams().put("videoTags", response2.getTitle());
							inputObject.getParams().put("videoInstru", response2.getDescription());
							getBaseDao().insert("WXMaterialMapper.insertVideo", inputObject.getParams());
							System.out.println("素材插入成功======("+itemCount+")=====");
						
					}
					itemCount++;
				}
			}
			k+=20;
		}
		
		
	}
	
	
	

	@Override
	public void updateSyncVoices(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		//微信公众号appId和secret
		String appId = inputObject.getParams().get("appId").toString();
		String secret = inputObject.getParams().get("secret").toString();
		ApiConfig config = new ApiConfig(appId, secret);
		MaterialAPI materialAPI = new MaterialAPI(config);
		//首先，获取微信服务器图片素材的总数量
		int imageCount = materialAPI.countMaterial().getVoice();
		System.out.println("微信服务器图片素材总数量为：=========="+imageCount+"==============");
		//查询数据库所有图片素材的mediaId
		List<Map<String, Object>> mediaIdList = getBaseDao().queryForList("WXMaterialMapper.getAllVoiceMediaId", inputObject.getParams());
		System.out.println("本地数据库图片素材总数量:======"+mediaIdList.size()+"=======");
		List<String> mediaIdContains = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(mediaIdList)){
			for (Map<String, Object> map : mediaIdList) {
				mediaIdContains.add(map.get("mediaId").toString());
			}
		}
		int itemCount = 1;
		//分页查询图片素材列表
		for(int k=0;k<imageCount;k++){
			GetMaterialListResponse response = materialAPI.batchGetMaterial(MaterialType.VOICE, k, 20);
			if(CollectionUtils.isNotEmpty(response.getItems())){
				for(Map<String, Object> item : response.getItems()){
					System.out.println("media_id : " + item.get("media_id"));
					//图片素材媒体ID
					String mediaId = item.get("media_id").toString();
					String name = item.get("name").toString();
					boolean existFlag = mediaIdContains.contains(mediaId);
					System.out.println("微信服务器返回素材结果("+itemCount+")-----"+item);
					//如果存在，忽略；不存在保存到本地数据库中
					System.out.println("第：("+itemCount+")个素材，数据库是否存在-----"+existFlag);
					if(!existFlag){
						
						//下载到本地
						DownloadMaterialResponse response2 = materialAPI.downloadMaterial(mediaId, MaterialType.VOICE);
		                String fileName=inputObject.getParams().get("path").toString()+"material/"+name;
				        try {				        	
					        	response2.writeTo(new FileOutputStream(new File(fileName)));
				        } catch (FileNotFoundException e) {
				        	logger.error("异常", e);
				        } catch (IOException e) {
				        	logger.error("异常", e);
				        }
						//生成图片素材主键ID-materialId
						   String materialId = StringUtil.getSquence();
							//保存微信素材表数据
							inputObject.getParams().put("materialId", materialId);
							inputObject.getParams().put("name", name);
							inputObject.getParams().put("mediaId", mediaId);
							inputObject.getParams().put("category", "voice");
							inputObject.getParams().put("createTime", new Date());
							inputObject.getParams().put("localUrl", name);
							inputObject.getParams().put("subType", "17");//17是音乐
							getBaseDao().insert("WXMaterialMapper.insertVoice", inputObject.getParams());
							System.out.println("素材插入成功======("+itemCount+")=====");
						
					}
					itemCount++;
				}
			}
			k+=20;
		}
		
		
	}
	
	
	@Override
	public void getByMediaId(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		Object object=getBaseDao().queryForObject("WXMaterialMapper.getByMediaId", inputObject.getParams());
		outputObject.setObject(object);
	}
	
}
