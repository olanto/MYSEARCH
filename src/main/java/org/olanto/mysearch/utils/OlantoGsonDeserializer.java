package org.olanto.mysearch.utils;

import org.olanto.mysearch.model.OlantoResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class OlantoGsonDeserializer {

	private String json;
	private OlantoResponse obj;

	public OlantoGsonDeserializer(String json) throws JsonSyntaxException {
		
		init(json);
	}

	private void init(String json) throws JsonSyntaxException {
		this.json = json;
		
		if (json==null)
			return;
		
		obj = new Gson().fromJson(this.json, OlantoResponse.class);
		//System.out.println("Data: " +obj.getData());
		
	}

	public OlantoResponse toObject() {
		return obj;
	}

}
