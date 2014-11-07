package org.olanto.mysearch.service;

import java.util.List;

import org.olanto.mysearch.model.SolrResponse.SolrDoc;
import org.olanto.mysearch.repository.impl.OlantoException;



public interface SolrService {

	List<SolrDoc> search(String key, String source, String target, String query) throws OlantoException;
	
	List<SolrDoc> solrSearch(String key, String source, String target, String query);
	
}
