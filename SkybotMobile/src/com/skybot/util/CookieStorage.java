package com.skybot.util;

import java.util.ArrayList;

/**
 * Cookie Storage class. Name says it all.
 * @author aabraham
 *
 */
public class CookieStorage {
	private ArrayList<Object> arrayList;

	private static CookieStorage instance;

	private CookieStorage() {
		arrayList = new ArrayList<Object>();
	}

	public static CookieStorage getInstance() {
		if (instance == null) {
			instance = new CookieStorage();
		}
		return instance;
	}

	public ArrayList<Object> getArrayList() {
		return arrayList;
	}

	@Override
	public String toString() {
		return getArrayList().toString();
	}
}
