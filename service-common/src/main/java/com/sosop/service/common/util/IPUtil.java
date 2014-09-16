package com.sosop.service.common.util;


public class IPUtil {
	/*public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null) {
			ip = request.getRemoteAddr();
		}
		if (!checkIP(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!checkIP(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!checkIP(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}*/

	public static boolean checkIP(String ip) {
		if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
				|| ip.split(".").length != 4) {
			return false;
		}
		return true;
	}
}
