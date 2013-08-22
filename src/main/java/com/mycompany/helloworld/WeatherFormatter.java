package com.mycompany.helloworld;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class WeatherFormatter {
	
	private static final Logger logger = Logger.getLogger(WeatherFormatter.class);
	
	public String format(Weather weather) throws Exception {
		logger.info("66666666666");
/*		logger.info("Formating Weather Data");
		URL url = Main.class.getClassLoader().getResource("output.vm");
		Reader reader = new InputStreamReader(WeatherFormatter.class.getClassLoader().getResourceAsStream("output.vm"));
		logger.info("1111");
		VelocityContext context = new VelocityContext();
		
		context.put("weather", weather);
		
		StringWriter writer = new StringWriter();
		
		Velocity.evaluate(context, writer, "", reader);
		
		return writer.toString();*/
		
		return "weather: " + "\n" +
		"country=" + weather.getCountry() + "\n" +
		"city=" + weather.getCity() + "\n" +
		"temperature=" + weather.getTemp()  ;
	}

}
