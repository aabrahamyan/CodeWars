package com.skybot.activities;

import java.util.Map;

import com.skybot.activities.delegate.ActionDelegate;

import android.app.Activity;
import android.os.Bundle;

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
	public void didFinishRequestProcessing(Map<?, ?>json) {
		// TODO Auto-generated method stub

		}
	
}
