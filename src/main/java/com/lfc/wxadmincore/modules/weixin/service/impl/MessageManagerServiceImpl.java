package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IMessageManagerService;

public class MessageManagerServiceImpl extends BaseServiceImpl implements IMessageManagerService {

	@Override
	public void getList(InputObject inputObject, OutputObject outputObject) {
		List<Map<String, Object>> list = getBaseDao().queryForList("MessageMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("MessageMapper.countAll", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		// 总数量
		beanMap.put("count", count);
		// 当前页码
		beanMap.put("currentPage", inputObject.getParams().get("pageNumber"));
		Integer limit = (Integer) inputObject.getParams().get("limit");
		int totalPages = (count + limit - 1) / limit;
		// 总页数
		beanMap.put("totalPages", totalPages);
		outputObject.setBean(beanMap);
	}

	@Override
	public void getById(InputObject inputObject, OutputObject outputObject) {
		Object object = getBaseDao().queryForObject("MessageMapper.getById", inputObject.getParams());
		outputObject.setObject(object);
	}

	@Override
	public int insertMessage(InputObject inputObject, OutputObject outputObject) {
		inputObject.getParams().put("id", StringUtil.getSquence());
		return getBaseDao().insert("MessageMapper.insert", inputObject.getParams());
	}

	@Override
	public int updateMessage(InputObject inputObject, OutputObject outputObject) {
		return getBaseDao().update("MessageMapper.update", inputObject.getParams());
	}

	@Override
	public int deleteMessage(InputObject inputObject, OutputObject outputObject) {
		return getBaseDao().delete("MessageMapper.delete", inputObject.getParams());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSortIndexCount(InputObject inputObject, OutputObject outputObject) {
		Map<String, Object> map = (Map<String, Object>) getBaseDao().queryForObject("MessageMapper.getSortIndexCount",
				inputObject.getParams());
		outputObject.setBean(map);
	}

	@Override
	public void getSendList(InputObject inputObject, OutputObject outputObject) {
		// 获取信息发送列表（前八个）
		List<Map<String, Object>> list = getBaseDao().queryForList("MessageMapper.getSendList",
				inputObject.getParams());
		// 获取所有用户openId
		List<Map<String, Object>> userList = getBaseDao().queryForList("WXUserMapper.getAllOpenid",
				inputObject.getParams());
		outputObject.setBeans(list);
		outputObject.setObject(userList);
	}

	@Override
	public void getPreviewArt(InputObject inputObject, OutputObject outputObject) {
		// 获取信息发送列表（前八个）
		List<Map<String, Object>> list = getBaseDao().queryForList("MessageMapper.getSendList",
				inputObject.getParams());
		if(list.size() > 0) {
			Map<String, Object> first = list.get(0);//获取第一个图文
			list.subList(0, 1).clear();//删除第一个图文
			first.put("normalArts", list);
			outputObject.setBean(first);
		}
	}

}
