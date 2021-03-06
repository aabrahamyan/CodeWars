package com.skybot.connection.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.skybot.util.ViewTracker;

/**
 * This class is responsible to provide network status and connection type.
 * 
 * @author Armen Abrahamyan
 * 
 */
public class NetworkReachability {

	/**
	 * Gets network status
	 * 
	 * @return
	 */
	public static boolean isReachable() {

		Context current = ViewTracker.getInstance().getCurrentContext();
		ConnectivityManager connectivity = (ConnectivityManager) current
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivity.getActiveNetworkInfo();
		if (info == null || !connectivity.getBackgroundDataSetting()) {
			return false;
		}

		int netType = info.getType();
		if (netType == ConnectivityManager.TYPE_WIFI
				|| netType == ConnectivityManager.TYPE_MOBILE) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets network type
	 * 
	 * @return
	 */
	public static int getNetworkType() {

		Context current = ViewTracker.getInstance().getCurrentContext();
		ConnectivityManager connectivity = (ConnectivityManager) current
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivity.getActiveNetworkInfo();
		if (info == null || !connectivity.getBackgroundDataSetting()) {
			return -1;
		}

		int netType = info.getType();
		return netType;
	}

}
