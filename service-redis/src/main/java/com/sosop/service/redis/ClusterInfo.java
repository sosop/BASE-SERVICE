package com.sosop.service.redis;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import com.sosop.service.common.util.PropertiesUtil;

public class ClusterInfo {
	
	private final static String DEFAUL_PATH = "reids-cluster.properties";
	
	private Properties prop;
	
	public ClusterInfo() {
		this.prop = PropertiesUtil.getProp(DEFAUL_PATH);
	}
	
	public ClusterInfo(String path) {
		this.prop = PropertiesUtil.getProp(path);
	}
	
	public ClusterInfo(Properties prop) {
		this.prop = prop;
	}
	
	public void config() {
		Iterator<Entry<Object, Object>> it = prop.entrySet().iterator();
		while(it.hasNext()) {
			System.out.println(it.next().getKey() + "  " + it.next().getValue());
		}
	}
}