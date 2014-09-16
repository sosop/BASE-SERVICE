package com.sosop.service.common.util;

public class FiltersUtil {
	public static String fileFilter(String file) {
		String result = file.replaceAll("/", "")
							.replaceAll(" ", "")
							.replaceAll("　", "");
		return result;
	}
}
