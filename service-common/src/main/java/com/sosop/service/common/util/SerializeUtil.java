package com.sosop.service.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;


/**
 * 
 * @author sosop
 * 
 * 对象序列化
 *
 * @param <T>
 */
public class SerializeUtil<T> {
	
	private Logger logger = Logger.getLogger(SerializeUtil.class);
	
	public byte[] serialize(T object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		byte[] bytes = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			bytes = baos.toByteArray();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if(oos != null) {
					oos.close();
				}
				if(baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				// e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		return bytes;
	}

	@SuppressWarnings("unchecked")
	public T unserialize(byte[] bytes) {
		ObjectInputStream ois = null;
		ByteArrayInputStream bais = null;
		T obj = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			obj = (T)ois.readObject();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if(ois != null) {
					ois.close();
				}
				if(bais != null) {
					bais.close();
				}
			} catch (IOException e) {
				// e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		return obj;
	}
}