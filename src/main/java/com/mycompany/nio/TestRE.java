package com.mycompany.nio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;


public class TestRE {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		String regex = "\\(?0\\d{2}[), ,-]?\\d{3}";
		
		String regex2 = "^139\\d{8}|\\d{4}\\-\\d{8}\\w+";
		
		String input = "0202-11111111ddd";

		Pattern p = Pattern.compile(regex2);
		
		Matcher m = p.matcher(input);
		
		while(m.find()){
			System.out.print(m.group());
		}

	}

}
