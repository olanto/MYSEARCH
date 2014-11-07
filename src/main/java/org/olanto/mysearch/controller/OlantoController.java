package org.olanto.mysearch.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.olanto.mysearch.model.OlantoResponse;
import org.olanto.mysearch.repository.impl.OlantoException;
import org.olanto.mysearch.service.OlantoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Produces(value = MediaType.APPLICATION_XHTML_XML)
@RequestMapping(value = "/olanto")
@Controller
public class OlantoController {

	@Autowired
	private OlantoService service;

	@Produces(value = MediaType.APPLICATION_JSON)
	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public List<OlantoResponse> search(
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "target", required = true) String target,
			@RequestParam(value = "q", required = true) String query) throws OlantoException {

		return service.search(key, source, target, query);
	}
	
	@RequestMapping(value = "searchAsHTML", method = RequestMethod.GET)
	@ResponseBody
	public String searchAsHTML(
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "target", required = true) String target,
			@RequestParam(value = "q", required = true) String query) throws OlantoException {

		List<OlantoResponse> list= service.search(key, source, target, query);
		
		StringBuilder buffer = new StringBuilder();
		buffer.append("<html><head></head><body>");
		for (OlantoResponse olantoResponse : list) {
			buffer.append("<p>"+olantoResponse.getData().getTranslatedText()+"</p>");
		}
		buffer.append("</body></html>");
		return buffer.toString();
	}
}
