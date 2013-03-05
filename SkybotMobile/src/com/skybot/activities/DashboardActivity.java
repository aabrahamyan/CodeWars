package com.skybot.activities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.ScrollItemsFragmentAdapter;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.util.Constants;
import com.skybot.util.ViewTracker;


/**
 * Activity for representing Dashboard charts and statistics
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class DashboardActivity extends FragmentActivity implements ActionDelegate{
	
	private ArrayList<HashMap<String, String>> dataList;
	private ScrollItemsFragmentAdapter pagerAdapter;
	private Handler handler = new Handler();
	private int REQUEST_IN_PROGRESS = 0;
	private int REQUEST_FINISHED = 1;
	private ViewPager pager;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_layout);	
		
		/** Getting a reference to the ViewPager defined the layout file */
		pager = (ViewPager) findViewById(R.id.pager);				
		
		/** Getting fragment manager */
		FragmentManager fm = getSupportFragmentManager();
		
		/** Instantiating FragmentPagerAdapter */
		pagerAdapter = new ScrollItemsFragmentAdapter(fm);
			
		getCompletedJobsResponse();
		
		//getTerminatedJobsResponse();
	}
	
	
	private void getCompletedJobsResponse() {
		
		String system_time = Long.toString(System.currentTimeMillis());
		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();
		
		Map <String,String> dashboard_params = creator.createAppropriateMapRequest(Constants.DATE,system_time);
		
		StringBuilder stringBuilder = new StringBuilder(Constants.SERVER_URL);
		stringBuilder.append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.COMPLETED_JOBS_ID)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE_URL)
					 .append(Constants.FIRST_PARAM_SEPARATOR)
					 .append(Constants.DATE)
					 .append(Constants.EQUAL)
					 .append(system_time);
		
		String stringWithUrlAndParams = stringBuilder.toString();
		
		baseNetworkManager.constructConnectionAndHitGET("Chart Request is successful", "Chart Request Started", stringWithUrlAndParams, 
				this, Constants.DASHBOARD_VIEW, Constants.COMPLETED_JOBS_ID);
		Log.i("Request Status", "Request completed");
		
		
	}
	
	public void getTerminatedJobsResponse() {
		String system_time = Long.toString(System.currentTimeMillis());
		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();
		
		Map <String,String> dashboard_params = creator.createAppropriateMapRequest(Constants.DATE,system_time);
		
		StringBuilder stringBuilder = new StringBuilder(Constants.SERVER_URL);
		stringBuilder.append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.TERMINATED_JOBS_ID)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE_URL)
					 .append(Constants.FIRST_PARAM_SEPARATOR)
					 .append(Constants.DATE)
					 .append(Constants.EQUAL)
					 .append(system_time);
		
		String stringWithUrlAndParams = stringBuilder.toString();
		
		baseNetworkManager.constructConnectionAndHitGET("Chart Request is successful", "Chart Request Started", stringWithUrlAndParams, 
				this, Constants.DASHBOARD_VIEW, Constants.TERMINATED_JOBS_ID);
		Log.i("Request Status", "Request completed");
	}
	
	@Override
	protected void onResume() {	
		super.onResume();
		
		ViewTracker.getInstance().setCurrentContext(this);
		ViewTracker.getInstance().setCurrentViewName("Dashboard");
	}
	
	@Override
	public void didFinishRequestProcessing() {
		
			
	}

	@Override
	public void didFailRequestProcessing() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void didFinishRequestProcessing(ArrayList<HashMap<String, String>> list) {						
		
		for(int i=0;i<list.size();i++) {
			Log.e("Thread", list.get(i).toString());
		}
		
		dataList = list;
		
		
		
		pagerAdapter.data = list;//dataList;				
		
		if(pager.getAdapter() == null) {
			pager.setAdapter(pagerAdapter);
		}
		
		pager.getAdapter().notifyDataSetChanged();
				
		Log.i("KukU","KuKu");		
	}


	}
	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
	 */
