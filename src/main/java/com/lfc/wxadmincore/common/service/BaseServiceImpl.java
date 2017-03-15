package com.lfc.wxadmincore.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.lfc.wxadmincore.common.dao.impl.BaseDaoImpl;

/**
 * 
 * 
 */
public class BaseServiceImpl {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private BaseDaoImpl baseDao;
	public BaseDaoImpl getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDaoImpl baseDao) {
		this.baseDao = baseDao;
	}
	

}
