/*
#
# Copyright 2015 The Trustees of Indiana University
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
*/

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
