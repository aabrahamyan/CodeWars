package com.skybot.serivce.parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.skybot.serivce.parser.dataholder.DataHolder;
import com.skybot.util.Constants;

public class JobsParser {

	/**
	 * 
	 * @param responseJsonString
	 * @throws ParseException
	 */
	public void parseData(final String responseData, final String serviceName)
			throws ParseException {
		String responseString = "";
		responseString = responseData.replace("maxId:", "\"maxId\":");
		responseString = responseString.replace("timestamp:", "\"timestamp\":");
		responseString = responseString.replace("growler_message:",
				"\"growler_message\":");
		responseString = responseString.replace("items:", "\"items\":");
		responseString = responseString.replace("<span>", "");
		responseString = responseString.replace("</span>", "");

		JSONParser jParser = new JSONParser();
		JSONObject jObject;

		jObject = (JSONObject) jParser.parse(responseString);

		final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		JSONArray jArray = (JSONArray) jObject.get("items");
		for (int i = 0; i < jArray.size(); i++) {
			JSONObject json_data = (JSONObject) jArray.get(i);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", json_data.get("name").toString());
			map.put("runid", json_data.get("id").toString());
			map.put("agent", json_data.get("agent").toString());
			map.put("description", json_data.get("description").toString());
			map.put("hold_status", json_data.get("hold_status").toString());
			map.put("job_type", json_data.get("job_type").toString());
			map.put("agent_name", json_data.get("agent_name").toString());
			map.put("target_type", json_data.get("target_type").toString());
			map.put("schedule_type", json_data.get("schedule_type").toString());
			map.put("calendar", json_data.get("calendar").toString());
			map.put("day_count_interval", json_data.get("day_count_interval")
					.toString());
			map.put("timed_interval_minutes",
					json_data.get("timed_interval_minutes").toString());
			map.put("timed_interval_display_preference",
					json_data.get("timed_interval_display_preference")
							.toString());
			map.put("day_type", json_data.get("day_type").toString());
			map.put("timezone_type", json_data.get("timezone_type").toString());
			map.put("update_counter", json_data.get("update_counter")
					.toString());
			map.put("default_priority", json_data.get("default_priority")
					.toString());
			map.put("dependent_via_job_suite",
					json_data.get("dependent_via_job_suite").toString());
			map.put("prerequisite_members",
					json_data.get("prerequisite_members").toString());
			map.put("schedule_type", json_data.get("schedule_type").toString());
			map.put("job_type", json_data.get("job_type").toString());
			map.put("is_reactive", json_data.get("is_reactive").toString());

			list.add(map);

		}

		if (serviceName.equals(Constants.MORE_JOBS)) {			
			DataHolder.getInstance().jobsList.addAll(list);
		} else {
			DataHolder.getInstance().emptyJobsList();
			DataHolder.getInstance().jobsList.addAll(list);
		}
	}
}
