package com.example.inclass5;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class NewsUtil {

	News news;
    static newsUtil newsUtilObj;
	   
	static ArrayList<News> parseNews(InputStream in,MainActivity context) throws XmlPullParserException,
			IOException {
		String namespace;
		ArrayList<News> newsArrayList = new ArrayList<News>();
		ArrayList<String> newsTitleList = new ArrayList<String>();
		XmlPullParser parser = XmlPullParserFactory.newInstance()
				.newPullParser();
		parser.setInput(in, "UTF-8");
		News news = null;
		newsUtilObj = (newsUtil) context;
		boolean isItem = false;
		String name = "", prefix = "";
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			
			switch (event) {
		
			case XmlPullParser.START_TAG:
				if(parser.getName().equals("item")){
				  news = new News();
				  isItem = true;
				} 
				else if(parser.getName().equals("media:description")) {
					news.setMediaDescription(parser.nextText());
				}
				else if(parser.getName().equals("title") && isItem)
				{ String text = parser.nextText();
					news.setTitle(text);
					newsTitleList.add(text);
				} else if(parser.getName().equals("media:content")){
					news.setMediaContent(parser.getAttributeValue(null, "url"));
				}
				break;
			case XmlPullParser.END_TAG:
				if(parser.getName().equals("item")){
					isItem = false;
					newsArrayList.add(news);
				}
				break;
			default:
				break;
			}

			event = parser.next();
		}
		
		Log.d("ArrayList Size", Integer.toString(newsArrayList.size()));
		for(int i=0 ; i < newsArrayList.size() ; i++) {
			Log.d("News Array",newsArrayList.get(i).toString());
		}
		
		
		return newsArrayList;
		
		
	}
	
	static interface newsUtil {
		public void setArrayList(ArrayList<News> arrayList);
		
	}
}
