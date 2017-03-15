package com.lfc.wxadmincore.modules.weiweb.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IBannerService {
	
	/**
	 * 分页查询微网站	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微网站
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微网站
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微网站
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertBanner(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微网站
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateBanner(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微网站(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteBanner(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微网站
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteBanner(InputObject inputObject, OutputObject outputObject) throws Exception;
}
