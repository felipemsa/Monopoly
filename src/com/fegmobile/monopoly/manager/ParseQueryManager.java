package com.fegmobile.monopoly.manager;

import static com.parse.ParseUser.getCurrentUser;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ParseQueryManager {

	@SuppressWarnings("unchecked")
	public List<ParseObject> getMyCards() {
		List<ParseObject> cards = new ArrayList<ParseObject>();

		for (ParseObject card : (List<ParseObject>) getCurrentUser().get("cards")) {
			cards.add(card);
		}

		return cards;
	}

	public ParseQuery<ParseObject> getAllCards() {
		ParseQuery<ParseObject> query = new ParseQuery<>("Cards");

		return query;
	}

}
