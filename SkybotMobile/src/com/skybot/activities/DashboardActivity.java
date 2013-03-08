package com.skybot.activities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.ScrollItemsFragmentAdapter;
import com.skybot.charts.singleton.ChartSingleton;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.util.Constants;
import com.skybot.util.Util;
import com.skybot.util.ViewTracker; 
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;




/**
 * Activity for representing Dashboard charts and statistics
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class DashboardActivity extends FragmentActivity implements ActionDelegate{
	
	private List<ArrayList<HashMap<String, String>>> dataList;
	private ScrollItemsFragmentAdapter pagerAdapter;
	private ViewPager pager;
	private ChartSingleton chartSingleton = ChartSingleton.getInstance();
	private PageIndicator mIndicator;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_layout);	
		
		
		/** Getting a reference to the ViewPager defined the layout file */
		pager = (ViewPager) findViewById(R.id.pager);				
		
		/** Getting fragment manager */
		FragmentManager fm = getSupportFragmentManager();
		
		/** Instantiating FragmentPagerAdapter */
		pagerAdapter = new ScrollItemsFragmentAdapter(fm);
			
		sendAndGetCharts();
		
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
	
	public void getSubmittedJobResponse() {
		String system_time = Long.toString(System.currentTimeMillis());
		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();
		
		Map <String,String> dashboard_params = creator.createAppropriateMapRequest(Constants.DATE,system_time);
		
		StringBuilder stringBuilder = new StringBuilder(Constants.SERVER_URL);
		stringBuilder.append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.SUBMITTED_JOBS_ID)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE_URL)
					 .append(Constants.FIRST_PARAM_SEPARATOR)
					 .append(Constants.DATE)
					 .append(Constants.EQUAL)
					 .append(system_time);
		
		String stringWithUrlAndParams = stringBuilder.toString();
		
		baseNetworkManager.constructConnectionAndHitGET("Chart Request is successful", "Chart Request Started", stringWithUrlAndParams, 
				this, Constants.DASHBOARD_VIEW, Constants.SUBMITTED_JOBS_ID);
		Log.i("Request Status", "Request completed");
	}
	
	public void getAgentEventsProcessedResponse() {
		String system_time = Long.toString(System.currentTimeMillis());
		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();
		
		Map <String,String> dashboard_params = creator.createAppropriateMapRequest(Constants.DATE,system_time);
		
		StringBuilder stringBuilder = new StringBuilder(Constants.SERVER_URL);
		stringBuilder.append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.AGENT_EVENT_PROCESSED_ID)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE_URL)
					 .append(Constants.FIRST_PARAM_SEPARATOR)
					 .append(Constants.DATE)
					 .append(Constants.EQUAL)
					 .append(system_time);
		
		String stringWithUrlAndParams = stringBuilder.toString();
		
		baseNetworkManager.constructConnectionAndHitGET("Chart Request is successful", "Chart Request Started", stringWithUrlAndParams, 
				this, Constants.DASHBOARD_VIEW, Constants.AGENT_EVENT_PROCESSED_ID);
		Log.i("Request Status", "Request completed");
	}
	
	public void sendAndGetCharts() {
		Util.showOrHideActivityIndicator(DashboardActivity.this.getParent(), 0,
		"Getting Dashboard info...");
		
		chartSingleton.charts_reg_counter = 4;
		
		getCompletedJobsResponse();
		getTerminatedJobsResponse();
		getSubmittedJobResponse();
		getAgentEventsProcessedResponse();
		
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
		
	}
	
	@Override
	public void didFinishRequestProcessing(ArrayList<HashMap<String, String>> list, String service) {						
		
		for(int i=0;i<list.size();i++) {
			Log.e("Thread", list.get(i).toString());
		}
		
		
		
		if(chartSingleton.charts_reg_counter>0) {
			
			chartSingleton.charts_reg_counter--;
			
			if(service.equals("terminated_jobs")) {
				chartSingleton.terminatedArrayLIst = list;
			}
			else if (service.equals("completed_jobs")) {
				chartSingleton.completedArayList = list;
			}
			else if (service.equals("submitted_jobs")) {
				chartSingleton.submittedArrayList = list;
			}
			else if(service.equals("agent_event_processed_jobs")) {
				chartSingleton.agentEventProcessedArrayList = list;
			}
			
			if(chartSingleton.charts_reg_counter == 0) {			

				if(pager.getAdapter() == null) {
					
					pager.setAdapter(pagerAdapter);
					
					CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
			        mIndicator = indicator;
					
					mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
			        mIndicator.setViewPager(pager);
			        
			        final float density = getResources().getDisplayMetrics().density;
			        indicator.setBackgroundColor(Color.parseColor("#FFFFFF"));
			        indicator.setRadius(5 * density);
			        indicator.setPageColor(Color.WHITE);
			        indicator.setFillColor(Color.parseColor("#65BDE3"));
			        indicator.setStrokeColor(Color.GRAY);
			        indicator.setStrokeWidth(1);
				}
				
				Util.showOrHideActivityIndicator(DashboardActivity.this.getParent(), 1,
				"Getting Dashboard info...");
				pager.getAdapter().notifyDataSetChanged();
				
			}
				
		}
		
//		else if(chartSingleton.charts_reg_counter == 0) {			
//
//			
//				if(pager.getAdapter() == null) {
//					pager.setAdapter(pagerAdapter);
//				}
//				
//				pager.getAdapter().notifyDataSetChanged();
//				
//		}
		
		
				
		//pagerAdapter.data = list; //dataList;				
		
		
		
		Log.i("Kuku","KuKu");		
	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list) {
		// TODO Auto-generated method stub
		
	}


	}
	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
	 */
