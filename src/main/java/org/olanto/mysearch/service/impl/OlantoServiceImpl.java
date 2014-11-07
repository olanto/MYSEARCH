package org.olanto.mysearch.service.impl;

import java.util.List;

import org.olanto.mysearch.model.OlantoResponse;
import org.olanto.mysearch.repository.OlantoRepository;
import org.olanto.mysearch.repository.impl.OlantoException;
import org.olanto.mysearch.service.OlantoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OlantoServiceImpl implements OlantoService {

	@Autowired
	private OlantoRepository repository;
	
	@Override
	public List<OlantoResponse> search(String key, String source,
			String target, String query) throws OlantoException {
		return repository.search(key, source, target, query);
	}

}
