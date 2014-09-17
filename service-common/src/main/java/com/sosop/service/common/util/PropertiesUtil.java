package com.sosop.service.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	
	private final static Logger LOG = Logger.getLogger(PropertiesUtil.class);
	
	public static Properties getProp(String path) {
		Properties prop = new Properties();
		try(InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(path)) {
			prop.load(in);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return prop;
	}
}
