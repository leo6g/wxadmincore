package com.lfc.wxadmincore.common.control;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.core.service.IControlService;
import com.lfc.core.util.ControlConstants;
import com.lfc.core.util.JsonUtil;
import com.lfc.wxadmincore.common.utils.StringUtil;
/**
 * 后台服务统一接入
 */
public class ControlServiceImpl implements IControlService, BeanFactoryAware {
	private static final Logger logger =  LoggerFactory.getLogger(ControlServiceImpl.class);

	private BeanFactory factory;

	public void setBeanFactory(BeanFactory factory) {
		this.factory = factory;
	}

	public OutputObject execute(InputObject inputObject) {
		OutputObject outputObject = new OutputObject(ControlConstants.RETURN_CODE.IS_OK);
		long start = System.currentTimeMillis();
		try {
			outputObject.setReturnCode(ControlConstants.RETURN_CODE.IS_OK);
			if (inputObject != null) {
				logger.info("INVOKE START!", "service=" + inputObject.getService() + "| method=" + inputObject.getMethod()+"|input="+ JsonUtil.convertObject2Json(inputObject));
				Object object = factory.getBean(inputObject.getService());
				Method mth = object.getClass().getMethod(inputObject.getMethod(), InputObject.class, OutputObject.class);
				mth.invoke(object, inputObject ,outputObject);
				
				logger.info("INVOKE SECCESS!", "service=" + inputObject.getService() + "| method=" + inputObject.getMethod()+"|output="+JsonUtil.convertObject2Json(outputObject)+"|COST="+(System.currentTimeMillis() - start)+"ms");
			} else {
				throw new Exception("InputObject can't be null !!!");
			}
		} catch (Exception e) {
			if (inputObject != null && inputObject.getLogParams() != null && StringUtil.isNotEmpty(inputObject.getLogParams().get("OP_REQST_NO"))) {
				logger.error("execute INVOKE ERROR! ", "OP_REQST_NO = " + inputObject.getLogParams().get("OP_REQST_NO") + ",  JSON = "+ JsonUtil.convertObject2Json(inputObject) , e);
			} else {
				logger.error("execute INVOKE ERROR! ", "JSON = "+ JsonUtil.convertObject2Json(inputObject), e);
			}
			e.printStackTrace();
			// 异常处理
			outputObject.setReturnCode(ControlConstants.RETURN_CODE.SYSTEM_ERROR);
			outputObject.setReturnMessage("service异常");
			
		} finally{
			//saveUserOperLog(start, System.currentTimeMillis(), inObj, outObj);
		}
		return outputObject;
	}
}
