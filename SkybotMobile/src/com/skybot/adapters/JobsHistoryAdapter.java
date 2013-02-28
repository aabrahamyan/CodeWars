package com.skybot.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skybot.activities.R;

public class JobsHistoryAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;

	public JobsHistoryAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.jobs_history_layout, null);

		TextView title = (TextView) vi.findViewById(R.id.title); // title
		TextView runnumber = (TextView) vi.findViewById(R.id.runnumber); // runnumber
		title.setText((data.get(3).toString()));
		TextView suitrunnumber = (TextView) vi.findViewById(R.id.suitrunnumber); // suitrunnumber

		HashMap<String, String> jobs = new HashMap<String, String>();
		jobs = data.get(position);

		return vi;
	}
}
