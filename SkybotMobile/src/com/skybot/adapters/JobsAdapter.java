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
import android.widget.ImageView;
import android.widget.TextView;

public class JobsAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	//public ImageLoaderAdapter imageLoader;

	public JobsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//imageLoader = new ImageLoaderAdapter(activity.getApplicationContext());
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
			vi = inflater.inflate(R.layout.agent_layout, null);

		TextView title = (TextView) vi.findViewById(R.id.title); // title
		TextView description = (TextView) vi.findViewById(R.id.description); // description
		// TextView agent = (TextView) vi.findViewById(R.id.agent); // agent
		ImageView thumb_image = (ImageView) vi.findViewById(R.id.list_image); // thumb
																				// image

		HashMap<String, String> jobs = new HashMap<String, String>();
		jobs = data.get(position);

		// Setting all values in listview
		//title.setText(jobs.get(CustomizedListViewActivity.KEY_TITLE));
		//description.setText(jobs
		//		.get(CustomizedListViewActivity.KEY_DESCRIPTION));
		// agent.setText(jobs.get(CustomizedListViewActivity.KEY_AGENT));
	/*	imageLoader
				.DisplayImage(
						jobs.get(CustomizedListViewActivity.KEY_THUMB_URL),
						thumb_image);*/
		return vi;
	}
}