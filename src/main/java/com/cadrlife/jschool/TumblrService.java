package com.cadrlife.jschool;

import static org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.codehaus.jackson.map.SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class TumblrService {
	private String apiKey;
	private HttpClient httpClient;
	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
	private static final Log logger = LogFactory.getLog(TumblrService.class);
	public TumblrService() {
		httpClient = setupHttpClient();
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(FAIL_ON_EMPTY_BEANS, false);
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public TumblrResponse fetchPosts(String tumblrUrl, long limit, long offset) throws HttpException, IOException {
		String urlTemplate = "http://api.tumblr.com/v2/blog/wearethe99percent.tumblr.com/posts/?api_key=%s&limit=%s&offset=%s";
		GetMethod get = new GetMethod(String.format(urlTemplate, apiKey, limit, offset));
        logger.debug("Posting read request to : " + get.getURI().toString());
        
        httpClient.executeMethod(get);
        
        TumblrResponse response = mapper.readValue(get.getResponseBodyAsStream(),TumblrResponse.class);
        return response;
	}
	
	private static HttpClient setupHttpClient() {
        HttpClient client = new HttpClient();
        return client;
    }
	
}
