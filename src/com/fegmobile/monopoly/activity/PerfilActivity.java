package com.fegmobile.monopoly.activity;

import static com.parse.ParseUser.getCurrentUser;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.fegmobile.monopoly.R;
import com.fegmobile.monopoly.adapter.CardsAdapter;
import com.fegmobile.monopoly.manager.ParseQueryManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class PerfilActivity extends Activity {

	ListView cardsList;
	ParseQueryManager pqm;
	public static ParseUser friendRequested;
	CardsAdapter adapter; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_perfil);

		pqm = new ParseQueryManager();

		pqm.getAllCards().findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> cards, ParseException arg1) {
				cardsList = (ListView) findViewById(R.id.lvCards);

				if (getIntent().getBooleanExtra("isMe", false))
					adapter = new CardsAdapter(getApplicationContext(), PerfilActivity.this, cards, getCurrentUser());
				else
					adapter = new CardsAdapter(getApplicationContext(), PerfilActivity.this, cards, friendRequested);
				
				cardsList.setAdapter(adapter);
			}
		});

		super.onCreate(savedInstanceState);
	}

}
