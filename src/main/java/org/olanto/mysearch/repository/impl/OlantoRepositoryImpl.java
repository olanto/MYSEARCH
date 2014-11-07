package org.olanto.mysearch.repository.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.olanto.mysearch.model.OlantoQueryParameter;
import org.olanto.mysearch.model.OlantoResponse;
import org.olanto.mysearch.repository.OlantoRepository;
import org.olanto.mysearch.utils.OlantoGsonDeserializer;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonSyntaxException;

@Repository
public class OlantoRepositoryImpl implements OlantoRepository {

	@Override
	public List<OlantoResponse> search(String key, String source,
			String target, String query) throws OlantoException {
		
		List<OlantoResponse> results = new ArrayList<OlantoResponse>(1);
		
		OlantoQueryParameter olantoParam = new OlantoQueryParameter();

		olantoParam.setKey(key);
		olantoParam.setSource(source);
		olantoParam.setTarget(target);
		olantoParam.setQ(query);
		//olantoParam.setDebug(OlantoQueryParameter.DEBUG.NO);
		
		String queryPart = olantoParam.toQueryUrl();

		try{
			URL url = new URL("http://srv2.olanto.org/R1/translate/query?"+queryPart);
			URLConnection urlCon = url.openConnection();

			String json = IOUtils.toString(urlCon.getInputStream());
			try {
				OlantoGsonDeserializer deserializer = new OlantoGsonDeserializer(json);
				
				if (deserializer.toObject().getData().isError()) {
					//if(deserializer.toObject().getData().getMessage().contains(""))
					throw new OlantoException(deserializer.toObject().getData().getMessage());
				}
				if (deserializer!=null && !deserializer.toObject().getData().isError())
					results.add(deserializer.toObject());
				
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return results;
	}

}
