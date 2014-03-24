package com.fegmobile.monopoly.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fegmobile.monopoly.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

@SuppressLint("NewApi")
public class CardsAdapter extends BaseAdapter {

	private List<ParseObject> data;
	private static LayoutInflater inflater = null;
	View view;
	ParseUser user;
	Activity activity;
	boolean hasCard = false;

	public CardsAdapter(Context context, Activity act, List<ParseObject> cards, ParseUser user) {
		data = cards;
		inflater = LayoutInflater.from(context);
		activity = act;
		this.user = user;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		view = convertView;
		if (convertView == null)
			view = inflater.inflate(R.layout.item_list_row, null);
		
		final TextView tvNumber = (TextView) view.findViewById(R.id.tvNumber);
		TextView tvDetail = (TextView) view.findViewById(R.id.tvDetail);
		final ImageView ivHave = (ImageView) view.findViewById(R.id.ivHave);
		
		tvNumber.setText("#"+data.get(position).getInt("code"));
		tvDetail.setText(data.get(position).getString("gift"));
		
		List<ParseObject> cards = (List<ParseObject>) user.get("cards");
		String idCard = data.get(position).getObjectId();
		
		if (cards != null && cards.indexOf(idCard) > 0) {
			ivHave.setImageDrawable(activity.getResources().getDrawable(R.drawable.right_check_mark));
			hasCard = true;
		} else {
			ivHave.setImageDrawable(activity.getResources().getDrawable(R.drawable.wrong_check_mark));
			hasCard = false;
		}
		ivHave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (hasCard) {
					hasCard = false;
					ivHave.setImageDrawable(activity.getResources().getDrawable(R.drawable.wrong_check_mark));
				}
				else {
					hasCard = true;
					ivHave.setImageDrawable(activity.getResources().getDrawable(R.drawable.right_check_mark));
				}
			}
		});
		
		return view;
	}
}