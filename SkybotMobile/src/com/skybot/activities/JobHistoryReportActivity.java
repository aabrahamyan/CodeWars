package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.JobHistoryReportAdapter;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.util.Constants;
import com.skybot.util.ViewTracker;

public class JobHistoryReportActivity extends ListActivity implements
		ActionDelegate {

	private ListView listView;
	private JobHistoryReportAdapter adapter;

	ArrayList<HashMap<String, String>> jobHistoryReportList = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		listView = getListView();
		adapter = new JobHistoryReportAdapter(this, jobHistoryReportList);
		listView.setAdapter(adapter);
	}

	private void getJobHistoryReportResponse() {
		String system_Time = Long.toString(System.currentTimeMillis());
		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();

		Map<String, String> job_params = creator.createAppropriateMapRequest(

		Constants.USERID, "1", Constants.DATE, "1362129459671",
				Constants.RESULTS, "5", Constants.SORT,
				"copied_server_time_utc", Constants.DIRECTION, "DESC",
				Constants.TAG, "", Constants.LIMIT, "3"

		);

		final RequestHelper reqHelper = new RequestHelper();
		String urlStringWithParams = reqHelper.constructGetRequestString(
				job_params, Constants.SERVER_URL,
				Constants.JOBHISTORYREPORT_SERVICE_URL);

		baseNetworkManager.constructConnectionAndHitGET(
				"Job History Report Data Recieved",
				"Job History Report Data Request Started", urlStringWithParams,
				this, Constants.JOBHISTORYREPORT_VIEW,
				Constants.JOBHISTORYREPORT_SERVICE_URL);
	}

	@Override
	public void onResume() {
		super.onResume();
		ViewTracker.getInstance().setCurrentContext(this);
		getJobHistoryReportResponse();

		listView = getListView();
		if (jobHistoryReportList != null) {
			adapter.data = jobHistoryReportList;
			adapter.notifyDataSetChanged();
		}
	};

	public void onClick(View v) {
		Intent jobsdetailsIntent = new Intent(this, JobsDetailsActivity.class);
		startActivity(jobsdetailsIntent);

	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list) {
		jobHistoryReportList = list;

		listView = getListView();
		if (jobHistoryReportList != null) {
			adapter.data = jobHistoryReportList;
			adapter.notifyDataSetChanged();
		}
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
