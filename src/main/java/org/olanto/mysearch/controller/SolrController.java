package org.olanto.mysearch.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.olanto.mysearch.model.SolrResponse.SolrDoc;
import org.olanto.mysearch.repository.impl.OlantoException;
import org.olanto.mysearch.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping(value = "/solr")
@Controller
public class SolrController {

	@Autowired
	private SolrService service;

	@Produces(value = MediaType.APPLICATION_XHTML_XML)
	@RequestMapping(value = "searchAsHtml", method = RequestMethod.GET)
	@ResponseBody
	public String searchAsHtml(
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "target", required = true) String target,
			@RequestParam(value = "q", required = true) String query) throws OlantoException {
		
		StringBuilder buffer = new StringBuilder();
		
		List<SolrDoc> list = service.search(key, source, target, query);
		
		buffer.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		buffer.append("<html><head>");
		buffer.append("<title>Results</title>");
		buffer.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>");
		buffer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap.min.css\"></link>");
		buffer.append("</head><body class=\"container\" width=\"800px\">");
		
		buffer.append("<div class=\"container-fluid\">");
		buffer.append("<h2>Results</h2>");
		buffer.append("<div>");
		buffer.append("<table class=\"table table-condensed\">");
		if (list.size()>0) {
			
			buffer.append("<tr><th>Id</th><th>Name</th></tr>");
			for (SolrDoc solrDoc : list) {
				buffer.append("<tr><td>");
				buffer.append(solrDoc.getId());
				buffer.append("</td><td>");
				buffer.append(solrDoc.getName());
				buffer.append("</td></tr>");
			}
			
		}
		else
		{
			buffer.append("<tr><th>#</th></tr>");
			buffer.append("<tr><td>No result</td></tr>");
		}
		buffer.append("</table>");
		buffer.append("</div></div>");
		buffer.append("</body></html>");
		return buffer.toString();
	}
	
	@Produces(value = MediaType.APPLICATION_JSON)
	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public List<SolrDoc> search(
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "target", required = true) String target,
			@RequestParam(value = "q", required = true) String query) throws OlantoException {
		
		List<SolrDoc> list = service.search(key, source, target, query);
		
		return list;
	}
	
	@Produces(value = MediaType.APPLICATION_JSON)
	@RequestMapping(value = "solrSearch", method = RequestMethod.GET)
	@ResponseBody
	public List<SolrDoc> solrSearch(
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "target", required = true) String target,
			@RequestParam(value = "q", required = true) String query) {
		
		List<SolrDoc> list = service.solrSearch(key, source, target, query);
		
		return list;
	}
}
