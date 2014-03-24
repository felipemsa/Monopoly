package com.fegmobile.monopoly.manager;

import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ParseQueryManager {

	public ParseQuery<ParseObject> getAllCards() {
		ParseQuery<ParseObject> query = new ParseQuery<>("Cards");

		return query;
	}

}
