package com.sosop.service.common.util;
/**
 * 
 * @author xiaolong.hou
 * 便于字符串拼接
 */
public class StringUtil {
	
	public static String append(String begin, String ... args) {
		StringBuffer buf = new StringBuffer(begin);
		if(args != null && args.length > 0) {
			for (String str : args) {
				buf.append(str);
			}
		}
		return buf.toString();
	}
	
	public static boolean isNull(String ...str) {
		boolean nil = true;
		if(null != str && str.length > 0) {
			for (String s : str) {
				// 只要有一个元素不为空，那么数组就不为空
				if(null != s && !"".equals(s.trim())) {
					nil = false; 
					break;
				}
			}
		}
		return nil;
	}
}