package com.skybot.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skybot.activities.R;

public class JobsHistoryAdapter extends BaseAdapter {

	private Activity activity;
	public ArrayList<HashMap<String, String>> data;
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

		TextView id = (TextView) vi.findViewById(R.id.title); // id
		TextView job = (TextView) vi.findViewById(R.id.runnumber); // runnumber
		TextView job_id = (TextView) vi.findViewById(R.id.suitrunnumber); // suitrunnumber
		ImageView image = (ImageView) vi.findViewById(R.id.list_image); // status
																		// image

		if (data != null && !data.isEmpty()) {
			HashMap m = new HashMap();
			m = data.get(position);

			if (m.get("job_status_raw").toString().equals("C")) {
				image.setImageResource(R.drawable.blank_badge_green);
			} else if (m.get("job_status_raw").toString().equals("S")) {
				image.setImageResource(R.drawable.blank_badge_orange);
			}

			else if (m.get("job_status_raw").toString().equals("F")) {
				image.setImageResource(R.drawable.blank_badge_red);
			}

			id.setText(m.get("job").toString());
			job.setText("JOB RUN ID: " + m.get("id").toString());
			job_id.setText("JOB SUITE RUN ID: "
					+ m.get("job_suite_run_id").toString());

		}

		return vi;
	}

}
