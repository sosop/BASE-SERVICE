package com.sosop.service.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Properties {

	private Map<String, String> container;

	public Properties() {
		container = new LinkedHashMap<>();
	}

	public void load(String filePath) {
		String kvs = FileUtil.read(filePath);
		this.parse(kvs);
	}

	private void parse(String kvs) {
		String[] lines = kvs.split("\n");
		for (String line : lines) {
			if (!"".equals(line.trim())) {
				String[] kv = line.split("=");
				if (kv.length >= 2) {
					container.put(kv[0].trim(), kv[1].trim());
				} else {
					container.put(kv[0], "");
				}
			}
		}
	}
	
	public String getValue(String key) {
		return container.get(key);
	}
	
	public Map<String, String> getContainer() {
		return this.container;
	}
}