package com.skybot.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.skybot.activities.TestFragment;

public class TestFragmentAdapter extends FragmentPagerAdapter {

	private int mCount = 7;

	public TestFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		TestFragment myFragment = new TestFragment();
		Bundle data = new Bundle();
		data.putInt("current_page", position);
		myFragment.setArguments(data);
		return myFragment;
	}

	@Override
	public int getCount() {
		return mCount;
	}

}