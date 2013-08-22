package com.mycompany.helloworld;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

public class YahooParser {
	
	private static final Logger logger = Logger.getLogger(YahooParser.class);
	
	public Weather parse(InputStream in) throws Exception{
		Weather weather = new Weather();
		
		logger.info("Creating XML Reader");
		
		SAXReader xmlReader = createXmlReader();
		
		Document doc = xmlReader.read(in);
		
		logger.info("Parsing XML Response");
		
		weather.setCity(doc.valueOf("/rss/channel/y:location/@city"));
		weather.setRegion(doc.valueOf("/rss/channel/y:location/@region"));
		weather.setCountry(doc.valueOf("/rss/channel/y:location/@country"));
		weather.setCondition(doc.valueOf("/rss/channel/y:location/@text"));
		weather.setTemp(doc.valueOf("/rss/channel/y:location/@temp"));
		weather.setChill(doc.valueOf("/rss/channel/y:location/@chill"));
		weather.setHumidity(doc.valueOf("/rss/channel/y:location/@humidity"));
		
		logger.info(weather.toString());
		return weather;
	}

	private SAXReader createXmlReader() {
		Map<String, String> uris = new HashMap<String, String>();
		
		uris.put("y", "http://xml.weather.yahoo.com/ns/rss/1.0");
		
		DocumentFactory factory = new DocumentFactory();
		factory.setXPathNamespaceURIs(uris);
		
		SAXReader xmlReader = new SAXReader();
		
		xmlReader.setDocumentFactory(factory);
		return xmlReader;
	}

}
