package org.olanto.mysearch.model;

import java.util.HashSet;
import java.util.Iterator;


public class QueryParameter extends HashSet<String> {

	private static final long serialVersionUID = 2348167789237142430L;
	
	public String toQueryUrl() {
		StringBuilder result = new StringBuilder();
		Iterator<String> it = iterator();
		while (it.hasNext()) {
			result.append(it.next());
			result.append("&");
		}
		return result.substring(0,result.length()-1);
	}
	
}
