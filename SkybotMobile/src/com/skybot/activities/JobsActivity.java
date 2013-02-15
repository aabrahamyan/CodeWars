package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.JobsAdapter;

/**
 * Activity for representing Dashboard items for overall statistics. This class
 * will contain also ScrollView implementation control
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class JobsActivity extends ListActivity implements ActionDelegate {

	static final String KEY_TITLE = "title";
	static final String KEY_DESCRIPTION = "description";
	static final String KEY_AGENT = "agent";

	ListView list;
	JobsAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.jobs_layout);
		super.onCreate(savedInstanceState);

		ArrayList<HashMap<String, String>> jobsList = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < 4; i++) {

			HashMap<String, String> map = new HashMap<String, String>();

			map.put(KEY_TITLE, "Job 1");
			map.put(KEY_DESCRIPTION, "Job 1");
			map.put(KEY_AGENT, "Job 1");

			jobsList.add(map);
		}		

		adapter = new JobsAdapter(this, jobsList);
		this.setListAdapter(adapter);
		ListView listView = getListView();
		listView.setBackgroundColor(Color.WHITE);
	}

	public void onClick(View v) {
		Intent jobsdetailsIntent = new Intent(this, JobsDetailsActivity.class);
		startActivity(jobsdetailsIntent);

	}

	@Override
	public void didFinishRequestProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void didFailRequestProcessing() {
		// TODO Auto-generated method stub

	}
}
