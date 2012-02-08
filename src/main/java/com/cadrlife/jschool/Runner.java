package com.cadrlife.jschool;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

public class Runner {
	static HttpClient httpClient = new HttpClient();
	public static void main(String[] args) throws Exception {
		TumblrService tumblrService = new TumblrService();
		tumblrService.setApiKey("Q9eCyfcUzUlEUwoYHM3nR20Xykwr6YtDk8f68pAjdoJFWHyYv7");
		
		int limit = 10;
		Multimap<String, String> dateIdMap = ArrayListMultimap.create();
		for (int offset = 0; offset < 4000; offset += limit) {
			System.out.println("offset " + offset);
			TumblrResponse response = tumblrService.fetchPosts("wearethe99percent.tumblr.com", limit, offset);
			
			Blog blog = response.getResponse().getBlog();
			for (Post post : response.getResponse().getPosts()) {
				String id = post.getId();
				String datePrefix = post.getDate().replaceFirst(" .*$", "");
				if (dateIdMap.containsValue(id)) {
					continue;
				}
				
				dateIdMap.put(datePrefix, id);
				String basePath = "/Users/racheldavis/Documents/tumblr bkp/wearethe99percent.tumblr.com/" + datePrefix + "_" + dateIdMap.get(datePrefix).size();
				for (Photo photo : post.getPhotos()) {
					String imgUrl = photo.getOriginal_size().getUrl();
					
					String fileName = download(imgUrl, basePath + "/img/");
					photo.getOriginal_size().setUrl("img/" + fileName);
				}
				String htmlString = renderPostHtml(post, blog);
//				syso
				File file = new File(basePath + "/post.html");
				file.mkdirs();
				file.delete();
				file.createNewFile();
				
				Files.write(htmlString, file, Charset.forName("UTF-16"));
			}
		}

		// writable.writeTo(new OutputStreamWriter(System.out));

	}

	private static String download(String url, String dest) throws HttpException, IOException {
		System.out.println(url);
		GetMethod get = new GetMethod(url);
		get.setFollowRedirects(true);
		httpClient.executeMethod(get);
		InputStream is = get.getResponseBodyAsStream();
		String fileName = parseFilename(get.getPath());
		File file = new File(dest + fileName);
		file.mkdirs();
		file.delete();
		file.createNewFile();
		
		FileOutputStream os = new FileOutputStream(file);
		ByteStreams.copy(is, os);
		is.close();
		os.close();
		return fileName;
		
	}

	private static String parseFilename(String path) {
		String[] pathSegments = path.split("/");
		String fileName = pathSegments[pathSegments.length-1];
		return fileName;
	}

	private static String renderPostHtml(Post post, Blog blog)
			throws ClassNotFoundException, IOException {
		HashMap<String, Object> binding = new HashMap<String, Object>();
		binding.put("post", post);
		binding.put("blog", blog);

		// mapper.writer(new DefaultPrettyPrinter()).writeValue(System.out,
		// response);
		Template template = new SimpleTemplateEngine()
				.createTemplate(Runner.class
						.getResource("/cim/cadrlife/jschool/post.html"));
		Writable writable = template.make(binding);

		String htmlString = writable.toString();
		return htmlString;
	}

}
