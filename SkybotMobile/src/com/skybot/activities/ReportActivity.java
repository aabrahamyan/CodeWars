package com.skybot.activities;


import java.util.ArrayList;
import java.util.HashMap;

import com.skybot.activities.delegate.ActionDelegate;

import android.app.Activity;
import android.os.Bundle;

public class ReportActivity extends Activity implements ActionDelegate {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);
    }

	@Override
	public void didFinishRequestProcessing() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void didFinishRequestProcessing(ArrayList<HashMap<String, String>> list) {
		
	}
	@Override
	public void didFailRequestProcessing() {
		// TODO Auto-generated method stub
		
	}
}
