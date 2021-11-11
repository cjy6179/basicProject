package config;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class LoadProperties {

	static Properties p = new Properties();
	
	public static Properties getProperties() {
		return p;
	}
	
	protected static void setProperties() {
		makeProperties();
	}
	
	private static Properties makeProperties() {
		
		String config = "application.properties";
		ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
		URL url = currentThreadClassLoader.getResource(config);
		try {
			p.load(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println(p.get("url"));
		return p;
	}
}
