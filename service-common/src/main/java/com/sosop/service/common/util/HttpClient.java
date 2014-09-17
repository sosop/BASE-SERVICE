package com.sosop.service.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * 
 * @author sosop
 * @date 2014.9.2 封装HTTP请求
 */
public class HttpClient {

	private final static Logger LOG = Logger.getLogger(HttpClient.class);

	public static class RESPONSE {
		public final static int OK = 200;
		public final static int NOT_FOUND = 404;
		public final static int SERVER_ERR = 500;
		public final static int REDIRECT = 302;
	}

	public static class CONTENTTYPE {
		public final static String TEXT = "text/plain";
		public final static String JSON = "application/json";
		public final static String HTML = "text/html";
		public final static String XML = "text/xml";
		public final static String JS = "application/x-javascript";
	}

	public static class METHOD {
		public final static String POST = "POST";
		public final static String GET = "GET";
		public final static String DELETE = "DELETE";
		public final static String PUT = "PUT";
	}

	public static byte[] request(String urlPath, String method, String params,
			String contentType) {
		byte[] response = new byte[512];
		StringBuffer sb = new StringBuffer(urlPath);
		PrintWriter out = null;
		try {
			if (HttpClient.METHOD.GET.equalsIgnoreCase(method)
					&& null != params) {
				sb.append("?").append(params);
			}

			URL url = new URL(sb.toString());
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();

			httpConn.setRequestMethod(method);
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setConnectTimeout(2000);
			httpConn.setUseCaches(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestProperty("Content-Type", contentType);

			if (HttpClient.METHOD.POST.equalsIgnoreCase(method)
					&& null != params) {
				out = new PrintWriter(httpConn.getOutputStream());
				out.write(params);
				out.flush();
			}
			int responseCode = httpConn.getResponseCode();
			if (responseCode == HttpClient.RESPONSE.OK) {
				httpConn.getInputStream().read(response);
			} else {
				LOG.error(responseCode);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (null != out) {
				out.close();
			}
		}
		return response;
	}

	public static byte[] request(String urlPath, String method,
			Map<String, Object> params, String contentType) {
		StringBuffer paramsStr = new StringBuffer();
		if (null != params) {
			for (Entry<String, Object> entry : params.entrySet()) {
				paramsStr.append(entry.getKey()).append("=")
						.append(entry.getValue()).append("&");
			}
			if (params != null && params.size() > 0) {
				paramsStr.deleteCharAt(paramsStr.length() - 1);
			}
		}
		return request(urlPath, method, paramsStr.toString(), contentType);
	}
}