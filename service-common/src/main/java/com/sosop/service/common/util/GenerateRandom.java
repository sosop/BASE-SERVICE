package com.sosop.service.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * @description 随机生成需要的字符窜
 * @author xiaolong.hou
 * 
 */
public class GenerateRandom {

	/**
	 * @description 生成唯一的UUID
	 * @return
	 */
	public static String uuidGenerator() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	/**
	 * ＠description 随即生成字符串
	 * @param length 生成字符串的长度
	 * @return
	 */
	public static String randomStrGenerator(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < length; i++) {
			int num = random.nextInt(62);
			buf.append(str.charAt(num));
		}

		return buf.toString();
	}
}
