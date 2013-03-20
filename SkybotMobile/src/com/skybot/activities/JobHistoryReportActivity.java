package com.skybot.activities;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.JobHistoryReportAdapter;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.serivce.parser.dataholder.DataHolder;
import com.skybot.util.Constants;
import com.skybot.util.Util;
import com.skybot.util.ViewTracker;

/**
 * Job History Report Activity
 * 
 * @author armenabrahamyan
 * 
 */
public class JobHistoryReportActivity extends ListActivity implements
		ActionDelegate {

	private ListView listView;
	private JobHistoryReportAdapter adapter;
	private ProgressDialog mProgressDialog;

	ArrayList<HashMap<String, String>> jobHistoryReportList = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		listView = getListView();
		adapter = new JobHistoryReportAdapter(this, jobHistoryReportList);
		listView.setAdapter(adapter);

	}

	/**
	 * Getting Job History Reports...
	 */
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
				Constants.TAG, "", Constants.LIMIT, "300", Constants.START, ""

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

	/**
	 * Getting Job History Reports...More functionality
	 */
	private void getMoreJobHistoryReportResponse() {

		Util.showOrHideActivityIndicator(
				JobHistoryReportActivity.this.getParent(), 0,
				"Getting Job History Reports...");

		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();

		Map<String, String> job_params = creator
				.createAppropriateMapRequest(

						Constants.USERID,
						"1",
						Constants.DATE,
						"1362129459671",
						Constants.RESULTS,
						String.valueOf(DataHolder.getInstance().JOBS_HISTORIES_REPORTS_MORE_END_INDEX),
						Constants.SORT,
						"copied_server_time_utc",
						Constants.DIRECTION,
						"DESC",
						Constants.TAG,
						"",
						Constants.LIMIT,
						"300",
						Constants.START,
						String.valueOf(DataHolder.getInstance().JOBS_HISTORIES_REPORTS_MORE_START_INDEX)

				);

		final RequestHelper reqHelper = new RequestHelper();
		String urlStringWithParams = reqHelper.constructGetRequestString(
				job_params, Constants.SERVER_URL,
				Constants.JOBHISTORYREPORT_SERVICE_URL);

		baseNetworkManager.constructConnectionAndHitGET(
				"Job History Report Data Recieved",
				"Job History Report Data Request Started", urlStringWithParams,
				this, Constants.JOBHISTORYREPORT_VIEW,
				Constants.MORE_JOB_HISTORIES_REPORTS);

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

			Util.showOrHideActivityIndicator(
					JobHistoryReportActivity.this.getParent(), 1,
					"Getting Job History Reports...");

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

		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}

	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list) {

		Util.showOrHideActivityIndicator(
				JobHistoryReportActivity.this.getParent(), 1,
				"Getting Job History Reports...");

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onBackPressed() {
		showDialog(10);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 10:
			// Create out AlterDialog
			Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Do you want to log out?");
			builder.setCancelable(true);
			builder.setPositiveButton("Yes", new OkOnClickListener());
			builder.setNegativeButton("No", new CancelOnClickListener());
			AlertDialog dialog = builder.create();
			dialog.show();
		}
		return super.onCreateDialog(id);
	}

	// --------------------------- Progress Dialog Part ---------------------//

	/**
	 * SHows progress Dialog
	 */
	public void showProgressDialog() {

		// instantiate it within the onCreate method
		mProgressDialog = new ProgressDialog(this.getParent());
		mProgressDialog.setMessage("Downloading...");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(100);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		mProgressDialog.show();
	}

	/**
	 * Integer progress
	 * 
	 * @param progress
	 */
	public void updateProgressDialog(final Integer progress) {
		mProgressDialog.setProgress(progress);

		if (progress == 100) {
			mProgressDialog.dismiss();
			readAndViewReport();
		}
	}

	/**
	 * Read report
	 */
	private void readAndViewReport() {
		String path = "/sdcard/CURRENT_REPORT.pdf";
		File targetFile = new File(path);
		Uri targetUri = Uri.fromFile(targetFile);

		Intent intent;
		intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(targetUri, "application/pdf");

		startActivity(intent);
	}

	/**
	 * 
	 * @author armenabrahamyan
	 * 
	 */
	private final class CancelOnClickListener implements
			DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {

		}
	}

	/**
	 * 
	 * @author armenabrahamyan
	 * 
	 */
	private final class OkOnClickListener implements
			DialogInterface.OnClickListener {

		public void onClick(DialogInterface dialog, int which) {
			JobsActivity.getActivity().signOutRequest();			
			JobHistoryReportActivity.this.finish();
			DataHolder.getInstance().emptyDataSet();
			Toast.makeText(getApplicationContext(), "Log out",
					Toast.LENGTH_LONG).show();
		}

	}

	/****************************** Menu Callbacks ************************************/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.more_refresh_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_refresh:
			DataHolder.getInstance().JOBS_HISTORIES_REPORTS_MORE_END_INDEX = 5;
			DataHolder.getInstance().JOBS_HISTORIES_REPORTS_MORE_START_INDEX = 0;
			// getJobsHistoryResponse();
			getJobHistoryReportResponse();
			return true;
		case R.id.menu_more:
			DataHolder.getInstance().JOBS_HISTORIES_REPORTS_MORE_END_INDEX += DataHolder
					.getInstance().JOBS_HISTORIES_REPORTS_MORE_NEXT_STEP;
			DataHolder.getInstance().JOBS_HISTORIES_REPORTS_MORE_START_INDEX += DataHolder
					.getInstance().JOBS_HISTORIES_REPORTS_MORE_NEXT_STEP;
			// getJobsMoreHistoryResponse();
			getMoreJobHistoryReportResponse();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
