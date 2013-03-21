package com.skybot.serivce.parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.skybot.serivce.parser.dataholder.DataHolder;
import com.skybot.util.Constants;

public class JobHistoriesParser {

	/**
	 * 
	 * @param responseJsonString
	 * @throws ParseException
	 */
	public void parseData(final String responseData, final String serviceName)
			throws ParseException {
		// --------------- Remove HTML from Response --------------//
		final String responseString = analyzeAndInjectHTML(responseData);

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
			map.put("job_suite_run_id", json_data.get("job_suite_run_id")
					.toString());
			map.put("job_status_raw", json_data.get("job_status_raw")
					.toString());
			map.put("job_suite_name", json_data.get("job_suite_name")
					.toString());
			map.put("agent", json_data.get("agent").toString());
			map.put("agent_group_name", json_data.get("agent_group_name")
					.toString());
			map.put("job_status", json_data.get("job_status").toString());
			map.put("job_retry_number", json_data.get("job_retry_number")
					.toString());
			map.put("job_retry_next_time", json_data.get("job_retry_next_time")
					.toString());
			map.put("job_initiated_code", json_data.get("job_initiated_code")
					.toString());
			map.put("scheduled_time_utc", json_data.get("scheduled_time_utc")
					.toString());
			map.put("scheduled_time_agent",
					json_data.get("scheduled_time_agent").toString());
			map.put("server_initiated_time_utc",
					json_data.get("server_initiated_time_utc").toString());
			map.put("server_ended_time_utc",
					json_data.get("server_ended_time_utc").toString());
			map.put("queue_ended_time_utc",
					json_data.get("queue_ended_time_utc").toString());
			map.put("queued_duration", json_data.get("queued_duration")
					.toString());
			map.put("running_duration", json_data.get("running_duration")
					.toString());
			map.put("duration", json_data.get("duration").toString());
			map.put("restart_history", json_data.get("restart_history")
					.toString());
			map.put("job_priority", json_data.get("job_priority").toString());

			list.add(map);

		}

		if (serviceName.equals(Constants.MORE_JOB_HISTORIES)) {
			// TODO: Investigate RESULT PARAMETER PROBLEM LATER: A.A.
			DataHolder.getInstance().emptyJobHistoriesList();
			DataHolder.getInstance().jobHistoriesList.addAll(list);
		} else {
			DataHolder.getInstance().emptyJobHistoriesList();
			DataHolder.getInstance().jobHistoriesList.addAll(list);
		}
	}

	private String analyzeAndInjectHTML(final String responseData) {
		String responseString = "";
		responseString = responseData.replace("{totalResultsReturned:",
				"{\"totalResultsReturned\":");
		responseString = responseString.replace("totalResultsAvailable:",
				"\"totalResultsAvailable\":");

		responseString = responseString.replace("firstResultPosition:",
				"\"firstResultPosition\":");
		responseString = responseString.replace("maxId:", "\"maxId\":");

		responseString = responseString.replace("timestamp:", "\"timestamp\":");

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
						"\"<span><span class='icon-job-individual-job'></span></span>\"",
						"\"");

		responseString = responseString
				.replace(
						"\"<span><span class='job-history-schedule-time job-history-server-time-zone'><span class='icon-job-history icon-job-history-server-time-zone'></span>",
						"");
		responseString = responseString.replace(
				"<span><span class='icon-job-history-scheduled'></span>", "");
		responseString = responseString
				.replace(
						"<div class='job-history-agent-schedule-time job-history-server-time-zone'><span class='icon-job-history icon-job-history-server-time-zone'></span>",
						"");
		responseString = responseString
				.replace(
						"<div class='job-history-schedule-time job-history-server-time-zone'><span class='icon-job-history icon-job-history-server-time-zone'></span>",
						"");
		responseString = responseString
				.replace(
						"<div class='job-history-status job-history-complete'><span class='icon-job-history icon-job-history-complete'></span>",
						"");
		responseString = responseString
				.replace(
						"<div class='job-history-status job-history-submitted'><span class='icon-job-history icon-job-history-submitted'></span>",
						"");
		responseString = responseString
				.replace(
						"<div class='job-history-status job-history-failed'><span class='icon-job-history icon-job-history-failed'></span>",
						"");
		responseString = responseString.replace("</div>", "");
		responseString = responseString.replace("<div>", "");
		responseString = responseString.replace("</span>", "");
		responseString = responseString.replace("<span>", "");
		responseString = responseString.replace(
				"<span class='icon-job-individual-job'>", "");
		responseString = responseString.replace(
				"<span class='icon-job-history-user'>", "");

		return responseString;
	}

}
