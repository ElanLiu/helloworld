package com.mycompany.tbchannel;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;

public class HttpTest {

	private static final Logger logger = Logger.getLogger(HttpTest.class);

	private static final String URL = "http://10.249.201.56/api/activity";

	private static HttpClient client = new HttpClient();;

	private static PostMethod post;

	private static GetMethod getMethod;

	private static int code = 200;

	private static String requestURL;

	public static void httpAssemble() {
		client = new HttpClient();

		post = new PostMethod(requestURL);

		getMethod = new GetMethod(requestURL);

		HttpState initialState = new HttpState();

		Cookie cookie = new Cookie();

		cookie.setDomain("www.tmall.com");

		cookie.setPath("/");

		cookie.setName("_tb_token_");

		cookie.setValue("j8Z6y5hZamll");

		initialState.addCookie(cookie);

		client.setState(initialState);

	}

	public static void URLAssemble(String str) {
		if (str.equals("user"))
			requestURL = URL + "/dianxinHeyue/userState";
		else if (str.equals("list"))
			requestURL = URL + "/guestbook/list";

		else if (str.equals("add"))
			requestURL = URL + "/guestbook/add";
		else
			return;

	}

	@Test
	public void httpTest() {

		URLAssemble("list");

		logger.info(requestURL);

		httpAssemble();

		String body = null;

		int statusCode = 0;

		try {
			//statusCode = client.executeMethod(getMethod);

			//body = getMethod.getResponseBodyAsString();

			//logger.info(statusCode);

			// ==========================

			NameValuePair[] postData = new NameValuePair[3];

			postData[0] = new NameValuePair("onlyself", "0");

			postData[1] = new NameValuePair("page", "1");

			postData[2] = new NameValuePair("activityId", "10001");

			post.setRequestBody(postData);

			statusCode = client.executeMethod(post);

			//assertEquals(statusCode, code);

			body = post.getResponseBodyAsString();

			logger.info(body);

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
