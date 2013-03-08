package com.skybot.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skybot.activities.R;

public class AgentAdapter extends BaseAdapter {

	private Activity activity;
	public ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;

	public AgentAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	HashMap<String, String> m = new HashMap<String, String>();

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.agent_layout, null);

		TextView name = (TextView) vi.findViewById(R.id.title);
		TextView description = (TextView) vi.findViewById(R.id.description);
		TextView id = (TextView) vi.findViewById(R.id.agent_id);
		ImageView image = (ImageView) vi.findViewById(R.id.list_image);
		try {
			if (data != null && !data.isEmpty()) {

				m = data.get(position);

				if (m.get("status").toString().equals("Active")) {
					image.setImageResource(R.drawable.blank_badge_green);
				} /*
				 * else if (m.get("status").toString().equals("S")) {
				 * image.setImageResource(R.drawable.blank_badge_orange); }
				 */

				else if (m.get("status").toString().equals("Failed")) {
					image.setImageResource(R.drawable.blank_badge_red);
				}

				name.setText(m.get("name").toString());
				id.setText("ID: " + m.get("id").toString());
				description.setText("Description: "
						+ m.get("description").toString());

			}

			name.setText(m.get("name").toString());
			id.setText("ID: " + m.get("id").toString());
			description.setText("Description: "
					+ m.get("description").toString());

		} catch (Exception e) {
			Log.e("Exception occured", e.toString());

		}
		return vi;
	}

}
