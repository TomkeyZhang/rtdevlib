package com.anjuke.devlib.utils;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class HttpRequest {

	// TODO: refact
	public static String post(String host, List<BasicNameValuePair> params, String encoding) {
		HttpPost httpPost = new HttpPost(host);
		try {

			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
			HttpConnectionParams.setSoTimeout(httpParams, 5000);

			DefaultHttpClient client = new DefaultHttpClient(httpParams);

			UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(params, encoding);
			httpPost.setEntity(p_entity);
			HttpResponse response = client.execute(httpPost);

			String result = "";
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == 200) {
				result = EntityUtils.toString(response.getEntity(), encoding);
			}

			return result;

		} catch (Exception e) {

			return "";
		}
	}

	public static String get(String host, String params, String encoding) {

		HttpGet request = new HttpGet(host + "?" + params);

		try {
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
			HttpConnectionParams.setSoTimeout(httpParams, 5000);

			DefaultHttpClient client = new DefaultHttpClient(httpParams);
			HttpResponse response = client.execute(request);

			String result = "";
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				result = EntityUtils.toString(response.getEntity(), encoding);
			}
			return result;
		} catch (Exception e) {
			return "";
		}
	}
}
