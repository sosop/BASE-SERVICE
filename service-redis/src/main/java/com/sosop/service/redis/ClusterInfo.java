package com.sosop.service.redis;

import java.util.Iterator;
import java.util.Map.Entry;

import com.sosop.service.common.util.Properties;

public class ClusterInfo {
	
	private final static String DEFAUL_PATH = "redis-cluster.properties";
	
	private Properties prop;
	
	public ClusterInfo() {
		this.prop = new Properties();
		prop.load(DEFAUL_PATH);
	}
	
	public ClusterInfo(String path) {
		this.prop = new Properties();
		prop.load(path);
	}
	
	public ClusterInfo(Properties prop) {
		this.prop = prop;
	}
	
	public void config() {
		for (Entry<String, String> entry : prop.getContainer().entrySet()) {
			System.out.println(entry.getKey() + "  =  " + entry.getValue());
		}
	}
}