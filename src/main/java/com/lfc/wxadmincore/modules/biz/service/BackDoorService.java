package com.lfc.wxadmincore.modules.biz.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface BackDoorService {
	
	public void getList(InputObject inputObject, OutputObject outputObject)
			throws Exception;
	
	public void executeUpdate(InputObject inputObject, OutputObject outputObject)
			throws Exception;

}
