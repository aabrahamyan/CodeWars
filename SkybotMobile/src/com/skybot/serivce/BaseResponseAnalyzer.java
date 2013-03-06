package com.skybot.serivce;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import android.app.Activity;
import android.util.Log;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.util.Constants;
import com.skybot.util.ViewTracker;

/**
 * Synchronized instance which evaluates response data and passes to the
 * structural parsing
 * 
 * @author aabraham
 * 
 */
public class BaseResponseAnalyzer {

	/**
	 * Handles responses and chains parsing responsibilities
	 * 
	 * @param serviceName
	 * @param map
	 * @param responseData
	 * @throws ParseException
	 */
	public static synchronized void analyze(final String serviceName,
			final String urlWithParams, final String responseData) {

		if (serviceName.equals(Constants.LOGIN_SERVICE)) {

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();
		}
		/******************************************* JOB **********************************************/
		else if (serviceName.equals(Constants.JOB_SERVICE_URL)) {

			String responseString = "";
			responseString = responseData.replace("maxId:", "\"maxId\":");
			responseString = responseString.replace("timestamp:",
					"\"timestamp\":");
			responseString = responseString.replace("growler_message:",
					"\"growler_message\":");
			responseString = responseString.replace("items:", "\"items\":");

			try {
				JSONParser jParser = new JSONParser();
				JSONObject jObject = (JSONObject) jParser.parse(responseString);

				final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

				JSONArray jArray = (JSONArray) jObject.get("items");
				for (int i = 0; i < jArray.size(); i++) {
					JSONObject json_data = (JSONObject) jArray.get(i);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("name", json_data.get("name").toString());					
					map.put("runid", json_data.get("id").toString());
					map.put("agent", json_data.get("agent").toString());
					map.put("description", json_data.get("description")
							.toString());
					map.put("hold_status", json_data.get("hold_status")
							.toString());
					map.put("job_type", json_data.get("job_type").toString());
					map.put("agent_name", json_data.get("agent_name").toString());
					map.put("target_type", json_data.get("target_type").toString());
					map.put("schedule_type", json_data.get("schedule_type").toString());
					map.put("calendar", json_data.get("calendar").toString());
					map.put("day_count_interval",
							json_data.get("day_count_interval").toString());
					map.put("timed_interval_minutes",
							json_data.get("timed_interval_minutes").toString());
					map.put("timed_interval_display_preference",
							json_data.get("timed_interval_display_preference")
									.toString());
					map.put("day_type", json_data.get("day_type").toString());
					map.put("timezone_type", json_data.get("timezone_type")
							.toString());
					map.put("update_counter", json_data.get("update_counter")
							.toString());
					map.put("default_priority",
							json_data.get("default_priority").toString());
					map.put("dependent_via_job_suite",
							json_data.get("dependent_via_job_suite").toString());
					map.put("prerequisite_members",
							json_data.get("prerequisite_members").toString());
					map.put("schedule_type", json_data.get("schedule_type")
							.toString());
					map.put("job_type", json_data.get("job_type").toString());
					map.put("is_reactive", json_data.get("is_reactive")
							.toString());

					list.add(map);
				}
				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();
				Activity jobsActivity = (Activity) ViewTracker.getInstance()
						.getCurrentContext();

				jobsActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing(list);
					}
				});

			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

		}
		/******************************************* JOB HISTORY **********************************************/
		else if (serviceName.equals(Constants.JOBHISTORY_SERVICE_URL)) {

			String responseString = "";
			responseString = responseData.replace("{totalResultsReturned:",
					"{\"totalResultsReturned\":");
			responseString = responseString.replace("totalResultsAvailable:",
					"\"totalResultsAvailable\":");

			responseString = responseString.replace("firstResultPosition:",
					"\"firstResultPosition\":");
			responseString = responseString.replace("maxId:", "\"maxId\":");

			responseString = responseString.replace("timestamp:",
					"\"timestamp\":");

			responseString = responseString.replace("items:", "\"items\":");

			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-history-job-suite'></span>6408_suite_special</span>\"",
							"\"6408_suite_special\"");

			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>6408_reactive</span>\"",
							"\"6408_reactive\"");

			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>Mobile_Job_Test_1</span>\"",
							"\"Mobile_Job_Test_1\"");
			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>Mobile_Job_Test_2</span>\"",
							"\"Mobile_Job_Test_2\"");
			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>Mobile_Job_Test_3</span>\"",
							"\"Mobile_Job_Test_3\"");
			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>Mobile_Job_Test_4</span>\"",
							"\"Mobile_Job_Test_4\"");
			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>Mobile_Job_Test_5</span>\"",
							"\"Mobile_Job_Test_5\"");

			System.out.println(responseString);

			try {
				JSONParser jParser = new JSONParser();
				JSONObject jObject = (JSONObject) jParser.parse(responseString);

				final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

				JSONArray jArray = (JSONArray) jObject.get("items");

				for (int i = 0; i < jArray.size(); i++) {
					JSONObject json_data = (JSONObject) jArray.get(i);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("id", json_data.get("id").toString());
					map.put("job", json_data.get("job").toString());
					map.put("job_id", json_data.get("job_id").toString());
					map.put("job_suite_run_id",
							json_data.get("job_suite_run_id").toString());
					map.put("job_status_raw", json_data.get("job_status_raw")
							.toString());

					list.add(map);
				}
				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();

				Activity jobHistoryActivity = (Activity) ViewTracker
						.getInstance().getCurrentContext();

				jobHistoryActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {

						del.didFinishRequestProcessing(list);
					}
				});

			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

		}
		/******************************************* AGENT **********************************************/
		else if (serviceName.equals(Constants.AGENT_SERVICE_URL)) {

			String responseString = "";
			responseString = responseData.replace("maxId:", "\"maxId\":");
			responseString = responseString.replace("timestamp:",
					"\"timestamp\":");
			responseString = responseString.replace("growler_message:",
					"\"growler_message\":");
			responseString = responseString.replace("items:", "\"items\":");

			responseString = responseString
					.replace(
							"\"<span><span class='icon-agent-active'></span>Active</span>\"",
							"\"Active\"");
			responseString = responseString
					.replace(
							"\"<span><span class='icon-agent-failed'></span>Failed</span>\"",
							"\"Failed\"");

			System.out.println(responseString);

			try {
				JSONParser jParser = new JSONParser();
				JSONObject jObject = (JSONObject) jParser.parse(responseString);

				final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

				JSONArray jArray = (JSONArray) jObject.get("items");
				for (int i = 0; i < jArray.size(); i++) {
					JSONObject json_data = (JSONObject) jArray.get(i);
					HashMap<String, String> map = new HashMap<String, String>();

					map.put("id", json_data.get("id").toString());
					map.put("status", json_data.get("status").toString());
					map.put("name", json_data.get("name").toString());
					map.put("description", json_data.get("description")
							.toString());

					list.add(map);
				}
				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();
				Activity jobsActivity = (Activity) ViewTracker.getInstance()
						.getCurrentContext();

				jobsActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing(list);
					}
				});

			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();
		}

		/******************************************* JOB HISTORY DATA REPORT **********************************************/
		else if (serviceName.equals(Constants.JOBHISTORYREPORT_SERVICE_URL)) {

			String responseString = "";

			responseString = responseData.replace("items:", "\"items\":");
			responseString = responseString.replace("timestamp:",
					"\"timestamp\":");
			responseString = responseString
					.replace(
							"\"<div class='job-history-status job-history-finished'><span class='icon-job-history icon-job-history-finished'></span>Finished</div>\"",
							"\"Finished\"");

			System.out.println(responseString);

			try {
				JSONParser jParser = new JSONParser();
				JSONObject jObject = (JSONObject) jParser.parse(responseString);

				final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

				JSONArray jArray = (JSONArray) jObject.get("items");
				for (int i = 0; i < jArray.size(); i++) {
					JSONObject json_data = (JSONObject) jArray.get(i);
					HashMap<String, String> map = new HashMap<String, String>();

					map.put("id", json_data.get("id").toString());
					map.put("status", json_data.get("status").toString());
					map.put("file_name", json_data.get("file_name").toString());
					map.put("copied_server_time_utc",
							json_data.get("copied_server_time_utc").toString());

					list.add(map);
				}
				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();
				Activity jobsActivity = (Activity) ViewTracker.getInstance()
						.getCurrentContext();

				jobsActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing(list);
					}
				});

			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();
		}

		else if (serviceName.equals(Constants.COMPLETED_JOBS_ID)) {
			Log.i("Parser info", "Entered Completed Jobs sequence");

			String responseString = "";
			responseString = responseData;
			final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			try {

				JSONParser jParser = new JSONParser();
				JSONObject jObject = (JSONObject) jParser.parse(responseString);

				JSONArray jArray = (JSONArray) jObject.get("data");

				for (int i = 0; i < jArray.size(); i++) {
					JSONObject json_data = (JSONObject) jArray.get(i);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("label", json_data.get("label").toString());
					map.put("value", json_data.get("value").toString());
					map.put("real_value", json_data.get("real_value")
							.toString());
					list.add(map);
				}

				for (int i = 0; i < list.size(); i++) {
					Log.w("Element", list.get(i).toString());
				}

			} catch (ParseException e) {
				Log.e("Chart Parser error", "Error parsing Chart Data");

				e.printStackTrace();
			}

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing(list);
		}

		else if (serviceName.equals(Constants.TERMINATED_JOBS_ID)) {
			Log.i("Parser Info", "Entered Terminated Jobs sequence ");
			String responseString = "";
			responseString = responseData;
			final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			try {
				JSONParser jParser = new JSONParser();
				JSONObject jObject = (JSONObject) jParser.parse(responseString);

				JSONArray jArray = (JSONArray) jObject.get("data");
				for (int i = 0; i < jArray.size(); i++) {

					JSONObject json_data = (JSONObject) jArray.get(i);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("label", json_data.get("label").toString());
					map.put("real_canceled_value",
							json_data.get("real_canceled_value").toString());
					map.put("real_failed_value",
							json_data.get("real_failed_value").toString());
					map.put("real_error_value",
							json_data.get("real_error_value").toString());

					list.add(map);

				}

				for (int i = 0; i < list.size(); i++) {
					Log.w("Element", list.get(i).toString());
				}

			} catch (ParseException e) {

				Log.e("Chart Parser error", "Error parsing Chart Data");

				e.printStackTrace();
			}

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing(list);

		}

	}
}
