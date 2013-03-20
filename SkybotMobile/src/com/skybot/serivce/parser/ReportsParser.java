package com.skybot.serivce.parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.skybot.serivce.parser.dataholder.DataHolder;
import com.skybot.util.Constants;

public class ReportsParser {

	/**
	 * 
	 * @param responseJsonString
	 * @throws ParseException
	 */
	public void parseData(final String responseData, final String serviceName)
			throws ParseException {

		String responseString = "";
		responseString = responseData.replace("items:", "\"items\":");
		responseString = responseString.replace("timestamp:", "\"timestamp\":");
		responseString = responseString
				.replace(
						"\"<div class='job-history-status job-history-finished'><span class='icon-job-history icon-job-history-finished'></span>Finished</div>\"",
						"\"Finished\"");

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
			// DataHolder.getInstance().reportsList.add(map);
		}

		if (serviceName.equals(Constants.MORE_JOB_HISTORIES_REPORTS)) {
			DataHolder.getInstance().reportsList.addAll(list);
		} else {
			DataHolder.getInstance().emptyReportsList();
			DataHolder.getInstance().reportsList.addAll(list);
		}

	}

}
