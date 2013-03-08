package com.skybot.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skybot.activities.JobsDetailsActivity;
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

	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.jobs_history_layout, null);

		TextView id = (TextView) vi.findViewById(R.id.title); // id
		TextView job = (TextView) vi.findViewById(R.id.runnumber); // runnumber
		TextView job_id = (TextView) vi.findViewById(R.id.suitrunnumber); // suitrunnumber
		ImageView image = (ImageView) vi.findViewById(R.id.list_image); // status
		ImageView infoButton = (ImageView) vi.findViewById(R.id.info_button);

		try {
			if (data != null && !data.isEmpty()) {

				HashMap<String, String> m = new HashMap<String, String>();
				m = data.get(position);

				if (m.get("job_status_raw") != null) {
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

					final HashMap<String, String> detailMap;
					detailMap = m;

					infoButton.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							try {
								Intent jobsdetailsIntent = new Intent(v
										.getContext(),
										JobsDetailsActivity.class);
								jobsdetailsIntent.putExtra("DetailMap",
										detailMap);
								v.getContext().startActivity(jobsdetailsIntent);
							} catch (Exception e) {
								Log.e("Error", e.getMessage());
							}
						}
					});
				}

			}
		} catch (Exception e) {
			Log.e("Exception occured", e.toString());
		}
		return vi;
	}

}
