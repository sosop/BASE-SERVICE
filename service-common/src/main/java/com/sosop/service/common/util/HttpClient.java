package com.sosop.service.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
	
	
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // 从上述SSLContext对象中得到SSLSocketFactory对象  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            System.out.println("返回的数据："+buffer.toString());
       //     jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
        	LOG.error("Weixin server connection timed out.");  
        } catch (Exception e) {  
        	LOG.error("https request error:{}", e);  
        }  
        return buffer.toString();  
    }
}

