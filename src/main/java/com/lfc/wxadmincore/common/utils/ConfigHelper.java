package com.lfc.wxadmincore.common.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {

    public static final Properties prop = new Properties();

    static {
	try {
	    InputStream is = ConfigHelper.class
		    .getResourceAsStream("/config/jdbc.properties");
	    prop.load(is);
	    is.close();

	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public static String getValue(String key) {
	return prop.getProperty(key);
    }
}
