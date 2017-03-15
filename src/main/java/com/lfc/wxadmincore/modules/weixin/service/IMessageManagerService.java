package com.lfc.wxadmincore.modules.weixin.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IMessageManagerService {

	public void getList(InputObject inputObject, OutputObject outputObject);

	public void getById(InputObject inputObject, OutputObject outputObject);

	public int insertMessage(InputObject inputObject, OutputObject outputObject);

	public int updateMessage(InputObject inputObject, OutputObject outputObject);

	public int deleteMessage(InputObject inputObject, OutputObject outputObject);
	
	public void getSortIndexCount(InputObject inputObject, OutputObject outputObject);
	
	public void getSendList(InputObject inputObject, OutputObject outputObject);
	
	public void getPreviewArt(InputObject inputObject, OutputObject outputObject);

}
