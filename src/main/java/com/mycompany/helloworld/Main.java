package com.mycompany.helloworld;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);
	
	private int zip;
	
	public Main(int zip){
		this.zip = zip;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		logger.info("Starting...");
		
		PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));
		
		int zipcode = 60202;
		
		try{
			zipcode = Integer.parseInt(args[0]);
		}catch(Exception e){
			logger.error(e);
		}
		
		new Main(zipcode).start();
	}
	
	public void start() throws Exception{
		InputStream dataIn = new YahooRetriever().retrieve(zip);
		
		Weather weather = new YahooParser().parse(dataIn);
		
		logger.info("Weather : " + new WeatherFormatter().format(weather));
	}

}
