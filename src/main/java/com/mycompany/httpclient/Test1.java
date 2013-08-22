package com.mycompany.httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

public class Test1 {
	
	private static final Logger logger = Logger.getLogger(Test1.class);
	
	private static HttpClient client = new HttpClient();
	
	private GetMethod method;
	
	private static PostMethod post;
	
	public static void HttpAssemble(){
		post = new PostMethod("http://10.249.201.56:8080/api/activity/guestbook/list");
		
		NameValuePair onlyself = new NameValuePair("onlyself", "0");
		NameValuePair page = new NameValuePair("page", "1");
		NameValuePair activityId = new NameValuePair("activityId", "10001");
		
		NameValuePair[] data = new NameValuePair[3];
		
		post.setRequestBody(new NameValuePair[]{onlyself, page, activityId});
		//post.addParameter(param)
	}

	public static void main(String args[]){
		 HttpAssemble();
		try {
			int statusCode = client.executeMethod(post);
			//System.out.print(statusCode);
			
			String re = post.getResponseBodyAsString();
			System.out.print(re);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
	}


}
