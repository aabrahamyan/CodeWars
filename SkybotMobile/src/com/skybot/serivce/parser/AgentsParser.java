package com.skybot.serivce.parser;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.skybot.serivce.parser.dataholder.DataHolder;

public class AgentsParser {

	/**
	 * 
	 * @param responseJsonString
	 * @throws ParseException
	 */
	public void parseData(final String responseData) throws ParseException {

		String responseString = "";
		responseString = responseData.replace("maxId:", "\"maxId\":");
		responseString = responseString.replace("timestamp:", "\"timestamp\":");
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

		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(responseString);

		JSONArray jArray = (JSONArray) jObject.get("items");
		for (int i = 0; i < jArray.size(); i++) {
			JSONObject json_data = (JSONObject) jArray.get(i);
			HashMap<String, String> map = new HashMap<String, String>();

			map.put("id", json_data.get("id").toString());
			map.put("status", json_data.get("status").toString());
			map.put("name", json_data.get("name").toString());
			map.put("description", json_data.get("description").toString());

			DataHolder.getInstance().agentsList.add(map);
		}

	}

}
