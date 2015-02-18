package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/***
 * resources
 * http://www.javaworld.com/article/2072602/java-properties-in-xml.html
 * http://www
 * .codejava.net/coding/reading-and-writing-configuration-for-java-application
 * -using-properties-class
 * 
 * @author miao
 *
 */

public class Configuration {

	/***
	 * currently the configPath is hardcoded 
	 */
	private final static String configPath = "WebContent/WEB-INF/config.xml";
	private Properties props = new Properties();

	public Configuration() {
		loadConfiguration();
	}

	public void loadConfiguration() {

		File configFile = new File(configPath);
		InputStream inputStream;

		try {
			inputStream = new FileInputStream(configFile);
			props.loadFromXML(inputStream);

			inputStream.close();
		} catch (FileNotFoundException e) {
			System.err
					.println("Cannot read properties from file " + configPath);
		} catch (IOException ioEx) {
			System.err.println("IOException encountered while reading "
					+ configPath);
		}

	}

	public void storePropertiesToXML (String outputPath) {

		try {
			FileOutputStream fos = new FileOutputStream(outputPath);
			props.storeToXML(fos, "Storing the configuration to an XML file.");
			fos.close();
		} catch (FileNotFoundException fnfEx) {
			System.err.println("ERROR writing to " + outputPath);
		} catch (IOException ioEx) {
			System.err.println("ERROR trying to write XML properties to file "
					+ outputPath);
		}

	}
	
	public String getPropertyValue (String key) {
		
		return (String) props.get(key);
		
	}
	
	public void addProperty (String key, String value) {
		
		props.setProperty(key, value);
		
	}
	
	public void removeProperty (String key) {
		
		props.remove(key);
		
	}
	

	public static void main(String[] args) {
		
		Configuration config = new Configuration ();
		System.out.println(config.props.toString());
		
		config.addProperty("testkey", "testvalue");
		System.out.println(config.props.toString());
		
		config.removeProperty("testkey");
		System.out.println(config.props.toString());

	}

}
