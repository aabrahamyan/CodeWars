package com.skybot.activities;


import com.skybot.activities.delegate.ActionDelegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Activity for representing Dashboard items for overall statistics. This class
 * will contain also ScrollView implementation control
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class JobsActivity extends Activity implements ActionDelegate{
	
	
	public void onClick(View v) {
		Intent jobsdetailsIntent = new Intent(this, JobsDetailsActivity.class);
		startActivity(jobsdetailsIntent);

	}
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobs_layout);
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
