package com.example.applefeed;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;



import android.util.Log;

public class AppleUtils {

	static class ApplePullParser {
		static ArrayList<Applications> parseAppleFeed(InputStream in)
				throws IOException {
			Applications application = null;
			ArrayList<Applications> applicationsList = new ArrayList<Applications>();
			try {
				XmlPullParser parser = XmlPullParserFactory.newInstance()
						.newPullParser();
				parser.setInput(in, "UTF-8");

				int event = parser.getEventType();
				while (event != XmlPullParser.END_DOCUMENT) {
					switch (event) {
					case XmlPullParser.START_TAG:
						if (parser.getName().equals("entry")) {
							application = new Applications();
						} else if(parser.getName().equals("im:name")) {
						    application.setApp_name(parser.nextText());
						} else if (parser.getName().equals("im:price")) {
							String appName = parser.nextText();
							application.setPrice(appName);
						} else if(parser.getName().equals("im:releaseDate")) {
							application.setRelease_date(parser.nextText());
						} else if(parser.getName().equals("category")) {
							application.setCategory(parser.getAttributeValue(null,"scheme"));
						} else if(parser.getName().equals("im:image")) {
							application.setImageUrl(parser.nextText());
						}

						break;
					case XmlPullParser.END_TAG:
						if(parser.getName().equals("entry")) {
							applicationsList.add(application);
							Log.d("Applications Object", applicationsList.toString());
						}
						break;
					default:
						break;
					}
					event = parser.next();
				}
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return applicationsList;

		}

	}
	
	static interface appleUtil {
		public void setArrayList(ArrayList<Applications> arrayList);
		
	}
}
