package com.skybot.activities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.skybot.activities.delegate.ActionDelegate;

/**
 * Main Activity where all other pages and activities are initiated in Tab Bar
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class SkybotTabLayoutActivity extends TabActivity implements
		ActionDelegate {
	/** Tab Layout. */
	/**
	 * TODO: USING TABACTIVITY IS DEPRECATED INSTEAD WE NEED TO CREATE TABS WITH
	 * FRAGMENTS - A.A.
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TabHost tabHost = getTabHost();

		// Tab for Job
		TabSpec jobsspec = tabHost.newTabSpec("Jobs");
		jobsspec.setIndicator("Jobs",
				getResources().getDrawable(R.drawable.icon_dashboard_tab));

		Intent jobsIntent = new Intent(this, JobsActivity.class);
		jobsspec.setContent(jobsIntent);

		// Tab for Dash board
		TabSpec dashboardspec = tabHost.newTabSpec("Dashboard");
		// setting Title and Icon for the Tab
		dashboardspec.setIndicator("Dashboard",
				getResources().getDrawable(R.drawable.icon_dashboard_tab));
		Intent dashboardIntent = new Intent(this, DashboardActivity.class);
		dashboardspec.setContent(dashboardIntent);

		// Tab for Job History
		TabSpec jobhistspec = tabHost.newTabSpec("Jobs History");
		jobhistspec.setIndicator("Jobs History",
				getResources().getDrawable(R.drawable.icon_dashboard_tab));
		Intent jobhistsIntent = new Intent(this, JobsHistoryActivity.class);
		jobhistspec.setContent(jobhistsIntent);

		// Tab for Agent
		TabSpec agentspec = tabHost.newTabSpec("Agent");
		agentspec.setIndicator("Agent",
				getResources().getDrawable(R.drawable.icon_dashboard_tab));
		Intent agentIntent = new Intent(this, AgentActivity.class);
		agentspec.setContent(agentIntent);

		// Tab for Report
		TabSpec reportspec = tabHost.newTabSpec("Report");
		reportspec.setIndicator("Report",
				getResources().getDrawable(R.drawable.icon_dashboard_tab));
		Intent reportIntent = new Intent(this, ReportActivity.class);
		reportspec.setContent(reportIntent);

		// Tab for API key
		TabSpec apikeyspec = tabHost.newTabSpec("API key");
		apikeyspec.setIndicator("API key",
				getResources().getDrawable(R.drawable.icon_dashboard_tab));
		Intent apikeyIntent = new Intent(this, SettingsActivity.class);
		apikeyspec.setContent(apikeyIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(jobsspec); // Adding Job tab
		tabHost.addTab(dashboardspec); // Adding Dash board tab
		tabHost.addTab(jobhistspec); // Adding Job History tab
		tabHost.addTab(agentspec); // Adding agent tab
		tabHost.addTab(reportspec); // Adding Report tab
		tabHost.addTab(apikeyspec); // Adding API key tab

	}

	

	
	// ---------------------------------- Menu Callbacks
	// -------------------------------//
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_apikey:

			return true;
		case R.id.menu_save:
			Toast.makeText(SkybotTabLayoutActivity.this, "Save is Selected",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_search:
			Toast.makeText(SkybotTabLayoutActivity.this, "Search is Selected",
					Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
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
	@Override
	public void didFinishRequestProcessing(Map<?, ?>json) {
		// TODO Auto-generated method stub

		}

}