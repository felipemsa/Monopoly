package com.fegmobile.monopoly.adapter;
import static com.parse.ParseUser.getCurrentUser;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.fegmobile.monopoly.R;
import com.parse.ParseObject;

public class CardsAdapter extends BaseAdapter {

	private List<ParseObject> data;
	private static LayoutInflater inflater = null;
	View view;
	Switch swHave;
	
	public CardsAdapter(Context context, List<ParseObject> cards)
	{
		data = cards;
		inflater = LayoutInflater.from(context);
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
	public View getView(int position, View convertView, ViewGroup parent) {
		
		view = convertView;
		if (convertView == null)
			view = inflater.inflate(R.layout.item_list_row, null);
		
		final TextView tvNumber = (TextView) view.findViewById(R.id.tvNumber);
		TextView tvDetail = (TextView) view.findViewById(R.id.tvDetail);
		Switch swHave = (Switch) view.findViewById(R.id.swHave);
		
		tvNumber.setText("#"+data.get(position).getInt("code"));
		tvDetail.setText(data.get(position).getString("gift"));
		
		if (((List<ParseObject>)getCurrentUser().get("cards")).indexOf(data.get(position).getObjectId()) > 0)
			swHave.setChecked(true);
		else
			swHave.setChecked(false);
		
		return view;
	}
	
}