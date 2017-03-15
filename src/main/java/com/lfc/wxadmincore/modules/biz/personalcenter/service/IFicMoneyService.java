package com.lfc.wxadmincore.modules.biz.personalcenter.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IFicMoneyService {
	
	/**
	 * 分页查询个人中心	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询个人中心
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有个人中心
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的个人中心
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertFicMoney(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新个人中心
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateFicMoney(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除个人中心(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteFicMoney(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除个人中心
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteFicMoney(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	/**
	 * 根据条件删除单表信息，此方法最起码有一个条件成立    zhaoyan 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteFicMoneyByParams(InputObject inputObject, OutputObject outputObject) throws Exception;


	/**
	 * 根据openId获取邮豆的总数量
	 *  @param inputObject
	 *  @param outputObject
	 *  @throws Exception
	 * */
	public void getFictitiousMoneyByOpenId(InputObject inputObject, OutputObject outputObject) throws Exception;
}
