package com.skybot.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.skybot.activities.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class JobsAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;

	public JobsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
			vi = inflater.inflate(R.layout.jobs_layout, null);

		TextView title = (TextView) vi.findViewById(R.id.title); // title
		TextView description = (TextView) vi.findViewById(R.id.description); // description
		TextView agent = (TextView) vi.findViewById(R.id.agent); // agent
		
		if (data != null && !data.isEmpty()) {
			HashMap m = new HashMap();
			m = data.get(0);
			title.setText(m.get("maxId").toString());
			description.setText(m.get("timestamp").toString());
			agent.setText(m.get("growler_message").toString());
		}
		
		vi.findViewById(R.id.title).setVisibility(View.VISIBLE);
		vi.findViewById(R.id.description).setVisibility(View.VISIBLE);
		vi.findViewById(R.id.agent).setVisibility(View.VISIBLE);
		vi.findViewById(R.id.btn1).setVisibility(View.INVISIBLE);
		vi.findViewById(R.id.btn2).setVisibility(View.INVISIBLE);
		vi.findViewById(R.id.btn3).setVisibility(View.INVISIBLE);

		return vi;
	}
}