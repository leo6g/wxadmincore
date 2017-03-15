package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWXMtrlImgGroupService {
	
	/**
	 * 分页查询图片素材分组表	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询图片素材分组表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有图片素材分组表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的图片素材分组表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertWXMtrlImgGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新图片素材分组表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateWXMtrlImgGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除图片素材分组表(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWXMtrlImgGroup(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除图片素材分组表
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWXMtrlImgGroup(InputObject inputObject, OutputObject outputObject) throws Exception;

	/**
	 * 查询分组信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getListInfoById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
