package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.JobsHistoryAdapter;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.serivce.parser.dataholder.DataHolder;
import com.skybot.util.Constants;
import com.skybot.util.Util;
import com.skybot.util.ViewTracker;

/**
 * Representing and controlling Job History items and logs.
 * 
 * @author gor, armenabrahamyan
 * 
 */

public class JobsHistoryActivity extends ListActivity implements ActionDelegate {

	static final String KEY_TITLE = "id";
	static final String KEY_RUNNUMBER = "runnumber";
	static final String KEY_SUITRUNNUMBER = "suitrunnumber";

	private ListView listView;
	private JobsHistoryAdapter adapter;

	ArrayList<HashMap<String, String>> jobsList = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		listView = getListView(); //
		adapter = new JobsHistoryAdapter(this, jobsList);
		listView.setAdapter(adapter);
	}

	private void getJobsHistoryResponse() {
		Util.showOrHideActivityIndicator(JobsHistoryActivity.this.getParent(),
				0, "Requesting Job Histories...");
		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();
		String system_Time = Long.toString(System.currentTimeMillis());

		Map<String, String> job_params = creator.createAppropriateMapRequest(
				Constants.DATE, system_Time, Constants.RESULTS, "5",
				Constants.SORT, "id", Constants.DIRECTION, "DESC",
				Constants.TAG, "", Constants.TAG_MATCH_ANY, "false"

		// Additional constants for job history
				/*
				 * Constants.DATAFILTERFIELD, "server_initiated_time_utc",
				 * Constants.DATAFILTERDATACOMPARASION, "eq",
				 * Constants.DATAFILTERDATATYPE, "dateTime",
				 * Constants.DATAFILTERVALUE, "2013-02-26T00:00:00",
				 * Constants.EXCLUDETIMEDINTERVAL, "false", Constants.START,
				 * "0", Constants.LIMIT, "3"
				 */

				);

		final RequestHelper reqHelper = new RequestHelper();
		String urlStringWithParams = reqHelper.constructGetRequestString(
				job_params, Constants.SERVER_URL,
				Constants.JOBHISTORY_SERVICE_URL);

		baseNetworkManager.constructConnectionAndHitGET(
				"Jobs History Recieved", "Jobs History Request Started",
				urlStringWithParams, this, Constants.JOBSHISTORY_VIEW,
				Constants.JOBHISTORY_SERVICE_URL);
	}

	@Override
	public void onResume() {
		super.onResume();
		ViewTracker.getInstance().setCurrentContext(this);

		if (DataHolder.getInstance().jobHistoriesList.isEmpty()) {
			getJobsHistoryResponse();
		}

		// listView = (ListView) findViewById(R.id.listView2);

		listView = getListView();
		if (jobsList != null) {
			adapter.data = jobsList;
			adapter.notifyDataSetChanged();
		}
	};

	@Override
	public void didFinishRequestProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list, String service) {
		jobsList = list;

		listView = getListView();
		if (jobsList != null) {
			Util.showOrHideActivityIndicator(
					JobsHistoryActivity.this.getParent(), 1,
					"Requesting Job Histories...");
			adapter.data = jobsList;
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void didFailRequestProcessing() {
		Util.showOrHideActivityIndicator(JobsHistoryActivity.this.getParent(), 1,
				"Requesting Job Histories...");

	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list) {
		// TODO Auto-generated method stub

	}

}