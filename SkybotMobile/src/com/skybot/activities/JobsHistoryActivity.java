package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.JobsHistoryAdapter;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.util.Constants;
import com.skybot.util.ViewTracker;

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

	ArrayList<HashMap<String, String>> jobsList = new ArrayList<HashMap<String, String>>();

	// JSONArray jobsHistoryList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// super.onCreate(savedInstanceState);
		// setContentView(R.layout.jobs_layout);
		super.onCreate(savedInstanceState);

		// Creating JSON Parser instance

		// getting JSON string from URL

		// Getting Array of Contacts
		// responseString = json.getJSONArray(KEY_TITLE);

		for (int i = 0; i < 5; i++) {

			HashMap<String, String> map = new HashMap<String, String>();

			// map.put(KEY_ID, parser.getValue(e, KEY_ID));
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

	private void getJobsHistoryResponse() {
		String system_Time = Long.toString(System.currentTimeMillis());
		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();

		Map<String, String> job_params = creator.createAppropriateMapRequest(
				Constants.DATE, system_Time, Constants.RESULTS, "1",
				Constants.SORT, "id", Constants.DIRECTION,
				"DESC",
				Constants.TAG,
				"",
				Constants.TAG_MATCH_ANY,
				"false",

				// Additional constants for job history
				Constants.DATAFILTERFIELD, "server_initiated_time_utc",
				Constants.DATAFILTERDATACOMPARASION, "eq",
				Constants.DATAFILTERDATATYPE, "dateTime",
				Constants.DATAFILTERVALUE, "2013-02-26T00:00:00",
				Constants.EXCLUDETIMEDINTERVAL, "false", Constants.START, "0",
				Constants.LIMIT, "5"

		);

		final RequestHelper reqHelper = new RequestHelper();
		String urlStringWithParams = reqHelper.constructGetRequestString(
				job_params, Constants.SERVER_URL,
				Constants.JOBHISTORY_SERVICE_URL);

		baseNetworkManager.constructConnectionAndHitGET("Login Successful",
				"Jobs History Request Started", urlStringWithParams, this,
				Constants.JOBSHISTORY_VIEW, Constants.JOBHISTORY_SERVICE_URL);
	}

	@Override
	public void onResume() {
		super.onResume();
		ViewTracker.getInstance().setCurrentContext(this);
		getJobsHistoryResponse();
	};

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

	@Override
	public void didFinishRequestProcessing(Map<?, ?> json) {
		// TODO Auto-generated method stub

		jobsList.add((HashMap<String, String>) json);
		this.setListAdapter(adapter);
	}
}