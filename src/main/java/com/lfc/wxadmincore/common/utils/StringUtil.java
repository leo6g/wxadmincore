package com.lfc.wxadmincore.common.utils;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

import oracle.sql.CLOB;

/**
 */
public final class StringUtil {
	/** Private Constructor **/
	
	
	/**
	 * 判断字符串是否非null && 非空字符 
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isNotEmpty(String param) {
		return param != null && !"".equals(param.trim());
	}

	/**
	 * 判断字符串是否为null || 空字符串
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isEmpty(String param) {
		return param == null || "".equals(param.trim());
	}
	
	/**
	 * 判断是否为数字类�?
	 * @param str
	 * @return True为数字类�?
	 */
	public static boolean isNum(String str) {
		String regex = "^(-?\\d+)(\\.\\d+)?$";
		return matchRegex(str, regex);
	}
	/**
	 * 生成uuid
	 * @return
	 */
	public static String getSquence() {
		String rtVal = null;
		rtVal = UUID.randomUUID().toString();
		rtVal = rtVal.replaceAll("-", "");
		return rtVal;
	}
				
	private static boolean matchRegex(String value, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	public static String unescapeHtml4(String value){
		return StringEscapeUtils.unescapeHtml4(value);
	}
	
	
	public static String ClobToString(CLOB clob)  { 
		String rtVal = ""; 
		try{
			Reader is = clob.getCharacterStream();// 得到流 
			BufferedReader br = new BufferedReader(is); 
			String s = br.readLine(); 
			StringBuffer sb = new StringBuffer(); 
			while (s != null) {// 执行循环将字符串全部取出付值给 StringBuffer由StringBuffer转成STRING 
			sb.append(s); 
			s = br.readLine(); 
			} 
			rtVal = sb.toString(); 
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return rtVal; 
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtil.getSquence());
	}
}
