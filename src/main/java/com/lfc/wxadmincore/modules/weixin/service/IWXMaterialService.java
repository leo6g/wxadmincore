package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXMaterialService {
	
	/**
	 * 分页查询微信素材表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信素材表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信素材表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信素材表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXMaterial(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信素材表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXMaterial(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信素材表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXMaterial(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信素材表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXMaterial(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入微信素材之图片
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertImage(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入微信素材之音频
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertVoice(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入微信素材之视频
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertVideo(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 分页获取素材列表值图片
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getImageList(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 批量更新图片分组信息
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int updateImageGroup(InputObject inputObject,OutputObject outputObject) throws Exception;
	/**
	 * 批量删除图片素材
	 * @param inputObject
	 * @param outputObject
	 * @return
	 * @throws Exception
	 */
	public int deleteImageGroup(InputObject inputObject, OutputObject outputObject)throws Exception;
	/**
	 * 同步图片信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateSyncImages(InputObject inputObject, OutputObject outputObject)throws Exception; 
	/**
	 * 同步音频信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateSyncVoices(InputObject inputObject, OutputObject outputObject)throws Exception;
	/**
	 * 同步视频信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateSyncVideos(InputObject inputObject, OutputObject outputObject)throws Exception;
	/**
	 * 根据MediaId查询微信素材表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getByMediaId(InputObject inputObject,OutputObject outputObject)throws Exception;
	
	
}
