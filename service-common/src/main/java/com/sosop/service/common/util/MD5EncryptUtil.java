package com.sosop.service.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;


/**
 * 
 * @author xiaolong.hou
 * @description MD5对用户密码加密
 */
public class MD5EncryptUtil {
	
	private final static Logger LOG = Logger.getLogger(MD5EncryptUtil.class);
	
	/**
	 * @description 加密的算法
	 * @param plain
	 * @return
	 */
	public static String encryption(String plain) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plain.getBytes());
            byte b[] = md.digest();
 
            int i;
 
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();
 
        } catch (NoSuchAlgorithmException e) {
        	LOG.error(e.getMessage(), e);
        }
        return re_md5;
    }
}
