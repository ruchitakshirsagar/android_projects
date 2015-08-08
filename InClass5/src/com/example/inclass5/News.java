package com.example.inclass5;

import java.util.ArrayList;

public class News {
 String title, mediaContent, mediaDescription;

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getMediaContent() {
	return mediaContent;
}



public void setMediaContent(String mediaContent) {
	this.mediaContent = mediaContent;
}

public String getMediaDescription() {
	return mediaDescription;
}

public void setMediaDescription(String mediaDescription) {
	this.mediaDescription = mediaDescription;
}

@Override
public String toString() {
	return "News [title=" + title + ", mediaContent=" + mediaContent
			+ ", mediaDescription=" + mediaDescription + "]";
}
 
}
