package com.skybot.util;

public class Constants {

	//---------------------------- General HTTP Request Information ----------------//
	public static String SERVER_URL = "http://192.168.0.5:8008/skybot-scheduler"; //"http://192.168.0.152:3000";
	public static String API_KEY = "";	
	public static final String EQUAL = "=";
	public static final String FIRST_PARAM_SEPARATOR = "?";
	public static final String PARAMETER_SEPARATOR = "&";
	public static final String RIGHT_SLASH = "/"; 
	
	//---------------------------- Services Names & Params ----------------//
	public static final String LOGIN_SERVICE = "user_session";
	public static final String AUTH_TOKEN = "authenticity_token";
	public static final String USERNAME = "user_session[username]";
	public static final String PASSWORD = "user_session[password]";
	public static final String COMMIT = "commit";
	
	public static final String JOB_SERVICE_URL = "skybot-scheduler/jobs.json";
	public static final String DATE = "_dc";
	public static final String RESULTS = "results";
	public static final String SORT = "sort";
	public static final String DIRECTION = "dir";
	public static final String TAG = "tag";
	public static final String TAG_MATCH_ANY = "tag_match_any";
	public static final String START = "start";
	public static final String LIMIT = "limit";
	
	//---------------------------- Views ----------------------------------//
	public static final String LOGIN_VIEW = "LoginView";
}

