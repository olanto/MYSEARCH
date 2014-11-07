package org.olanto.mysearch.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.olanto.mysearch.model.OlantoQueryParameter;

import com.google.gson.JsonSyntaxException;


public class TestOlanto {

	public static final String HEADER_STATUS = null;
	public static final String STATUS_200 = "200";
	public static final String STATUS_OK = "OK";
	
	@Test
	public void TestOlantoServer() {		
		
		String status = null;
		try {
			URL url = new URL("http://srv2.olanto.org/R1");
			URLConnection urlCon = url.openConnection();
			
			status = urlCon.getHeaderField(HEADER_STATUS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assert.assertNotNull("Olanto server status is null", status);
		
		Assert.assertTrue("Olanto server not available", status.contains(STATUS_200) && status.contains(STATUS_OK));
				
	}
	
	
	@Test
	public void TestOlantoQuery() {

		OlantoQueryParameter olantoParam = new OlantoQueryParameter();

		olantoParam.setKey("CORPONU");
		olantoParam.setSource("fr");
		olantoParam.setTarget("en");
		olantoParam.setQ("bonjour");
		olantoParam.setDebug(OlantoQueryParameter.DEBUG.NO);
		
		String queryPart = olantoParam.toQueryUrl();

		//System.out.println("http://srv2.olanto.org/R1/translate/query?"+queryPart);
		try{
			URL url = new URL("http://srv2.olanto.org/R1/translate/query?"+queryPart);
			URLConnection urlCon = url.openConnection();

			String json = IOUtils.toString(urlCon.getInputStream());
			try {
				OlantoGsonDeserializer deserializer = new OlantoGsonDeserializer(json);

				System.out.println(deserializer.toObject());
				Assert.assertNotNull("response is null", deserializer);
				if (deserializer!=null)
					Assert.assertFalse("response as an Error", deserializer.toObject().getData().isError());
				
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
