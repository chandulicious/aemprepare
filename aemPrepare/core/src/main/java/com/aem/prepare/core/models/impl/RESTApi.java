package com.aem.prepare.core.models.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = {SlingHttpServletRequest.class, Resource.class})
public class RESTApi{
	private final Logger log = LoggerFactory.getLogger(RESTApi.class);
	
	@Inject
	private String pagePath;
	
	@SlingObject
	private Resource currentResource;
	
	List<Products> products;

	@PostConstruct
	public void init() {
		try {
			if(this.pagePath.length() > 0) {
				log.info("page path: :{}",this.pagePath);
				log.info("CURRENT RESOURCE::{}", this.currentResource);
				//products = Test();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			
			log.debug(e.getMessage());
		}
	}
	
//	public APIResponse makePostWSCall(String endPointURL, String requestBody, Map<String, String> requestParams, Map<String, String> headers) {
//	    try (CloseableHttpClient client = HttpClients.createDefault()) {
//	        final long startTime = System.nanoTime();
//	        endPointURL = makeEndPointURL(endPointURL, requestParams);
//
//	        final String endPoint = StringEscapeUtils.escapeJava(endPointURL);
//	        log.debug("RestServiceImpl :: makePostWSCall :: EndPoint URL --> {}", endPoint);
//
//	        HttpPost httpPost = new HttpPost(endPointURL);
//	        if (null != headers && !headers.isEmpty()) {
//	            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
//	                httpPost.addHeader(headerEntry.getKey(), headerEntry.getValue());
//	            }
//	        }
//
//	        requestBody = WebServiceUtil.getPrettyJsonString(requestBody);
//	        log.debug("RestServiceImpl :: makePostWSCall :: Request Body --> {}", requestBody);
//	        if (StringUtils.isNotEmpty(requestBody)) {
//	            StringEntity stringEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
//	            httpPost.setEntity(stringEntity);
//	        }
//
//	        APIResponse apiResponse = new APIResponse();
//	        CloseableHttpResponse response = client.execute(httpPost);
//	        final int statusCode = response.getStatusLine().getStatusCode();
//	        apiResponse.setStatusCode(statusCode);
//	        log.info("RestServiceImpl :: makePostWSCall :: Response Status Code --> {}", statusCode);
//
//	        getResponse(endPointURL, startTime, apiResponse, response, "RestServiceImpl :: WSCall :: Response --> {}");
//
//	        return apiResponse;
//	    } catch (Exception e) {
//	        log.error("RestServiceImpl :: makePostWSCall :: Exception --> ", e);
//	    }
//	    return null;
//	}


}
