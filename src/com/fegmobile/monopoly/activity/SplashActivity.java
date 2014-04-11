package com.fegmobile.monopoly.activity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.fegmobile.monopoly.R;
import com.fegmobile.monopoly.manager.UtilManager;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQuery.CachePolicy;

public class SplashActivity extends Activity {

	private static final long SPLASH_TIME_OUT = 4000;
	AnimationDrawable rollDice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		getActionBar().hide();

		this.setContentView(R.layout.splash);
		
		ImageView roll_animation = (ImageView) findViewById(R.id.roll_animation);
		roll_animation.setBackgroundResource(R.drawable.roll_dice);

		rollDice = (AnimationDrawable) roll_animation
				.getBackground();
		rollDice.start();

		Parse.initialize(this,
				getResources().getString(R.string.PARSE_APPLICATION_ID),
				getResources().getString(R.string.PARSE_CLIENT_KEY));
		
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Cards");
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.setMaxCacheAge(TimeUnit.DAYS.toMillis(7));
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> result, ParseException pex) {
				if (pex == null) {
					UtilManager.CARDS = result;
				} else {
					pex.printStackTrace();
				}
			}
		});
		
		new Handler().postDelayed(new Runnable()
		{
			
			@Override
			public void run()
			{
				Intent i = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(i);
				
				finish();
			}
		}, SPLASH_TIME_OUT);

		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onDestroy() {
		if (rollDice != null)
			rollDice.stop();
		super.onDestroy();
	}
}