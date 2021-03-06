package com.skybot.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skybot.activities.JobsActivity;
import com.skybot.activities.JobsDetailsActivity;
import com.skybot.activities.R;

@SuppressLint("NewApi")
public class JobsAdapter extends BaseAdapter {

	private JobsActivity activity;
	public ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	private Drawable hold;
	private Drawable run;
	private Drawable release;

	public JobsAdapter(JobsActivity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		hold = a.getResources().getDrawable(R.drawable.hold_job);
		run = a.getResources().getDrawable(R.drawable.run_job);
		release = a.getResources().getDrawable(R.drawable.release_job);
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
		if (vi.getTag() == null)
			vi.setTag("left");

		TextView title = (TextView) vi.findViewById(R.id.title); // title
		TextView description = (TextView) vi.findViewById(R.id.description); // description
		TextView agent = (TextView) vi.findViewById(R.id.agent); // agent
		ImageView image = (ImageView) vi.findViewById(R.id.list_image); // status
		final ImageView runBtn = (ImageView) vi.findViewById(R.id.btn1);
		final ImageView holdBtn = (ImageView) vi.findViewById(R.id.btn2);
		final ImageView releaseBtn = (ImageView) vi.findViewById(R.id.btn3);
		ImageView showDetails = (ImageView) vi.findViewById(R.id.details);

		try {
			if (data != null && !data.isEmpty()) {

				HashMap<String, String> m = new HashMap<String, String>();
				m = data.get(position);
				String status = null;
				String[] runid = { "", "" };

				if (m.get("hold_status").toString().equals("Released")) {
					image.setImageResource(R.drawable.blank_badge_green);
					status = "Released";
					/*
					 * holdBtn.setVisibility(View.VISIBLE);
					 * releaseBtn.setVisibility(View.INVISIBLE);
					 */
				} else if (m.get("hold_status").toString().equals("Held")) {
					image.setImageResource(R.drawable.blank_badge_orange);
					status = "Held";
					/*
					 * holdBtn.setVisibility(View.INVISIBLE);
					 * releaseBtn.setVisibility(View.VISIBLE);
					 */
				} else if (m.get("hold_status").toString().equals("Running")) {
					image.setImageResource(R.drawable.blank_badge_red);
					status = "Running";
				}
				title.setText(m.get("name").toString());
				description.setText("Agent: " + m.get("agent").toString());
				agent.setText("Description: " + m.get("description").toString());

				runid[0] = m.get("runid");
				runid[1] = status;
				runBtn.setTag(runid);
				holdBtn.setTag(runid);
				releaseBtn.setTag(runid);
				final HashMap<String, String> detailMap;
				detailMap = m;

				showDetails.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						try {
							Intent jobsdetailsIntent = new Intent(v
									.getContext(), JobsDetailsActivity.class);
							jobsdetailsIntent.putExtra("DetailMap", detailMap);
							v.getContext().startActivity(jobsdetailsIntent);
						} catch (Exception e) {
							Log.e("Error", e.getMessage());
						}
					}
				});

				runBtn.setOnClickListener(new OnClickListener() {

					@SuppressWarnings("deprecation")
					@Override
					public void onClick(View v) {

						String[] rid = (String[]) v.getTag();
						if (rid[1].equals("Held") || rid[1].equals("Released")) {
							activity.runJob(v, rid[0]);
						}
					}
				});

				holdBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String[] rid = (String[]) v.getTag();
						if (rid[1].equals("Released")) {
							activity.holdJob(v, rid[0]);
							holdBtn.setVisibility(View.INVISIBLE);
							releaseBtn.setVisibility(View.VISIBLE);
						}
						else if (rid[1].equals("Held")) {
							Toast.makeText(v.getContext(),
									"Job is already released.",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

				releaseBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String[] rid = (String[]) v.getTag();
						if (rid[1].equals("Held")) {
							activity.releaseJob(v, rid[0]);
							releaseBtn.setVisibility(View.INVISIBLE);
							holdBtn.setVisibility(View.VISIBLE);
						} else if (rid[1].equals("Released")) {
							Toast.makeText(v.getContext(),
									"Job is already released.",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		} catch (Exception e) {
			Log.e("Exception occured", e.toString());
		}
		vi.setBackgroundColor(Color.parseColor("#e0f2f9"));
		vi.findViewById(R.id.title).setVisibility(View.VISIBLE);
		vi.findViewById(R.id.description).setVisibility(View.VISIBLE);
		vi.findViewById(R.id.agent).setVisibility(View.VISIBLE);
		vi.findViewById(R.id.btn1).setVisibility(View.INVISIBLE);
		vi.findViewById(R.id.btn2).setVisibility(View.INVISIBLE);
		vi.findViewById(R.id.btn3).setVisibility(View.INVISIBLE);
		vi.findViewById(R.id.hide).setVisibility(View.INVISIBLE);
		vi.findViewById(R.id.list_image).setVisibility(View.VISIBLE);
		vi.findViewById(R.id.details).setVisibility(View.VISIBLE);
		vi.findViewById(R.id.thumbnail).setVisibility(View.VISIBLE);

		return vi;
	}
}