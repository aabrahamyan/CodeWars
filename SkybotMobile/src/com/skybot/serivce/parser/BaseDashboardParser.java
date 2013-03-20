package com.skybot.serivce.parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.skybot.serivce.parser.dataholder.DataHolder;

/**
 * 
 * @author aabraham
 * 
 */
public class BaseDashboardParser {

	/**
	 * 
	 * @param responseJsonString
	 * @throws ParseException
	 */
	public void parseCompletedJobsData(final String responseData)
			throws ParseException {
		String responseString = "";
		responseString = responseData;

		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(responseString);

		JSONArray jArray = (JSONArray) jObject.get("data");

		final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < jArray.size(); i++) {
			JSONObject json_data = (JSONObject) jArray.get(i);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("label", json_data.get("label").toString());
			map.put("value", json_data.get("value").toString());
			map.put("real_value", json_data.get("real_value").toString());

			list.add(map);
		}

		DataHolder.getInstance().emptyCompletedJobsList();
		DataHolder.getInstance().completedJobsList.addAll(list);

	}

	/**
	 * 
	 * @param responseJsonString
	 * @throws ParseException
	 */
	public void parseTerminatedJobsData(final String responseData)
			throws ParseException {
		String responseString = "";
		responseString = responseData;

		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(responseString);

		final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		JSONArray jArray = (JSONArray) jObject.get("data");
		for (int i = 0; i < jArray.size(); i++) {

			JSONObject json_data = (JSONObject) jArray.get(i);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("label", json_data.get("label").toString());
			map.put("real_canceled_value", json_data.get("real_canceled_value")
					.toString());
			map.put("real_failed_value", json_data.get("real_failed_value")
					.toString());
			map.put("real_error_value", json_data.get("real_error_value")
					.toString());

			list.add(map);
		}

		DataHolder.getInstance().emptyTerminatedJobsList();
		DataHolder.getInstance().terminatedJobsList.addAll(list);
	}

	/**
	 * 
	 * @param responseJsonString
	 * @throws ParseException
	 */
	public void parseSubmittedJobsData(final String responseData)
			throws ParseException {
		String responseString = "";
		responseString = responseData;

		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(responseString);
		JSONArray jArray = (JSONArray) jObject.get("data");

		final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < jArray.size(); i++) {
			JSONObject json_data = (JSONObject) jArray.get(i);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("label", json_data.get("label").toString());
			map.put("value", json_data.get("value").toString());
			map.put("real_value", json_data.get("real_value").toString());

			list.add(map);
		}

		DataHolder.getInstance().emptySubmittedJobsList();
		DataHolder.getInstance().submittedJobsList.addAll(list);

	}

	/**
	 * 
	 * @param responseJsonString
	 * @throws ParseException
	 */
	public void parseAgentEventProcessData(final String responseData)
			throws ParseException {
		String responseString = "";
		responseString = responseData;

		final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(responseString);
		JSONArray jArray = (JSONArray) jObject.get("data");

		for (int i = 0; i < jArray.size(); i++) {
			JSONObject json_data = (JSONObject) jArray.get(i);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("label", json_data.get("label").toString());
			map.put("manual_events", json_data.get("manual_events").toString());
			map.put("file_events", json_data.get("file_events").toString());
			map.put("directory_events", json_data.get("directory_events")
					.toString());
			map.put("process_events", json_data.get("process_events")
					.toString());

			list.add(map);
		}

		DataHolder.getInstance().emptyAgentEventProcessesList();
		DataHolder.getInstance().agentEventsProcessList.addAll(list);

	}

}
