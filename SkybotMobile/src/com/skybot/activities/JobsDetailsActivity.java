package com.skybot.activities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;

import com.skybot.activities.delegate.ActionDelegate;

public class JobsDetailsActivity extends Activity implements ActionDelegate {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobs_details);
	}

	@Override
	public void didFinishRequestProcessing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void didFailRequestProcessing() {
		// TODO Auto-generated method stub
		
	}


	
	@Override
	public void didFinishRequestProcessing(ArrayList<HashMap<String, String>> list, String service) {
		
	}
	
}
