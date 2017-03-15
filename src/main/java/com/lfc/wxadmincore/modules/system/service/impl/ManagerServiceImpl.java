package com.lfc.wxadmincore.modules.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.wxadmincore.common.constant.Constants;
import com.lfc.wxadmincore.common.service.BaseServiceImpl;
import com.lfc.wxadmincore.common.utils.DateUtil;
import com.lfc.wxadmincore.common.utils.MD5Util;
import com.lfc.wxadmincore.common.utils.StringUtil;
import com.lfc.wxadmincore.modules.system.service.IManagerService;
import com.lfc.wxadmincore.modules.system.service.IMenuService;

public class ManagerServiceImpl extends BaseServiceImpl implements
		IManagerService {

	private IMenuService menuService;

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int login(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// TODO Auto-generated method stub
		// 用户密码 MD5 加密
		inputObject.getParams().put(
				"passWord",
				MD5Util.getMD5(inputObject.getParams().get("passWord")
						.toString()));
		Map<String, Object> managerObj = (Map<String, Object>) getBaseDao()
				.queryForObject("ManagerMapper.login", inputObject.getParams());
		if (managerObj != null) {
			if (!(((BigDecimal) managerObj.get("status"))
					.compareTo(new BigDecimal("-1")) == 0)) {
				// 查询用户的角色ID集合
				inputObject.getParams().put("userId", managerObj.get("id"));
				List<Map<String, Object>> roleList = getBaseDao().queryForList(
						"RoleMapper.queryRolesByUserId",
						inputObject.getParams());
				// 查询用户权限集合
				List<Map<String, Object>> menuList = menuService
						.queryMenuTreeForUser(inputObject, outputObject);
				// 用户信息，角色ID集合，权限信息返回
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("USER", managerObj);
				result.put("ROLEIDS", roleList);
				result.put("PRIVILEGES", menuList);
				outputObject.setBean(result);
				outputObject
						.setReturnCode(Constants.SYSTEM_LOGIN_STATE.STATE_0);
				outputObject.setReturnMessage("登录成功.");
			} else {
				outputObject
						.setReturnCode(Constants.SYSTEM_LOGIN_STATE.STATE_2);
				outputObject.setReturnMessage("用户账号已经被冻结.");
			}
		} else {
			outputObject.setReturnCode(Constants.SYSTEM_LOGIN_STATE.STATE_N_1);
			outputObject.setReturnMessage("用户名或密码错误.");
		}
		return 1;
	}

	@Override
	public void getList(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		List<Map<String, Object>> list = getBaseDao().queryForList(
				"ManagerMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int count = getBaseDao().getTotalCount(
				"ManagerMapper.countTotalManager", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		// 总数量
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
	}

	@Override
	public int insertManager(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// 查询用户名是否存在
		int count = getBaseDao().getTotalCount("ManagerMapper.countByUserName",
				inputObject.getParams());
		if (count == 0) {
			inputObject.getParams().put("id", StringUtil.getSquence());
			inputObject.getParams().put("deleteFlag", 0);
			inputObject.getParams().put("createTime", new Date());
			inputObject.getParams().put(
					"validBeginTime",
					DateUtil.string2Date2(inputObject.getParams()
							.get("validBeginTime").toString()));
			inputObject.getParams().put(
					"validEndTime",
					DateUtil.string2Date2(inputObject.getParams()
							.get("validEndTime").toString()));
			inputObject.getParams().put(
					"birthday",
					DateUtil.string2Date2(inputObject.getParams()
							.get("birthday").toString()));
			inputObject.getParams().put("status", 1);
			// 用户密码 MD5 加密
			inputObject.getParams().put(
					"passWord",
					MD5Util.getMD5(inputObject.getParams().get("passWord")
							.toString()));
			// 新增用户
			int flag = getBaseDao().insert("ManagerMapper.insertManager",
					inputObject.getParams());
			if (flag > 0) {
				inputObject.getParams().put("userId",
						inputObject.getParams().get("id"));
				inputObject.getParams().put("id", StringUtil.getSquence());
				// 新增用户属性
				flag = getBaseDao().insert("ManagerMapper.insertUserProperty",
						inputObject.getParams());
				//if (flag > 0) {
					// 新增用户职位
					//flag = getBaseDao().insert("ManagerMapper.insertUserPosition", inputObject.getParams());
					if (flag > 0) {
						// 新增部门用户
						flag = getBaseDao().insert(
								"ManagerMapper.insertDepartUser",
								inputObject.getParams());
						if (flag > 0) {
							outputObject.setReturnCode("0");
							outputObject.setReturnMessage("添加用户信息成功");
							return 1;
						}
					}
				//}
			}
			outputObject.setReturnCode("-9999");
			outputObject.setReturnMessage("添加用户信息失败");
			return 0;
		} else {
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("用户名已经存在，请修改!");
			return 0;
		}
	}

	@Override
	public void getById(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// 根据ID,查询用户所有信息
		Object manager = getBaseDao().queryForObject("ManagerMapper.getById",
				inputObject.getParams());
		outputObject.setObject(manager);
	}

	@Override
	public int updateManager(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// 查询用户名是否存在
		int count = getBaseDao().getTotalCount("ManagerMapper.countByUserName",
				inputObject.getParams());
		if (count == 0) {
			inputObject.getParams().put("updateTime", new Date());
			inputObject.getParams().put(
					"validBeginTime",
					DateUtil.string2Date2(inputObject.getParams()
							.get("validBeginTime").toString()));
			inputObject.getParams().put(
					"validEndTime",
					DateUtil.string2Date2(inputObject.getParams()
							.get("validEndTime").toString()));
			inputObject.getParams().put(
					"birthday",
					DateUtil.string2Date2(inputObject.getParams()
							.get("birthday").toString()));
			// 修改用户
			int flag = getBaseDao().update("ManagerMapper.updateManager",
					inputObject.getParams());
			if (flag >= 0) {
				inputObject.getParams().put("userId",
						inputObject.getParams().get("id"));
				// 修改用户属性
				flag = getBaseDao().update("ManagerMapper.updateUserProperty",
						inputObject.getParams());
				//if (flag >= 0) {
					// 修改用户职位
					//flag = getBaseDao().update("ManagerMapper.updateUserPosition", inputObject.getParams());
					if (flag >= 0) {
						// 修改部门用户
						flag = getBaseDao().update(
								"ManagerMapper.updateDepartUser",
								inputObject.getParams());
						if (flag >= 0) {
							outputObject.setReturnCode("0");
							outputObject.setReturnMessage("修改用户信息成功");
							return 1;
						}
					}
				//}
			}
			outputObject.setReturnCode("-9999");
			outputObject.setReturnMessage("修改用户信息失败");
			return 0;
		} else {
			outputObject.setReturnCode("-1");
			outputObject.setReturnMessage("用户名已经存在，请修改!");
			return 0;
		}
	}

	@Override
	public int updateManagerObj(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		return getBaseDao().update("ManagerMapper.updateManager",
				inputObject.getParams());
	}

	@Override
	public void checkPassword(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// 用户密码 MD5 加密
		inputObject.getParams().put(
				"passWord",
				MD5Util.getMD5(inputObject.getParams().get("passWord")
						.toString()));
		int count = getBaseDao().getTotalCount(
				"ManagerMapper.checkPasswordIsTrue", inputObject.getParams());
		Map<String, Object> beanMap = new HashMap<String, Object>();
		beanMap.put("count", count);
		outputObject.setBean(beanMap);
	}

	@Override
	public void editPassWord(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// 用户密码 MD5 加密
		if (!"".equals(inputObject.getParams().get("passWord"))
				&& inputObject.getParams().get("passWord") != null) {
			inputObject.getParams().put(
					"passWord",
					MD5Util.getMD5(inputObject.getParams().get("passWord")
							.toString()));
		}
		int flag = getBaseDao().update("ManagerMapper.updateManager",
				inputObject.getParams());
		//之前方案    beg
//		if (flag > 0) {
//			outputObject.setReturnMessage("密码修改成功");
//		} else {
//			outputObject.setReturnMessage("密码修改失败");
//		}
		//之前方案   end
		
		//之后方案，用户在修改密码时也可以修改手机号码     zhaoyan 添加   beg
		if (flag>0) {
			//手机号码不为空执行更新操作
			if (StringUtil.isNotEmpty(String.valueOf(inputObject.getParams().get("phoneNumber")))) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userId", inputObject.getParams().get("id"));
				map.put("phoneNumber", inputObject.getParams().get("phoneNumber"));
				System.out.println(map+"--------------------->core层参数");
				flag = getBaseDao().update("ManagerMapper.updateUserProperty",map);
				if (flag>0) {
					outputObject.setReturnMessage("个人信息修改成功");
				}else {
					outputObject.setReturnMessage("个人信息修改失败");
				}
				
			}else {
				outputObject.setReturnMessage("个人信息修改成功");
			}
		}else {
			outputObject.setReturnMessage("个人信息修改失败");
		}
		//之后方案，用户在修改密码时也可以修改手机号码     zhaoyan 添加   end
		
		
		
	}

	@Override
	public OutputObject getUserRoleCodesByOpenId(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String result = "";
		List<Map<String, Object>> list = getBaseDao().queryForList(
				"ManagerMapper.getUserRoleCodesByOpenId",
				inputObject.getParams());
		for (Map<String, Object> map : list) {
			result += map.get("ROLE_CODE").toString() + ",";
		}

		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		outputObject.setReturnCode(result);
		return outputObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OutputObject getUserDepartCodeByOpenId(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String result = "";
		Map<String, Object> map = (Map<String, Object>) getBaseDao().queryForObject("ManagerMapper.getUserDepartCodeByOpenId",
						inputObject.getParams());
		if (map != null) {
			result = map.get("DEPART_CODE").toString();
		}
		outputObject.setReturnCode(result);
		return outputObject;
	}

	@Override
	public int updateManagerCodeImg(InputObject inputObject, OutputObject outputObject) throws Exception {
		
		logger.debug(inputObject.getParams()+"-------------->");
		int flag = getBaseDao().update("ManagerMapper.updateUserProperty", inputObject.getParams());

		if (flag>0) {
			outputObject.setReturnCode("0");
			return flag;
		}else {
			outputObject.setReturnCode("-1");
			return 0;
		}
	}
	
	
	


	/* 
	 * 
	 * 导入普通用户信息数据
	 * 
	 */
	@Transactional
	@Override
	public OutputObject importUserInfo(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		userList = (List<Map<String, Object>>) inputObject.getParams().get(
				"managerList");
		// bean在这里存放无法插入进数据库的数据
		Map<String, Object> bean = new HashMap<String, Object>();

		if (!CollectionUtils.isEmpty(userList)) {

			inputObject.getParams().put("roleCode", "ROLE_PTYG");
			Map<String, Object> object = (Map<String, Object>) getBaseDao().queryForObject("RoleMapper.selectRoleIdByRoleCode",
							inputObject.getParams());
			
			for (Map<String, Object> userMap : userList) {
				if (getBaseDao().getTotalCount("ManagerMapper.countByUserName",userMap) == 0) {
					userMap.put("id", StringUtil.getSquence());
					userMap.put("deleteFlag", 0);
					userMap.put("createTime", new Date());
					userMap.put("status", 1);
					userMap.put("passWord",
							MD5Util.getMD5("123456"));
					
					// 新增用户
					int flag = getBaseDao().insert(
							"ManagerMapper.insertManager",
							userMap);
					if (flag > 0) {
						userMap.put("userId",userMap.get("id"));
						userMap.put("id",StringUtil.getSquence());
						// 新增用户属性
						flag = getBaseDao().insert(
								"ManagerMapper.insertUserProperty",
								userMap);
						if (flag > 0) {
							if (!"".equals(object.get("roleId")) && object.get("roleId") != null) {
								userMap.put("roleId", object.get("roleId"));
							}
							flag = getBaseDao().insert("ManagerMapper.insertRoleUser",userMap);
							if (flag > 0) {
								// 新增部门用户
								flag = getBaseDao().insert("ManagerMapper.insertDepartUser",userMap);
								if (flag > 0) {
									outputObject.setReturnCode("0");
									outputObject.setReturnMessage("添加用户信息成功");
									return outputObject;
								}
							}

						}
						outputObject.setReturnCode("-9999");
						outputObject.setReturnMessage("添加用户信息失败");
						return outputObject;
					} else {
						outputObject.setReturnCode("-1");
						outputObject.setReturnMessage("用户名已经存在，请修改!");
						return outputObject;
					}
				} else {
					// 数据存在该账户名称里面是已经存在的用户数据，已经存在的数据不插入。
					bean.putAll(userMap);
				}
			}
		}
		return outputObject;
	}
	
	@Override
	public void getUserDepartCodeByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("ManagerMapper.getUserDepartCodeByUserId", inputObject.getParams());
		outputObject.setBeans(list);
	}

	@Override
	public void getTask(InputObject inputObject,OutputObject outputObject) throws Exception {
		String depart = "";
		String roleCode = (String)inputObject.getParams().get("roleCode");//角色编码
		logger.debug("---------roleCode----------"+roleCode);
		String departId = (String)inputObject.getParams().get("departId");//部门号
		inputObject.getParams().put("parentId", departId);
		List<Map<String, Object>> departList = getBaseDao().queryForList("ManagerMapper.getInfoByParentId", 
				inputObject.getParams());
		
		for (Map<String, Object> map2 : departList) {//拼接-该用户管辖内-机构号
			if(map2.get("id").toString().contains("N")){
				inputObject.getParams().put("parentId", map2.get("id"));
				List<Map<String, Object>> departTwo = getBaseDao().queryForList("ManagerMapper.getInfoByParentId", 
						inputObject.getParams());
				for (Map<String, Object> map3 : departTwo) {
					depart += map3.get("id").toString().trim() + ",";
				}
			}else{
				depart += map2.get("id").toString().trim() + ",";
			}
		}
		depart = depart.substring(0,depart.lastIndexOf(","));
		inputObject.getParams().put("departId", depart.split(","));
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+inputObject.getParams().get("departId"));
		List<Map<String,Object>> list = getBaseDao().queryForList("ManagerMapper.getTask", inputObject.getParams());
		outputObject.setBeans(list);
	}
	
	@Override
	public void getUserByOpenId(InputObject inputObject,OutputObject outputObject) throws Exception {
		List<Map<String,Object>> list = getBaseDao().queryForList("ManagerMapper.getUserByOpenId", inputObject.getParams());
		outputObject.setBeans(list);
	}
	
	@Override
	public void getUserInfo(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// 根据ID,查询用户所有信息
		Object manager = getBaseDao().queryForObject("ManagerMapper.getUserInfo",
				inputObject.getParams());
		outputObject.setObject(manager);
	}
	
	@Override
	public void getRolesByUserId(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		// 根据ID,查询该用户所拥有的角色
		List<Map<String, Object>> list = getBaseDao().queryForList("RoleMapper.queryRolesByUserId",
				inputObject.getParams());
		outputObject.setBeans(list);
	}

	@Override
	public void deleteRUById(InputObject inputObject, OutputObject outputObject) throws Exception {
		int flag=getBaseDao().delete("ManagerMapper.deletetRUByUserId", inputObject.getParams());
		if(flag>0){
              outputObject.setReturnMessage("删除成功");		
		}else{
			  outputObject.setReturnMessage("删除失败");
		}
	}

	@Override
	public void insertRoleUserInfo(InputObject inputObject, OutputObject outputObject) throws Exception {
		this.deleteRUById(inputObject, outputObject);
		int flag=getBaseDao().insert("RoleMapper.insertRoleUserInfo", inputObject.getParams().get("roleUserForm"));
		if(flag>0){
              outputObject.setReturnMessage("添加成功");		
		}else{
			  outputObject.setReturnMessage("添加失败");
		}
		
	}
}
