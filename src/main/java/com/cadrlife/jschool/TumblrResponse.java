package com.cadrlife.jschool;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TumblrResponse {
	
	private Meta meta = new Meta();
	private Response response = new Response();
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
}
