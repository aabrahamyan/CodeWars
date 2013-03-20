package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.AgentAdapter;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.serivce.parser.dataholder.DataHolder;
import com.skybot.util.Constants;
import com.skybot.util.Util;
import com.skybot.util.ViewTracker;

/**
 * Activity lifecycle for Skybot Agents
 * 
 * @author gor, armenabrahamyan
 * 
 */

public class AgentActivity extends ListActivity implements ActionDelegate {

	private ListView listView;
	private AgentAdapter adapter;

	ArrayList<HashMap<String, String>> agentList = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		listView = getListView();
		adapter = new AgentAdapter(this, agentList);
		listView.setAdapter(adapter);
	}

	private void getAgentResponse() {

		Util.showOrHideActivityIndicator(AgentActivity.this.getParent(), 0,
				"Getting Agents list...");

		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();

		Map<String, String> job_params = creator.createAppropriateMapRequest(
				Constants.DATE, "1362126843029", Constants.RESULTS, "5",
				Constants.SORT, "id", Constants.DIRECTION, "ASC",
				Constants.TAG, "", Constants.TAG_MATCH_ANY, "false",
				Constants.LIMIT, "3"
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
				job_params, Constants.SERVER_URL, Constants.AGENT_SERVICE_URL);

		baseNetworkManager.constructConnectionAndHitGET("Agent data Recieved",
				"Agent Data Request Started", urlStringWithParams, this,
				Constants.AGENT_VIEW, Constants.AGENT_SERVICE_URL);
	}

	public void restartAgent(View v, String agentId) {

		String system_Time = Long.toString(System.currentTimeMillis());
		RequestCreator creator = new RequestCreator();
		final RequestHelper reqHelper = new RequestHelper();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();

		Map<String, String> restart_agent_params = creator
				.createAppropriateMapRequest("authenticity_token",
						LoginActivity.authToken);

		final List<NameValuePair> paramsList = reqHelper
				.createPostDataWithKeyValuePair(restart_agent_params);
		String service_url = Constants.AGENT_SERVICE + Constants.RIGHT_SLASH
				+ agentId + Constants.RIGHT_SLASH + "restart";

		baseNetworkManager.constructConnectionAndHitPOST("Restart Successful",
				"Restart Agent Request Started", paramsList, this,
				Constants.AGENT_VIEW, service_url);

		Map<String, String> agent_params = creator.createAppropriateMapRequest(
				Constants.DATE, system_Time, Constants.RESULTS, "300",
				Constants.SORT, "id", Constants.DIRECTION, "ASC",
				Constants.TAG, "", Constants.TAG_MATCH_ANY, "false",
				Constants.LIMIT, "3", Constants.START, "0"

		);

		String urlStringWithParams = reqHelper
				.constructGetRequestString(agent_params, Constants.SERVER_URL,
						Constants.AGENT_SERVICE_URL);

		baseNetworkManager.constructConnectionAndHitGET("Agent data Recieved",
				"Agent Data Request Started", urlStringWithParams, this,
				Constants.AGENT_VIEW, Constants.AGENT_SERVICE_URL);
	}

	@Override
	public void onResume() {
		super.onResume();
		ViewTracker.getInstance().setCurrentContext(this);
		if (DataHolder.getInstance().agentsList.isEmpty()) {
			getAgentResponse();
		}

		listView = getListView();
		if (agentList != null) {
			adapter.data = agentList;
			adapter.notifyDataSetChanged();
		}
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
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list, String service) {
		agentList = list;

		listView = getListView();
		if (agentList != null) {
			adapter.data = agentList;

			Util.showOrHideActivityIndicator(AgentActivity.this.getParent(), 1,
					"Getting Agents list...");

			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void didFailRequestProcessing() {
		Util.showOrHideActivityIndicator(AgentActivity.this.getParent(), 1,
				"Getting Agents list...");

	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list) {
		// TODO Auto-generated method stub

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

	private final class CancelOnClickListener implements
			DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {

		}
	}

	private final class OkOnClickListener implements
			DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			JobsActivity jobsActivity = new JobsActivity();
			jobsActivity.signOutRequest();			
			AgentActivity.this.finish();
			DataHolder.getInstance().emptyDataSet();
			Toast.makeText(getApplicationContext(), "Log out",
					Toast.LENGTH_LONG).show();
		}
	}
}
