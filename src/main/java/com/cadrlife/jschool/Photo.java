package com.cadrlife.jschool;

public class Photo {
	private String caption = "";
	private ImageSize original_size = new ImageSize();
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public ImageSize getOriginal_size() {
		return original_size;
	}
	public void setOriginal_size(ImageSize original_size) {
		this.original_size = original_size;
	}
}
