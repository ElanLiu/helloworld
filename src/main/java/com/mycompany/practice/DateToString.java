package com.mycompany.practice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateToString {
	
	private static final Logger logger = Logger.getLogger(DateToString.class);
	
	
	/**
	 * SimpleDateFormat 使得可以选择任何用户定义的日期-时间格式的模式。
	 * 但是，仍然建议通过 DateFormat 中的 getTimeInstance、getDateInstance 
	 * 或 getDateTimeInstance 来创建日期-时间格式器。
	 * 每一个这样的类方法都能够返回一个以默认格式模式初始化的日期/时间格式器。
	 * 可以根据需要使用 applyPattern 方法来修改格式模式。
	 * 
	 * @param date
	 * @param pattern
	 * @return String dateToString
	 */
	public static String dateToString(Date date, String pattern){
		if(null == date){
			logger.error("The parameter of date is null");
			return null;
		}
		String defaultPattern = "yyyy-MM-dd HH:mm:ss SSS EE";
		
		SimpleDateFormat formatter = (SimpleDateFormat) DateFormat.getDateInstance();
		
		if(null == pattern || pattern.equals("")){
			formatter.applyPattern(defaultPattern);
		}else{
			try{
				formatter.applyPattern(pattern);
			}catch(IllegalArgumentException e){
				logger.error("The parameter of pattern is illegal.", e);
				return null;
			}
		}
		String dateString = formatter.format(date);
		
		logger.info("The date is: " + dateString);
		
		return dateString;
	}
	
	/**
	 * string转换为date，不需要考虑格式问题，只要成为Date对象即可。
	 * @param stringDate
	 * @return Date stringToDate
	 */
	public static Date stringToDate(String stringDate, String pattern){
		if(null == stringDate || stringDate.equals("")){
			return new Date();
		}
		
		String defaultPattern = "yyyy-MM-dd HH:mm:ss SSS EE";
		if(null == pattern || pattern.equals("")){
			pattern = defaultPattern;
		}
		
		Date date = null;
		SimpleDateFormat formatter = (SimpleDateFormat) DateFormat.getDateInstance();
		try {
			date = formatter.parse(stringDate);
			logger.info("The date is: " + date.toString());
		} catch (ParseException e) {
			logger.error("The parameters is illegal.", e);
		}
		return date;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = stringToDate("2013-01-02 12:12:12", "");
		
		System.out.print(date.toString());

		dateToString(date, null);

		
		
		
	}

}
