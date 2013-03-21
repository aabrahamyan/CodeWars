package com.skybot.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skybot.activities.AgentActivity;
import com.skybot.activities.JobsActivity;
import com.skybot.activities.R;
import com.skybot.serivce.parser.dataholder.DataHolder;
import com.skybot.util.Util;

public class AgentAdapter extends BaseAdapter {

	private AgentActivity activity;
	public ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;

	public AgentAdapter(AgentActivity a, ArrayList<HashMap<String, String>> d) {
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
		ImageView restartBtn = (ImageView) vi.findViewById(R.id.restart_agent);
		try {
			if (data != null && !data.isEmpty()) {

				m = data.get(position);

				if (m.get("status").toString().equals("Active")) {
					image.setImageResource(R.drawable.blank_badge_green);
				} else if (m.get("status").toString().equals("Stopped")) {
					image.setImageResource(R.drawable.blank_badge_orange);
				}

				else if (m.get("status").toString().equals("Failed")) {
					image.setImageResource(R.drawable.blank_badge_red);
				}

				name.setText(m.get("name").toString());
				id.setText("ID: " + m.get("id").toString());
				description.setText("Description: "
						+ m.get("description").toString());

				String runid = m.get("id");
				restartBtn.setTag(runid);

			}

			name.setText(m.get("name").toString());
			id.setText("ID: " + m.get("id").toString());
			description.setText("Description: "
					+ m.get("description").toString());

			restartBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Util.showOrHideActivityIndicator(activity.getParent(), 0,
							"Please wait...");
					Toast.makeText(activity, "Restarting agent...",
							Toast.LENGTH_LONG).show();
					String id = v.getTag().toString();					
					DataHolder.getInstance().emptyAgentsList();
					activity.restartAgent(v, id);
				}
			});

		} catch (Exception e) {
			Log.e("Exception occured", e.toString());

		}
		return vi;
	}

}
