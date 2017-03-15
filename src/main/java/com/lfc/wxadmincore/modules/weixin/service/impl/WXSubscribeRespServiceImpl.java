package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXAutoResponseService ;
import com.lfc.wxadmincore.modules.weixin.service.IWXSubscribeRespService;

public class WXSubscribeRespServiceImpl extends BaseServiceImpl implements IWXSubscribeRespService   {

	@Override
	public int saveReply(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Map<String,Object> map =(Map<String,Object>) getBaseDao().queryForObject("WXSubcribeRespMapper.getAll", inputObject.getParams());
		String id=null;
		if(map==null){
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("id", StringUtil.getSquence());
			return getBaseDao().insert("WXSubcribeRespMapper.insertReply", inputObject.getParams());
		}else{
			id=(String)map.get("id");
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put("id", id);
			return getBaseDao().update("WXSubcribeRespMapper.updateReply", inputObject.getParams());
		}
	}
	@Override
	public void getReply(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object =getBaseDao().queryForObject("WXSubcribeRespMapper.getAll", inputObject.getParams());
		outputObject.setObject(object);
	}
	//删除
	public int deleteReply(InputObject inputObject, OutputObject outputObject) throws Exception {
		return getBaseDao().delete("WXSubcribeRespMapper.delete", inputObject.getParams());
	}

}
