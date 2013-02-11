package com.skybot.activities;


import com.skybot.activities.delegate.ActionDelegate;

import android.app.Activity;
import android.os.Bundle;


/**
 * Representing and controlling Job History items and logs.
 * @author gor, armenabrahamyan
 *
 */
public class JobsHistoryActivity extends Activity implements ActionDelegate {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobs_history_layout);
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