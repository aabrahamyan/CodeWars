package com.skybot.serivce.parser;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.util.Log;

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

		for (int i = 0; i < jArray.size(); i++) {
			JSONObject json_data = (JSONObject) jArray.get(i);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("label", json_data.get("label").toString());
			map.put("value", json_data.get("value").toString());
			map.put("real_value", json_data.get("real_value").toString());
			DataHolder.getInstance().completedJobsList.add(map);
		}
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

			DataHolder.getInstance().terminatedJobsList.add(map);
		}
	}

	/**
	 * 
	 * @param responseJsonString
	 * @throws ParseException 
	 */
	public void parseSubmittedJobsData(final String responseData) throws ParseException {
		String responseString = "";
		responseString = responseData;

		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(responseString);
		JSONArray jArray = (JSONArray) jObject.get("data"); 

		for (int i = 0; i < jArray.size(); i++) {
			JSONObject json_data = (JSONObject) jArray.get(i);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("label", json_data.get("label").toString());
			map.put("value", json_data.get("value").toString());
			map.put("real_value", json_data.get("real_value").toString());
			DataHolder.getInstance().submittedJobsList.add(map);
		}

	}

	/**
	 * 
	 * @param responseJsonString
	 */
	public void parseAgentEventProcessData(final String responseJsonString) {

	}

}
