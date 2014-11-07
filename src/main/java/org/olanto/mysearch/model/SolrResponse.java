package org.olanto.mysearch.model;

import java.io.Serializable;
import java.util.List;

public class SolrResponse implements Serializable {

	private static final long serialVersionUID = 591938539501888800L;

	private SolrResponseHeader responseHeader;

	private SolrResponseData response; // Do not rename, because is named like that in response of solr

	public class SolrResponseData implements Serializable {

		private static final long serialVersionUID = -5286716256140771075L;

		private Integer numFound;

		private Integer start;

		private List<SolrDoc> docs;

		public Integer getNumFound() {
			return numFound;
		}

		public void setNumFound(Integer numFound) {
			this.numFound = numFound;
		}

		public Integer getStart() {
			return start;
		}

		public void setStart(Integer start) {
			this.start = start;
		}

		public List<SolrDoc> getDocs() {
			return docs;
		}

		public void setDocs(List<SolrDoc> docs) {
			this.docs = docs;
		}

	}

	public class SolrDoc implements Serializable {

		private static final long serialVersionUID = -1485254517901549204L;

		private String id;

		private String name;

		private List<String> cat;

		private String language_s;
		
		private String filename_s;

		private List<String> content;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public List<String> getCat() {
			return cat;
		}

		public void setCat(List<String> cat) {
			this.cat = cat;
		}

		public String getLanguage() {
			return language_s;
		}

		public void setLanguage(String language) {
			this.language_s = language;
		}
		
		public String getFilename() {
			return filename_s;
		}

		public void setFilename(String filename) {
			this.filename_s = filename;
		}

		public List<String> getContent() {
			return content;
		}

		public void setContent(List<String> content) {
			this.content = content;
		}

	}

	public class SolrResponseHeader implements Serializable {

		private static final long serialVersionUID = -1485254517901549204L;

		private Integer status;
		private Long qTime;
		private SolrParams params;

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Long getqTime() {
			return qTime;
		}

		public void setqTime(Long qTime) {
			this.qTime = qTime;
		}

		public SolrParams getParams() {
			return params;
		}

		public void setParams(SolrParams params) {
			this.params = params;
		}

	}

	public class SolrParams implements Serializable {

		private static final long serialVersionUID = 4708803260631846417L;
		private String q;
		private String wt;

		public String getQ() {
			return q;
		}

		public void setQ(String q) {
			this.q = q;
		}

		public String getWt() {
			return wt;
		}

		public void setWt(String wt) {
			this.wt = wt;
		}
	}

	public SolrResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(SolrResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}

	public SolrResponseData getResponse() {
		return response;
	}

	public void setResponse(SolrResponseData responseData) {
		this.response = responseData;
	}
}
