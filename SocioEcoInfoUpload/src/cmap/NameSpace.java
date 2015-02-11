package cmap;

import java.util.HashMap;
import java.util.Map;

public class NameSpace {
	
	public static final String ns_sescore="http://localhost:8080/sesonto/sescore#";
	public static final String ns_sesins = "http://localhost:8080/sesonto/sampleinstances#";
	public static final String ns_skos = "http://www.w3.org/2004/02/skos/core#";
	
	public static final Map<String,String> SESInstancePrefixes = new HashMap <String, String>();
	
	static {
		
		SESInstancePrefixes.put("sescore", ns_sescore);
		SESInstancePrefixes.put("sampleinstances", "http://localhost:8080/sesonto/sampleinstances#");
		SESInstancePrefixes.put("skos", ns_skos);
		
	}

}
