package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.JobHistoryReportAdapter;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.serivce.parser.dataholder.DataHolder;
import com.skybot.util.Constants;
import com.skybot.util.Util;
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

		Util.showOrHideActivityIndicator(
				JobHistoryReportActivity.this.getParent(), 0,
				"Getting Job History Reports...");

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
		if (DataHolder.getInstance().reportsList.isEmpty()) {
			getJobHistoryReportResponse();
		}

		listView = getListView();
		if (jobHistoryReportList != null) {
			adapter.data = jobHistoryReportList;
			adapter.notifyDataSetChanged();
		}
	};

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list, String service) {
		jobHistoryReportList = list;

		listView = getListView();
		if (jobHistoryReportList != null) {
			adapter.data = jobHistoryReportList;
<<<<<<< HEAD

			Util.showOrHideActivityIndicator(
					JobHistoryReportActivity.this.getParent(), 1,
					"Getting Job History Reports...");

			Util.showOrHideActivityIndicator(
					JobHistoryReportActivity.this.getParent(), 1,
					"Getting Job History Reports...");
=======

			
			Util.showOrHideActivityIndicator(JobHistoryReportActivity.this.getParent(), 1,
			"Getting Job History Reports...");
			
			

			/*
			 * Util.showOrHideActivityIndicator(
			 * JobHistoryReportActivity.this.getParent(), 0,
			 * "Getting Job History Reports...");
			 */
>>>>>>> 564f325c4c2d80ab8cebfbe57368dc5602611e60

			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void didFinishRequestProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void didFailRequestProcessing() {
		Util.showOrHideActivityIndicator(
				JobHistoryReportActivity.this.getParent(), 1,
				"Getting Job History Reports...");

	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list) {

		Util.showOrHideActivityIndicator(
				JobHistoryReportActivity.this.getParent(), 1,
				"Getting Job History Reports...");

	}

}
