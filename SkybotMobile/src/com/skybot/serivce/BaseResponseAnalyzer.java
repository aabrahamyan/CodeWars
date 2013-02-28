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

				ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				JSONArray jArray = (JSONArray) jObject.get("items");
				for (int i = 0; i < jArray.size(); i++) {
					JSONObject json_data = (JSONObject) jArray.get(i);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("name", json_data.get("name").toString());
					map.put("agent", json_data.get("agent").toString());
					map.put("description", json_data.get("description")
							.toString());
					map.put("hold_status", json_data.get("hold_status")
							.toString());

					list.add(map);
				}
				ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
						.getCurrentContext();
				del.didFinishRequestProcessing(list);
			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();
		}

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
							"\">Mobile_Job_Test\"");
			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>Mobile_Job_Test_2</span>\"",
							"\">Mobile_Job_Test\"");
			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>Mobile_Job_Test_3</span>\"",
							"\">Mobile_Job_Test\"");
			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>Mobile_Job_Test_4</span>\"",
							"\">Mobile_Job_Test\"");
			responseString = responseString
					.replace(
							"\"<span><span class='icon-job-individual-job'></span>Mobile_Job_Test_5</span>\"",
							"\">Mobile_Job_Test\"");

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

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();

		}

	}
}
