package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.JobDetailsAdapter;
import com.skybot.util.Util;

public class JobsDetailsActivity extends Activity implements ActionDelegate {

	private ListView listView;
	private JobDetailsAdapter adapter;
	public static HashMap<String, String> map = new HashMap<String, String>();

	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobdetails_list);

		Intent intent = getIntent();
		map = (HashMap<String, String>) intent
				.getSerializableExtra("DetailMap");

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

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list, String service) {

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
			JobsDetailsActivity.this.finish();
			Toast.makeText(getApplicationContext(), "Log out",
					Toast.LENGTH_LONG).show();
		}
	}
}
