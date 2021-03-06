package org.olanto.mysearch.repository;

import java.util.List;

import org.olanto.mysearch.model.OlantoResponse;
import org.olanto.mysearch.repository.impl.OlantoException;

public interface OlantoRepository {

	List<OlantoResponse> search(String key, String source, String target, String query) throws OlantoException;
}
