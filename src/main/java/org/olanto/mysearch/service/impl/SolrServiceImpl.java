package org.olanto.mysearch.service.impl;

import java.util.List;

import org.olanto.mysearch.model.SolrResponse.SolrDoc;
import org.olanto.mysearch.repository.SolrRepository;
import org.olanto.mysearch.repository.impl.OlantoException;
import org.olanto.mysearch.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolrServiceImpl implements SolrService {

	@Autowired
	private SolrRepository repository;

	@Override
	public List<SolrDoc> search(String key, String source, String target, String query) throws OlantoException {
		return repository.search(key, source, target, query);
	}

	@Override
	public List<SolrDoc> solrSearch(String key, String source, String target, String query) {
		return repository.solrSearch(key, source, target, query);
	}
}
