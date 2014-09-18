package com.sosop.service.common.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Properties {

	private Map<String, String> containerMap;
	
	private List<String> containerList;

	public Properties() {
		containerMap  = new LinkedHashMap<>();
		containerList = new ArrayList<>();
	}

	public void load(String filePath) {
		String kvs = FileUtil.read(filePath);
		this.parse(kvs);
	}

	private void parse(String kvs) {
		String[] lines = kvs.split("\n");
		for (String line : lines) {
			if (!"".equals(line.trim()) && !line.contains("#")) {
				containerList.add(line);
				String[] kv = line.split("=");
				if (kv.length >= 2) {
					containerMap.put(kv[0].trim(), kv[1].trim());
				} else {
					containerMap.put(kv[0], "");
				}
			}
		}
	}
	
	public String getValue(String key) {
		return containerMap.get(key);
	}
	
	public Map<String, String> getContainerMap() {
		return this.containerMap;
	}
	
	public List<String> getContainerList() {
		return this.containerList;
	}
}