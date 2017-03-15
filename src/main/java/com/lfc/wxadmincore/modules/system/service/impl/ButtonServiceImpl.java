package com.lfc.wxadmincore.modules.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.system.service.IButtonService;

public class ButtonServiceImpl extends BaseServiceImpl implements IButtonService  {

	@Override
	public void queryButtonList(InputObject inputObject,
			OutputObject outputObject) throws Exception {		
		List<Map<String,Object>> list = getBaseDao().queryForList("ButtonOperMapper.selectButtonInfoList", inputObject.getParams());
		outputObject.setBeans(list);
		inputObject.getParams().put("id", inputObject.getParams().get("menuId"));
		Object object=getBaseDao().queryForObject("MenuMapper.selectByPrimaryKey", inputObject.getParams());
		outputObject.setObject(object);

	}

	@Override
	public void insertButtonInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("id", StringUtil.getSquence());
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createTime=sf.parse(sf.format(new Date()));
		inputObject.getParams().put("createTime", createTime);
		int flag=getBaseDao().insert("ButtonOperMapper.insertButtonInfo", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("添加按钮操作信息成功");		
		}else{
			  outputObject.setReturnMessage("添加按钮操作信息失败");
		}

	}

	@Override
	public void updateButtonInfoById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createTime=sf.parse(sf.format(new Date()));
		inputObject.getParams().put("updateTime", createTime);
		int flag=getBaseDao().update("ButtonOperMapper.updateButtonInfoById", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("更新按钮操作信息成功");		
		}else{
			  outputObject.setReturnMessage("更新按钮操作信息失败");
		}

	}

	@Override
	public void deletetButtonInfoById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		int flag=getBaseDao().delete("ButtonOperMapper.deletetButtonInfoById", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("删除按钮操作信息成功");		
		}else{
			  outputObject.setReturnMessage("删除按钮操作信息失败");
		}

	}
	@Override
	public void selectByPrimaryKey(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("ButtonOperMapper.selectByPrimaryKey", inputObject.getParams());
		outputObject.setObject(object);
	}
	@Override
	public void deletetButtonInfoByMenuId(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		int flag=getBaseDao().delete("ButtonOperMapper.deletetButtonInfoByMenuId", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("删除按钮操作信息成功");		
		}else{
			  outputObject.setReturnMessage("删除按钮操作信息失败");
		}

	}

}
