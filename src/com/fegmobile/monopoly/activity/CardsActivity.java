package com.fegmobile.monopoly.activity;

import static com.fegmobile.monopoly.manager.UtilManager.collapse;
import static com.fegmobile.monopoly.manager.UtilManager.expand;
import static com.parse.ParseUser.getCurrentUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fegmobile.monopoly.R;
import com.fegmobile.monopoly.manager.ParseQueryManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQuery.CachePolicy;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CardsActivity extends Activity
{
	
	ListView cardsList;
	ParseQueryManager pqm;
	public static ParseUser friendRequested;
	public static List<String> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.activity_cards);
		
		final TextView tvSave = (TextView) findViewById(R.id.tvSave);
		tvSave.setVisibility(View.GONE);
		list = new ArrayList<String>();
		final LinearLayout svCards = (LinearLayout) findViewById(R.id.svCards);
		
		pqm = new ParseQueryManager();
		ParseQuery<ParseObject> query = pqm.getAllCards();
		query.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);
		query.setMaxCacheAge(TimeUnit.DAYS.toMillis(7));
		query.findInBackground(new FindCallback<ParseObject>()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void done(final List<ParseObject> cards, ParseException arg1)
			{
				List<ParseObject> userCards;
				if (getIntent().getBooleanExtra("isMe", false))
					userCards = (List<ParseObject>) getCurrentUser().get("cards");
				else
					userCards = (List<ParseObject>) friendRequested.get("cards");
				
				for (final ParseObject card : cards) {
					LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View view = inflater.inflate(R.layout.item_list_row, null);
					
					final TextView tvNumber = (TextView) view.findViewById(R.id.tvNumber);
					final TextView tvDetail = (TextView) view.findViewById(R.id.tvDetail);
					final CheckBox checkHave = (CheckBox) view.findViewById(R.id.checkHave);
					
					tvNumber.setText("#" + card.getInt("code"));
					tvDetail.setText(card.getString("gift"));
					
					if (userCards != null && userCards.indexOf(card.getObjectId()) > 0) {
						checkHave.setChecked(true);
						list.add(card.getObjectId());
					} else {
						checkHave.setChecked(false);
						if (list.indexOf(card.getObjectId()) > -1)
							list.remove(list.indexOf(card.getObjectId()));
					}
					
					checkHave.setOnCheckedChangeListener(new OnCheckedChangeListener()
					{
						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
						{
							if (tvSave.getVisibility() != View.VISIBLE)
								expand(tvSave);
							if (isChecked) {
								list.add(card.getObjectId());
							} else {
								if (list.indexOf(card.getObjectId()) > -1) {
									list.remove(list.indexOf(card.getObjectId()));
								}
							}
						}
					});
					svCards.addView((LinearLayout) view.findViewById(R.id.lnCard));
				}
				
			}
		});
		
		tvSave.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				collapse(tvSave);
				final ProgressDialog pd = new ProgressDialog(CardsActivity.this);
				pd.setMessage("Salvando...");
				pd.show();
				getCurrentUser().put("cards", list);
				getCurrentUser().saveInBackground(new SaveCallback()
				{
					@Override
					public void done(ParseException arg0)
					{
						pd.dismiss();
					}
				});
			}
		});
		
		super.onCreate(savedInstanceState);
	}
	
}
