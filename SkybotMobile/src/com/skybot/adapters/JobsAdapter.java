package com.skybot.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.skybot.activities.JobsActivity;
import com.skybot.activities.R;
import com.skybot.activities.R.drawable;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JobsAdapter extends BaseAdapter {

	public static String run_id;
	private Activity activity;
	public ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	
	public JobsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	public static String getId () {
		return run_id;
	}
	public static void setId (String _id) {
		run_id = _id;
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
		TextView jobId = (TextView) vi.findViewById(R.id.job_id); // Job Id
		ImageView image = (ImageView) vi.findViewById(R.id.list_image); //status image
		
		if (data != null && !data.isEmpty()) {
			HashMap<String, String> m = new HashMap<String, String>();
			m = data.get(position);
			
			if (m.get("hold_status").toString().equals("Released")) {
				image.setImageResource(R.drawable.blank_badge_green);
			}
			else if (m.get("hold_status").toString().equals("Hold")) {
				image.setImageResource(R.drawable.blank_badge_orange);
			}
			title.setText(m.get("name").toString());
			description.setText("Agent: "+m.get("agent").toString());
			agent.setText("Description: "+m.get("description").toString());
			jobId.setText(m.get("runid").toString());
			run_id = m.get("runid");
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