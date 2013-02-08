package com.skybot.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Dediacted for scrolling Chart viewes from one to another
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class ScrollItemsFragment extends Fragment {

	int mCurrentPage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/** Getting the arguments to the Bundle object */
		Bundle data = getArguments();

		/** Getting integer data of the key current_page from the bundle */
		mCurrentPage = data.getInt("current_page", 0);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.scrolling_fragment_layout,
				container, false);
		TextView tv = (TextView) v.findViewById(R.id.tv);
		tv.setText("You are viewing the page #" + mCurrentPage + "\n\n"
				+ "Swipe Horizontally left / right");
		return tv;

	}

}
