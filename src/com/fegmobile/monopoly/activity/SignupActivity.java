package com.fegmobile.monopoly.activity;

import static android.widget.Toast.makeText;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fegmobile.monopoly.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends Activity {

	TextView tvLogin;
	EditText etUser;
	EditText etPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_sing_up);

		Parse.initialize(this,
				getResources().getString(R.string.PARSE_APPLICATION_ID),
				getResources().getString(R.string.PARSE_CLIENT_KEY));

		getActionBar().setTitle(R.string.sign_up);

		etUser = (EditText) findViewById(R.id.etEmail);
		etPass = (EditText) findViewById(R.id.etPass);

		etUser.requestFocus();
		
		tvLogin = (TextView) findViewById(R.id.tvSignup);
		tvLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String email = etUser.getText().toString();
				final String pass = etPass.getText().toString();

				ParseQuery<ParseUser> query = ParseUser.getQuery();
				query.whereEqualTo("email", email);

				query.findInBackground(new FindCallback<ParseUser>() {
					@Override
					public void done(List<ParseUser> users, ParseException pe) {
						if (pe == null) {
							if (users.size() == 0) {
								ParseUser user = new ParseUser();

								user.setEmail(email);
								user.setUsername(email);
								user.setPassword(pass);

								user.signUpInBackground(new SignUpCallback() {

									@Override
									public void done(ParseException pe) {
										if (pe == null) {
											makeText(
													getApplicationContext(),
													"Cadastro efetuado com sucesso!",
													Toast.LENGTH_SHORT).show();
											Intent i = new Intent(getApplicationContext(),PerfilActivity.class);
											i.putExtra("isMe",true);
											startActivity(i);
										} else {
											pe.printStackTrace();
										}
									}
								});
							} else {
								makeText(getApplicationContext(),
										"Email já cadastrado!",
										Toast.LENGTH_SHORT).show();
							}
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
