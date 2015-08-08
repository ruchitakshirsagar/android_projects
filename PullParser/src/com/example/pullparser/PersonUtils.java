package com.example.pullparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class PersonUtils {
	static public class PersonsPullParser {
		Person person;

		static ArrayList<Person> parsePersons(InputStream in)
				throws XmlPullParserException, IOException {
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(in, "UTF-8");
			Person person = null;
			ArrayList<Person> personsList = new ArrayList<Person>();
			int event = parser.getEventType();

			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("person")) {
						person = new Person();
						person.setId(parser.getAttributeValue(null, "id"));

					} else if (parser.getName().equals("name")) {
						person.setName(parser.nextText().trim());
					} else if (parser.getName().equals("age")) {
						person.setAge(parser.nextText().trim());
					} else if (parser.getName().equals("department")) {
						person.setDepartment(parser.nextText().trim());
					}
					break;

				case XmlPullParser.END_TAG:
					if(parser.getName().equals("person"))
					personsList.add(person);
					break;
				default:
					
					break;
				}
				event = parser.next();
			}
			return personsList;

		}

		public ArrayList<Person> getPersonsList() {

			return null;

		}

	}
}
