package com.cadrlife.jschool;

public class Blog {
	private String title = "";
	private long posts;
	private String name = "";
	private String url="";
	private String description="";
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getPosts() {
		return posts;
	}
	public void setPosts(long posts) {
		this.posts = posts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
