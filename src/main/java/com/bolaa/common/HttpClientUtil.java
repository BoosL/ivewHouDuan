package com.bolaa.common;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();

		httpPost.setConfig(requestConfig);
		try {
			if (null != jsonParam) {
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");

				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					str = EntityUtils.toString(result.getEntity(), "utf-8");

					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}
	
	/**
	 * post请求(用于key-value格式的参数)
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map params) {

		BufferedReader in = null;
		try {
			// 定义HttpClient
			@SuppressWarnings("deprecation")
			HttpClient client = new DefaultHttpClient();
			// 实例化HTTP方法
			HttpPost request = new HttpPost();
			request.setURI(new URI(url));

			// 设置参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String value = String.valueOf(params.get(name));
				nvps.add(new BasicNameValuePair(name, value));

				// System.out.println(name +"-"+value);
			}
			request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) { // 请求成功
				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}

				in.close();

				return sb.toString();
			} else { //
				System.out.println("状态码：" + code);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		
	}

	public static JSONObject httpPost(String url, String strParam) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(80000).setConnectTimeout(80000).build();

		httpPost.setConfig(requestConfig);
		try {
			if (null != strParam) {
				StringEntity entity = new StringEntity(strParam, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					str = EntityUtils.toString(result.getEntity(), "utf-8");

					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	public static JSONObject httpGet(String url) {
		JSONObject jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();

		HttpGet request = new HttpGet(url);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();

		request.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String strResult = EntityUtils.toString(entity, "utf-8");

				jsonResult = JSONObject.parseObject(strResult);
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		} finally {
			request.releaseConnection();
		}
		return jsonResult;
	}

	public static void main(String[] args) {
	}
}
