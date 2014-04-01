package com.fegmobile.monopoly.activity;

import static android.widget.Toast.makeText;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fegmobile.monopoly.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SigninActivity extends Activity {

	TextView tvLogin;
	EditText etUser;
	EditText etPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_sing_in);

		Parse.initialize(this,
				getResources().getString(R.string.PARSE_APPLICATION_ID),
				getResources().getString(R.string.PARSE_CLIENT_KEY));

		getActionBar().setTitle(R.string.sign_in);

		etUser = (EditText) findViewById(R.id.etUser);
		etPass = (EditText) findViewById(R.id.etPass);

		etUser.requestFocus();
		
		tvLogin = (TextView) findViewById(R.id.tvLogin);
		tvLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final ProgressDialog pd = new ProgressDialog(SigninActivity.this);
				pd.setMessage("aguarde...");
				pd.show();
				ParseUser.logInInBackground(etUser.getText().toString(), etPass
						.getText().toString(), new LogInCallback() {
					@Override
					public void done(ParseUser user, ParseException pe) {
						if (pe == null) {
							makeText(getApplicationContext(),
									"Logado com sucesso!", Toast.LENGTH_SHORT)
									.show();
							Intent i = new Intent(getApplicationContext(),CardsActivity.class);
							i.putExtra("isMe",true);
							pd.dismiss();
							startActivity(i);
						} else {
							pe.printStackTrace();
						}

					}
				});
			}
		});

		super.onCreate(savedInstanceState);
	}

}
