package com.skybot.util;

public class Constants {

	// ---------------------------- General HTTP Request Information
	// ----------------//
	public static String SERVER_URL = "http://192.168.0.5:8008/skybot-scheduler"; // "http://192.168.0.152:3000";
	public static String API_KEY = "";
	public static final String EQUAL = "=";
	public static final String FIRST_PARAM_SEPARATOR = "?";
	public static final String PARAMETER_SEPARATOR = "&";
	public static final String RIGHT_SLASH = "/";

	// ---------------------------- Services Names & Params ----------------//

	public static final String LOGIN_SERVICE = "user_session";
	public static final String JOB_SERVICE = "jobs";
	public static final String AUTH_TOKEN = "authenticity_token";
	public static final String USERNAME = "user_session[username]";
	public static final String PASSWORD = "user_session[password]";

	public static final String DASHBOARD_SERVICE = "dashboards";

	public static final String COMMIT = "commit";
	public static final String ADMIN = "admin";
	public static final String JOBHISTORYREPORT_SERVICE_URL = "user_files.json";
	public static final String JOBHISTORY_SERVICE_URL = "job_histories.json";
	public static final String AGENT_SERVICE_URL = "agents.json";
	public static final String AGENT_SERVICE = "agents";
	public static final String JOB_SERVICE_URL = "jobs.json";
	public static final String DASHBOARD_SERVICE_URL = "show.json";
	public static final String DATE = "_dc";
	public static final String USERID="user_id";
	public static final String RESULTS = "results";
	public static final String SORT = "sort";
	public static final String DIRECTION = "dir";
	public static final String TAG = "tag";
	public static final String TAG_MATCH_ANY = "tag_match_any";
	public static final String START = "start";
	public static final String LIMIT = "limit";
	public static final String LIST = "list";
	public static final String DATAFILTERFIELD = "dateFilter[0][field]";
	public static final String DATAFILTERDATACOMPARASION = "dateFilter[0][data][comparison]";
	public static final String DATAFILTERDATATYPE = "dateFilter[0][data][type]";
	public static final String DATAFILTERVALUE = "dateFilter[0][data][value]";
	public static final String EXCLUDETIMEDINTERVAL = "exclude_timed_interval";



	
	//---------------------------- Run Job Params --------------------------//	

	public static final String JOB_DETAILS_URL = "jobs/1000/edit.json";
	public static final String COMMAND_URL = "commands.json";

	// ---------------------------- Run Job Params --------------------------//

	public static final String RUN_JOB_URL = "commands.json";
	public static final String CS_ID = "command_set_id";
	public static final String CS_TYPE = "command_set_type";
	public static final String JOB_ID = "job_id";
	
	public static final String HOLD_JOB = "hold_job";
	public static final String RELEASE_JOB = "release_job";

	// ---------------------------- Views ----------------------------------//

	public static final String DO_WHAT = "do_what";
	public static final String DO_NOW = "donow";

	// ---------------------------- Views ----------------------------------//

	public static final String LOGIN_VIEW = "LoginView";
	public static final String JOBS_VIEW = "JobsView";
	public static final String DASHBOARD_VIEW = "DashboardView";
	public static final String JOBSHISTORY_VIEW = "JobsHistoryView";
	public static final String AGENT_VIEW = "AgentView";
	public static final String JOBHISTORYREPORT_VIEW = "JobHistoryReportView";

	// ---------------- Chart ids ------------------------------------------//
	public static final String COMPLETED_JOBS_ID = "4";
	public static final String TERMINATED_JOBS_ID = "5";
	public static final String SUBMITTED_JOBS_ID = "1";
	public static final String AGENT_EVENT_PROCESSED_ID = "10";
}
