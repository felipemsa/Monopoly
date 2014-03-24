package com.fegmobile.monopoly.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.fegmobile.monopoly.R;
import com.parse.Parse;

public class MainActivity extends ActionBarActivity {

	private TextView tvSignUp;
	private TextView tvSignIn;

	// private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Parse.initialize(this,
				getResources().getString(R.string.PARSE_APPLICATION_ID),
				getResources().getString(R.string.PARSE_CLIENT_KEY));
		/*
		 * adView = new AdView(getActivity()); adView.setAdSize(AdSize.BANNER);
		 * adView
		 * .setAdUnitId(getResources().getString(R.string.AD_UNIT_ID_BOTTON));
		 * 
		 * LinearLayout layout = (LinearLayout)
		 * mainView.findViewById(R.id.lnBanBotton); layout.addView(adView);
		 * 
		 * AdRequest adRequest = new AdRequest.Builder().build();
		 * adView.loadAd(adRequest);
		 */
		tvSignUp = (TextView) findViewById(R.id.tvSignUp);
		tvSignIn = (TextView) findViewById(R.id.tvSignIn);

		tvSignUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						SignupActivity.class);
				startActivity(i);
			}
		});

		tvSignIn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						SigninActivity.class);
				startActivity(i);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * @Override public void onResume() { super.onResume(); if (adView != null)
	 * { adView.resume(); } }
	 * 
	 * @Override public void onPause() { if (adView != null) { adView.pause(); }
	 * super.onPause(); }
	 * 
	 * @Override public void onDestroy() { // Destroy the AdView. if (adView !=
	 * null) { adView.destroy(); } super.onDestroy(); }
	 */

}
