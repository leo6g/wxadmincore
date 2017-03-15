package com.lfc.wxadmincore.modules.weixin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.UserInfo;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoListResponse;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.api.response.GetUsersResponse;
import com.github.sd4324530.fastweixin.api.response.GetUsersResponse.Openid;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.core.util.BeanUtil;
import com.lfc.core.util.ControlConstants;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.weixin.service.IWXUserService;
/**
 *               ii.                                         ;9ABH,          
 *              SA391,                                    .r9GG35&G          
 *              &#ii13Gh;                               i3X31i;:,rB1         
 *              iMs,:,i5895,                         .5G91:,:;:s1:8A         
 *               33::::,,;5G5,                     ,58Si,,:::,sHX;iH1        
 *                Sr.,:;rs13BBX35hh11511h5Shhh5S3GAXS:.,,::,,1AG3i,GG        
 *                .G51S511sr;;iiiishS8G89Shsrrsh59S;.,,,,,..5A85Si,h8        
 *               :SB9s:,............................,,,.,,,SASh53h,1G.       
 *            .r18S;..,,,,,,,,,,,,,,,,,,,,,,,,,,,,,....,,.1H315199,rX,       
 *          ;S89s,..,,,,,,,,,,,,,,,,,,,,,,,....,,.......,,,;r1ShS8,;Xi       
 *        i55s:.........,,,,,,,,,,,,,,,,.,,,......,.....,,....r9&5.:X1       
 *       59;.....,.     .,,,,,,,,,,,...        .............,..:1;.:&s       
 *      s8,..;53S5S3s.   .,,,,,,,.,..      i15S5h1:.........,,,..,,:99       
 *      93.:39s:rSGB@A;  ..,,,,.....    .SG3hhh9G&BGi..,,,,,,,,,,,,.,83      
 *      G5.G8  9#@@@@@X. .,,,,,,.....  iA9,.S&B###@@Mr...,,,,,,,,..,.;Xh     
 *      Gs.X8 S@@@@@@@B:..,,,,,,,,,,. rA1 ,A@@@@@@@@@H:........,,,,,,.iX:    
 *     ;9. ,8A#@@@@@@#5,.,,,,,,,,,... 9A. 8@@@@@@@@@@M;    ....,,,,,,,,S8    
 *     X3    iS8XAHH8s.,,,,,,,,,,...,..58hH@@@@@@@@@Hs       ...,,,,,,,:Gs   
 *    r8,        ,,,...,,,,,,,,,,.....  ,h8XABMMHX3r.          .,,,,,,,.rX:  
 *   :9, .    .:,..,:;;;::,.,,,,,..          .,,.               ..,,,,,,.59  
 *  .Si      ,:.i8HBMMMMMB&5,....                    .            .,,,,,.sMr 
 *  SS       :: h@@@@@@@@@@#; .                     ...  .         ..,,,,iM5 
 *  91  .    ;:.,1&@@@@@@MXs.                            .          .,,:,:&S 
 *  hS ....  .:;,,,i3MMS1;..,..... .  .     ...                     ..,:,.99 
 *  ,8; ..... .,:,..,8Ms:;,,,...                                     .,::.83 
 *   s&: ....  .sS553B@@HX3s;,.    .,;13h.                            .:::&1 
 *    SXr  .  ...;s3G99XA&X88Shss11155hi.                             ,;:h&, 
 *     iH8:  . ..   ,;iiii;,::,,,,,.                                 .;irHA  
 *      ,8X5;   .     .......                                       ,;iihS8Gi
 *         1831,                                                 .,;irrrrrs&@
 *           ;5A8r.                                            .:;iiiiirrss1H
 *             :X@H3s.......                                .,:;iii;iiiiirsrh
 *              r#h:;,...,,.. .,,:;;;;;:::,...              .:;;;;;;iiiirrss1
 *             ,M8 ..,....,.....,,::::::,,...         .     .,;;;iiiiiirss11h
 *             8B;.,,,,,,,.,.....          .           ..   .:;;;;iirrsss111h
 *            i@5,:::,,,,,,,,.... .                   . .:::;;;;;irrrss111111
 *            9Bi,:,,,,......                        ..r91;;;;;iirrsss1ss1111
*/

public class WXUserServiceImpl extends BaseServiceImpl implements IWXUserService {

	@Override
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appId = inputObject.getValue("appId");
		String secret = inputObject.getValue("secret");
		ApiConfig config = new ApiConfig(appId, secret);
		UserAPI userAPI = new UserAPI(config);
		// 分页查询微信关注用户表信息
		List<Map<String, Object>> list = getBaseDao().queryForList("WXUserMapper.getList", inputObject.getParams());
		Integer maxTagSize = 0;//该页用户所拥有的最大标签量
		for (Map<String, Object> m : list) {
			if(m.get("openId") == null) {
				continue;
			}
			String openId = m.get("openId").toString();
			GetUserInfoResponse userInfo = userAPI.getUserInfo(openId);//根据每个用户的openid获取标签id组
			if(userInfo.getTagid_list() != null && userInfo.getTagid_list().length > maxTagSize) {
				maxTagSize = userInfo.getTagid_list().length;
			}
			m.put("tag_list", userInfo.getTagid_list());
		}
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount("WXUserMapper.countAll", inputObject.getParams());
		int countAll = getBaseDao().getTotalCount("WXUserMapper.countAllUser", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		// 总数量
		beanMap.put("count", count);
		beanMap.put("countAll", countAll);
		// 当前页码
		beanMap.put("currentPage", inputObject.getParams().get("pageNumber"));
		Integer limit = (Integer) inputObject.getParams().get("limit");
		int totalPages = (count + limit - 1) / limit;
		// 总页数
		beanMap.put("totalPages", totalPages);
		beanMap.put("maxTagSize", maxTagSize);
		outputObject.setBean(beanMap);
	}

	@Override
	public void getById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Object object = getBaseDao().queryForObject("WXUserMapper.getById", inputObject.getParams());
		outputObject.setObject(object);
	}

	@Override
	public void getByOpenId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Object object = getBaseDao().queryForObject("WXUserMapper.getByOpenId", inputObject.getParams());
		outputObject.setObject(object);
	}

	@Override
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag", "0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WXUserMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);

	}

	@Override
	public void insertWXUser(InputObject inputObject, OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag", 0);
		inputObject.getParams().put("createTime", new Date());
		inputObject.getParams().put("wxUserId", StringUtil.getSquence());
		inputObject.getParams().put("gender", inputObject.getParams().get("sex").toString());
		inputObject.getParams().put("wxPropertyId", StringUtil.getSquence());
		inputObject.getParams().put("subscribeTime",new java.sql.Timestamp((Long) (inputObject.getParams().get("subscribeTime"))* 1000));
		getBaseDao().insert("WXUserMapper.insertSync", inputObject.getParams());
		getBaseDao().insert("WXUserMapper.insertProperty", inputObject.getParams());
	}
	@Override
	public void updateWXUser(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		inputObject.getParams().put("gender", inputObject.getParams().get("sex").toString());
		inputObject.getParams().put("subscribeTime",new java.sql.Timestamp((Long) (inputObject.getParams().get("subscribeTime"))* 1000));
		getBaseDao().update("WXUserMapper.updateSync", inputObject.getParams());
		getBaseDao().update("WXUserMapper.updateProperty", inputObject.getParams());
	}
	@Override
	public int setUserRemark(InputObject inputObject, OutputObject outputObject) throws Exception {
		// 查询微信关注用户表是否存在 有code验证时放开
		// Object object = getBaseDao().queryForObject("WXUserMapper.getByCode",
		// inputObject.getParams());
		// if(object==null){
		return getBaseDao().update("WXUserMapper.update", inputObject.getParams());
		// }else{
		// outputObject.setReturnCode("-1");
		// outputObject.setReturnMessage("微信关注用户表已经存在，请修改!");
		// return -1;
		// }

	}

	@Override
	public int deleteWXUser(InputObject inputObject, OutputObject outputObject) throws Exception {
		return getBaseDao().delete("WXUserMapper.delete", inputObject.getParams());
	}

	@Override
	public int logicDeleteWXUser(InputObject inputObject, OutputObject outputObject) throws Exception {
		inputObject.getParams().put("updateTime", new Date());
		return getBaseDao().update("WXUserMapper.logicDelete", inputObject.getParams());
	}

	@Override
	public int updateWXUserGroup(InputObject inputObject, OutputObject outputObject) throws Exception {
		return getBaseDao().update("WXUserMapper.updateUserGroup", inputObject.getParams());
	}

	@SuppressWarnings("unchecked")
	@Override
	public OutputObject syncWXUsers(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appId = inputObject.getValue("appId");
		String secret = inputObject.getValue("secret");
		ApiConfig config = new ApiConfig(appId, secret);
		UserAPI userAPI = new UserAPI(config);
		String flag = "";// 循环的标识
		//所有的用户
		List<Map<String, Object>> list = getBaseDao().queryForList("WXUserMapper.getAll", null);
		//key为openid，value为对象
		Map<String,Map<String,Object>> userMap = new HashMap<String, Map<String,Object>>();
		for (Map<String, Object> m : list) {
			userMap.put(m.get("openId").toString(), m);
		}
		do {
			// 查询出所有的openId的集合，最大数量10000
			GetUsersResponse res = userAPI.getUsers(flag);
			Openid openId = res.getData();
			if (null != openId) {// 仍存在用户
				String[] openIds = openId.getOpenid();
				List<UserInfo> userInfoList = new ArrayList<UserInfo>();//新增的集合
				List<UserInfo> updateList = new ArrayList<UserInfo>();//更新的集合
				// 循环判断openid是否存在关注表中
				for (int i = 0; i < openIds.length; i++) {
					UserInfo userInfo = new UserInfo();
					// 检索openId是否存在
					Object object = userMap.get(openIds[i]);//根据openid获取用户
					userInfo.setOpenid(openIds[i]);
					if (null == object) {// 不存在
						userInfoList.add(userInfo);
					} else {// 存在，放入更新的集合
						updateList.add(userInfo);
					}
				}
				if(updateList.size() > 0) {
					List<List<UserInfo>> updateLists = subList(updateList, 100);
					List<GetUserInfoResponse> updateAllUserInfoList = new ArrayList<GetUserInfoResponse>();
					for (int i = 0; i < updateLists.size(); i++) {
						GetUserInfoListResponse resp = userAPI.getUserInfoList(updateLists.get(i));//获取所有用户信息
						updateAllUserInfoList.addAll(resp.getUser_info_list());
					}
					//比对
					for (int i = 0; i < updateAllUserInfoList.size(); i++) {//循环比对
						Object object = userMap.get(updateAllUserInfoList.get(i).getOpenid());
						GetUserInfoResponse g = updateAllUserInfoList.get(i);
						boolean state = compareUser((Map<String,Object>)object, g);//为真说明数据无变更不更新，为假更新
						if(!state) {
							Map<String, Object> param = BeanUtil.convertBean2Map(g);
							param.put("gender", g.getSex() == null ? null : g.getSex().toString());
							param.put("subscribeTime", g.getSubscribeTime() == null ? null : new java.sql.Timestamp(g.getSubscribeTime() * 1000));//返回的是毫秒，转换日期格式
							param.put("wxUserId", ((Map<String,Object>)object).get("wxUserId"));//根据数据库存的用户id去更新（不是openid）
							getBaseDao().update("WXUserMapper.updateSync", param);
							getBaseDao().update("WXUserMapper.updateProperty", param);
						}
					}
				}
				if(userInfoList.size() > 0) {//如果有新增关注用户则此list大小不为0
					List<List<UserInfo>> userInfoLists= subList(userInfoList, 100);
					List<GetUserInfoResponse> allUserInfoList=new ArrayList<GetUserInfoResponse>();
					for (int i = 0; i < userInfoLists.size(); i++) {
						GetUserInfoListResponse allUserInfo = userAPI.getUserInfoList(userInfoLists.get(i));//获取所有用户信息
						allUserInfoList.addAll(allUserInfo.getUser_info_list());
					}
					for (int i = 0; i < allUserInfoList.size(); i++) {//循环插入用户信息
						try {//防止插入时表中已经存在该openid（通过其他渠道进表），跳过此次insert
							GetUserInfoResponse userInfo = allUserInfoList.get(i);
							Map<String, Object> paraMap = BeanUtil.convertBean2Map(userInfo);
							paraMap.put("gender", userInfo.getSex() == null ? null : userInfo.getSex().toString());
							paraMap.put("wxUserId", StringUtil.getSquence());
							paraMap.put("wxPropertyId", StringUtil.getSquence());
							paraMap.put("createTime", new Date());
							paraMap.put("deleteFlag", "0");
							paraMap.put("subscribeTime", userInfo.getSubscribeTime() == null ? null : new java.sql.Timestamp(userInfo.getSubscribeTime() * 1000));//返回的是毫秒，转换日期格式
							getBaseDao().insert("WXUserMapper.insertSync", paraMap);
							getBaseDao().insert("WXUserMapper.insertProperty", paraMap);
						} catch (Exception e) {
							continue;
						}
					}
				}
				flag = res.getNextOpenid();
			} else {
				flag = "";
			}
		} while (!"".equals(flag));
		return outputObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OutputObject tagToUser(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appId = inputObject.getValue("appId");
		String secret = inputObject.getValue("secret");
		List<String> openidList = (List<String>) inputObject.getParams().get("openidList");//选中的所有用户openid
		List<String> selectTagList =(List<String>) inputObject.getParams().get("selectTagList");//选中的分组
		List<String> unselectTagList = (List<String>) inputObject.getParams().get("unSelectTagList");//未选中的分组
		UserAPI userApi = new UserAPI(new ApiConfig(appId, secret));
		//此处逻辑是这么地，因为微信接口只有取消和新增，并没有移动用户到标签，故采取
		//先删除选中用户的未选中分组，在添加选中用户的选中分组
		if(unselectTagList != null && unselectTagList.size() > 0) {
			for (String s : unselectTagList) {//先遍历未选中分组
				userApi.batchDeleteTagsToUser(openidList, Integer.parseInt(s));
			}
		}
		if(selectTagList != null && selectTagList.size() > 0) {
			for (String s : selectTagList) {//在遍历选中分组
				userApi.batchTagsToUser(openidList, Integer.parseInt(s));
			}
		}
		outputObject.setReturnMessage("操作成功");
		return outputObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OutputObject deleteTagToUser(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appId = inputObject.getValue("appId");
		String secret = inputObject.getValue("secret");
		List<String> openidList = (List<String>) inputObject.getParams().get("openidList");
		Integer tagId = Integer.parseInt(inputObject.getParams().get("tagId").toString());
		UserAPI userApi = new UserAPI(new ApiConfig(appId, secret));
		ResultType result = userApi.batchDeleteTagsToUser(openidList, tagId);
		if(result.getCode() == ResultType.SUCCESS.getCode()) {//用户分组成功
			//更新分组
			//getBaseDao().batchUpdate(statementName, list);
			outputObject.setReturnMessage("操作成功！");
		} else {
			outputObject.setReturnCode(ControlConstants.RETURN_CODE.SYSTEM_ERROR);
			outputObject.setReturnMessage(result.getDescription());
		}
		return outputObject;
	}

	@Override
	public OutputObject getBlackList(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appId = inputObject.getValue("appId");
		String secret = inputObject.getValue("secret");
		ApiConfig config = new ApiConfig(appId, secret);
		UserAPI userAPI = new UserAPI(config);
		String flag = "";// 存放openid
		int count = 0;//标识位
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<GetUserInfoResponse> allUser = new ArrayList<GetUserInfoResponse>();//存放所有拉黑用户
		do {
			// 查询出所有的openId的集合，最大数量10000
			GetUsersResponse res = userAPI.getBlackUsers(flag);
			paramMap.put("total", res.getTotal());
			count = res.getCount();//小于10000跳出循环
			Openid openId = res.getData();
			if (null != openId) {// 仍存在用户
				String[] openIds = openId.getOpenid();
				List<UserInfo> userInfoList = new ArrayList<UserInfo>();
				for (int i = 0; i < openIds.length; i++) {
					UserInfo userInfo = new UserInfo();
					userInfo.setOpenid(openIds[i]);
					userInfoList.add(userInfo);
				}
				if(userInfoList.size() > 0) {
					GetUserInfoListResponse allUserInfo = userAPI.getUserInfoList(userInfoList);//获取所有用户信息
					List<GetUserInfoResponse> allUserInfoList = allUserInfo.getUser_info_list();
					if(allUserInfoList != null && allUserInfoList.size() > 0) {
						allUser.addAll(allUserInfoList);
					}
				}
				flag = res.getNextOpenid();
			}
		} while (count == 10000);
		outputObject.setObject(allUser);
		outputObject.setBean(paramMap);
		return outputObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OutputObject insertBlackList(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appId = inputObject.getValue("appId");
		String secret = inputObject.getValue("secret");
		ApiConfig config = new ApiConfig(appId, secret);
		UserAPI userAPI = new UserAPI(config);
		List<String> openidList = (List<String>) inputObject.getParams().get("openidList");
		ResultType result = userAPI.batchBlackToUser(openidList);
		if(result.getCode() == ResultType.SUCCESS.getCode()) {
			outputObject.setReturnMessage("操作成功");
		} else {
			outputObject.setReturnMessage(result.getDescription());
		}
		return outputObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OutputObject deleteBlackList(InputObject inputObject, OutputObject outputObject) throws Exception {
		String appId = inputObject.getValue("appId");
		String secret = inputObject.getValue("secret");
		ApiConfig config = new ApiConfig(appId, secret);
		UserAPI userAPI = new UserAPI(config);
		List<String> openidList = (List<String>) inputObject.getParams().get("openidList");
		ResultType result = userAPI.batchDeleteBlackToUser(openidList);
		if(result.getCode() == ResultType.SUCCESS.getCode()) {
			outputObject.setReturnMessage("操作成功");
		} else {
			outputObject.setReturnMessage(result.getDescription());
		}
		return outputObject;
	}
	
	/**
	 * @param o1 第一个用户信息对象,数据库获取的
	 * @param o2 第二个用户信息对象
	 * @return
	 */
	private boolean compareUser(Map<String,Object> o1, GetUserInfoResponse o2){
		return !((o2.getNickname() == null ? "" : o2.getNickname()).equals(o1.get("nickName") == null ? "" : o1.get("nickName"))) ? false :
			!((o2.getSubscribeTime() == null ? "" : o2.getSubscribeTime()).equals(o1.get("subscribeTime") == null ? "" : ((Date)o1.get("subscribeTime")).getTime() / 1000)) ? false :
			!((o2.getHeadimgurl() == null ? "" : o2.getHeadimgurl()).equals(o1.get("headerImage") == null ? "" : o1.get("headerImage"))) ? false :
			!((o2.getSex() == null ? "" : o2.getSex()).toString().equals(o1.get("gender") == null ? "" : o1.get("gender").toString())) ? false :
			!((o2.getLanguage() == null ? "" : o2.getLanguage()).equals(o1.get("LANGUAGE") == null ? "" : o1.get("LANGUAGE"))) ? false :
			!((o2.getCity() == null ? "" : o2.getCity()).equals(o1.get("CITY") == null ? "" : o1.get("CITY"))) ? false :
			!((o2.getCountry() == null ? "" : o2.getCountry()).equals(o1.get("COUNTRY") == null ? "" : o1.get("COUNTRY"))) ? false : 
			!(o2.getSubscribe().toString().equals(o1.get("subscribe").toString())) ? false : 
			!((o2.getProvince() == null ? "" : o2.getProvince()).equals(o1.get("PROVINCE") == null ? "" : o1.get("PROVINCE"))) ? false : true;
	}

	@Override
	public int updateByOpenId(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().update("WXUserMapper.updateByOpenId", inputObject.getParams());
	}
	
	//将list按blockSize大小等分，最后多余的单独一份  
    public static List<List<UserInfo>> subList(List<UserInfo> list, int blockSize) {  
        List<List<UserInfo>> lists = new ArrayList<List<UserInfo>>();  
        if (list != null && blockSize > 0) {  
            int listSize = list.size();  
            if(listSize<=blockSize){  
                lists.add(list);  
                return lists;  
            }  
            int batchSize = listSize / blockSize;  
            int remain = listSize % blockSize;  
            for (int i = 0; i < batchSize; i++) {  
                int fromIndex = i * blockSize;  
                int toIndex = fromIndex + blockSize;  
                lists.add(list.subList(fromIndex, toIndex));  
            }  
            if(remain>0){  
                System.out.println("fromIndex=" + (listSize-remain) + ", toIndex=" + (listSize));  
                lists.add(list.subList(listSize-remain, listSize));  
            }  
        }  
        return lists;  
    }

	@Override
	public void getUserInfoByOpenId(InputObject inputObject, OutputObject outputObject) throws Exception {
		Object object = getBaseDao().queryForObject("WXUserMapper.getUserInfoByOpenId", inputObject.getParams());
		outputObject.setObject(object);
	}
}
