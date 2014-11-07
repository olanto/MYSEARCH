package org.olanto.mysearch.utils;

import java.util.List;

import org.olanto.mysearch.model.SolrResponse;
import org.olanto.mysearch.model.SolrResponse.SolrDoc;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class SolrGsonDeserializer {

	private String json;
	private SolrResponse obj;

	public SolrGsonDeserializer(String json) throws JsonSyntaxException {
		
		init(json);
	}

	private void init(String json) throws JsonSyntaxException {
		this.json = json;
		
		if (json==null)
			return;
		
		obj = new Gson().fromJson(this.json, SolrResponse.class);
		//System.out.println("Data: " +obj.getData());
		
	}

	public SolrResponse toObject() {
		return obj;
	}
	
	public List<SolrDoc> getDocs() {
		return obj.getResponse().getDocs();
	}

}
