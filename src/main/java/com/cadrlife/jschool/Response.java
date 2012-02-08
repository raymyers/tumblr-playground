package com.cadrlife.jschool;

import java.util.ArrayList;
import java.util.List;

public class Response {
	private Blog blog = new Blog();
	private List<Post> posts = new ArrayList<Post>();
	
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
