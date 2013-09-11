package com.mycompany.practice;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.testng.annotations.Test;


public class ConvertorTest {
	
	private String str = "8";
	

	public static  void main(String args[]){
		List li;
		Object ob;
		
		String str1 = "a";
		String str2 = str1;
		str1 = "d";
		StringUtils.equalsIgnoreCase(str1, str2);
		
		System.out.println(StringUtils.equalsIgnoreCase(str1, str2));
		System.out.println(str1.equals(str2));
	}

}
