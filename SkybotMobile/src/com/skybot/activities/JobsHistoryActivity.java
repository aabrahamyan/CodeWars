package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.JobsAdapter;
import com.skybot.adapters.JobsHistoryAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Representing and controlling Job History items and logs.
 * 
 * @author gor, armenabrahamyan
 * 
 */

public class JobsHistoryActivity extends ListActivity implements ActionDelegate {
	
	
		static final String KEY_TITLE = "title";
		static final String KEY_RUNNUMBER = "runnumber";
		static final String KEY_SUITRUNNUMBER = "suitrunnumber";

		ListView list;
		JobsHistoryAdapter adapter;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			//super.onCreate(savedInstanceState);
			//setContentView(R.layout.jobs_layout);
			super.onCreate(savedInstanceState);

			ArrayList<HashMap<String, String>> jobsList = new ArrayList<HashMap<String, String>>();

			for (int i = 0; i < 6; i++) {

				HashMap<String, String> map = new HashMap<String, String>();

				map.put(KEY_TITLE, "Job 1");
				map.put(KEY_RUNNUMBER, "Job 1");
				map.put(KEY_SUITRUNNUMBER, "Job 1");

				jobsList.add(map);
			}		

			adapter = new JobsHistoryAdapter(this, jobsList);
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