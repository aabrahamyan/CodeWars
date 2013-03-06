package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.JobDetailsAdapter;

public class JobsDetailsActivity extends Activity implements ActionDelegate {

	private ListView listView;	
	private JobDetailsAdapter adapter;	
	public static HashMap<String, String> map = new HashMap<String, String>();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobdetails_list);
		
		listView = (ListView) findViewById(R.id.listView2);
		adapter = new JobDetailsAdapter(this, map);
		listView.setAdapter(adapter);
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
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list) {

	}

}
