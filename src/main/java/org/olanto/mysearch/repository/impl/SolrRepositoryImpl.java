package org.olanto.mysearch.repository.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.olanto.mysearch.model.OlantoResponse;
import org.olanto.mysearch.model.QueryParameter;
import org.olanto.mysearch.model.SolrResponse.SolrDoc;
import org.olanto.mysearch.repository.OlantoRepository;
import org.olanto.mysearch.repository.SolrRepository;
import org.olanto.mysearch.utils.SolrGsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonSyntaxException;

@Repository
public class SolrRepositoryImpl implements SolrRepository {
	

	@Autowired
	private OlantoRepository olantoRepository;
	
	@Override
	public List<SolrDoc> search(String key, String source, String target, String query) throws OlantoException {

		List<SolrDoc> results = new ArrayList<SolrDoc>(1);
		List<OlantoResponse> responses=olantoRepository.search(key, source, target, query);
		
		
		QueryParameter olantoParam = new QueryParameter();
		//olantoParam.add("wt=json");
		if (responses.size()>0) {
			String luceneQuery = "\\{q.op=OR\\}";
			luceneQuery += query.replaceAll("\\s", "+");
			for (OlantoResponse response: responses) {
				try {
					luceneQuery += "+" + URLEncoder.encode(response.getData().getTranslatedText().trim(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}	
			olantoParam.add("q="+luceneQuery);
		} else {
			olantoParam.add("q=+"+query.replaceAll("\\s", "+"));
		}
		
		
		// olantoParam.setDebug(OlantoQueryParameter.DEBUG.NO);

		String queryPart = olantoParam.toQueryUrl();

		try {
			// http://localhost:8983/solr/#/collection1/query?q=name:video
			//String solrUrl = "http://localhost:8080/solr";
			String solrUrl = "http://localhost:8983/solr";
			
			URL url = new URL(solrUrl + "/collection1/query?"
					+ queryPart);
			
			System.out.println("url: '"+url+"'");
			
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			if (urlCon.getErrorStream()!=null) {
				String error = IOUtils.toString(urlCon.getErrorStream());
				if (error!=null && error.length()>0) {
					System.err.println("[ERROR] Solr server response : '"+error+"'");
				}
			}
			String json = IOUtils.toString(urlCon.getInputStream());
			try {
				SolrGsonDeserializer deserializer = new SolrGsonDeserializer(json);
				
				if (deserializer!=null)
					results.addAll(deserializer.getDocs());
				
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 

		return results;
	}
	
	
	@Override
	public List<SolrDoc> solrSearch(String key, String source, String target, String query) {

		List<SolrDoc> results = new ArrayList<SolrDoc>(1);
		
		QueryParameter solrParam = new QueryParameter();
		
		String languagePart = "%20AND%20language_s:"+source;
		solrParam.add("q=text:"+query.replaceAll("\\s", "+")+languagePart);
		
		String queryPart = solrParam.toQueryUrl();

		try {
			// http://localhost:8983/solr/#/collection1/query?q=name:video
			//String solrUrl = "http://localhost:8080/solr";
			String solrUrl = "http://localhost:8983/solr";
			
			URL url = new URL(solrUrl + "/collection1/query?"
					+ queryPart);
			
			System.out.println("url: '"+url+"'");
			
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			if (urlCon.getErrorStream()!=null) {
				String error = IOUtils.toString(urlCon.getErrorStream());
				if (error!=null && error.length()>0) {
					System.err.println("[ERROR] Solr server response : '"+error+"'");
				}
			}
			String json = IOUtils.toString(urlCon.getInputStream());
			try {
				SolrGsonDeserializer deserializer = new SolrGsonDeserializer(json);
				
				if (deserializer!=null)
					results.addAll(deserializer.getDocs());
				
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 

		return results;
	}

}
