package com.skybot.adapters;

import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skybot.activities.R;

public class JobDetailsAdapter extends BaseAdapter {
	private Activity activity;
	public HashMap<String, String> data;
	private static LayoutInflater inflater = null;

	public JobDetailsAdapter(Activity a, HashMap<String, String> map) {
		activity = a;
		data = map;
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
			vi = inflater.inflate(R.layout.jobs_details, null);

		TextView name = (TextView) vi.findViewById(R.id.detailName);
		TextView description = (TextView) vi.findViewById(R.id.detailContent);

		try {
			if (data != null && !data.isEmpty()) {
				Object keys[] = data.keySet().toArray();
				Object values[] = data.values().toArray();

				name.setText(keys[position].toString() + ": ");
				description.setText(values[position].toString());
			}
		} catch (Exception e) {
			Log.e("Exception occured", e.toString());
		}
		return vi;
	}
}
