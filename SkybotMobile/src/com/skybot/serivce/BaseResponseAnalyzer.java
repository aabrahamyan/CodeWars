package com.skybot.serivce;

import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.util.Log;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.serivce.parser.AgentsParser;
import com.skybot.serivce.parser.BaseDashboardParser;
import com.skybot.serivce.parser.JobHistoriesParser;
import com.skybot.serivce.parser.JobsParser;
import com.skybot.serivce.parser.ReportsParser;
import com.skybot.serivce.parser.dataholder.DataHolder;
import com.skybot.util.Constants;
import com.skybot.util.Util;
import com.skybot.util.ViewTracker;

/**
 * Synchronized instance which evaluates response data and passes to the
 * structural parsing
 * 
 * @author aabraham
 * 
 */
public class BaseResponseAnalyzer {

	/**
	 * Handles responses and chains parsing responsibilities
	 * 
	 * @param serviceName
	 * @param map
	 * @param responseData
	 * @throws ParseException
	 */
	public static synchronized void analyze(final String serviceName,
			final String urlWithParams, final String responseData) {

		if (serviceName.equals(Constants.LOGIN_SERVICE)) {

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();
		}
		/******************************************* JOB **********************************************/
		else if (serviceName.equals(Constants.JOB_SERVICE_URL)
				|| serviceName.equals(Constants.MORE_JOBS)) {
			try {
				/************** Parser: Start ******************/
				JobsParser jobsParser = new JobsParser();
				jobsParser.parseData(responseData, serviceName);
				/************** Parser: End ********************/

				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();
				Activity jobsActivity = (Activity) ViewTracker.getInstance()
						.getCurrentContext();

				jobsActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing(
								DataHolder.getInstance().jobsList, serviceName);
					}
				});

			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

		}
		/**************************************** RUN JOB *******************************************/
		else if (serviceName.equals(Constants.COMMAND_URL)) {
			try {
				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();
				Activity jobsActivity = (Activity) ViewTracker.getInstance()
						.getCurrentContext();

				jobsActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing();
					}
				});

			} catch (Exception e) {
				Log.e("RUN JOB", "Error running job" + e.toString());
			}

		}
		/******************************************* JOB HISTORY **********************************************/
		else if (serviceName.equals(Constants.JOBHISTORY_SERVICE_URL)
				|| serviceName.equals(Constants.MORE_JOB_HISTORIES)) {

			try {
				/************** Parser: Start ******************/
				JobHistoriesParser jhParser = new JobHistoriesParser();
				jhParser.parseData(responseData, serviceName);
				/************** Parser: End ******************/

				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();

				Activity jobHistoryActivity = (Activity) ViewTracker
						.getInstance().getCurrentContext();

				jobHistoryActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {

						del.didFinishRequestProcessing(
								DataHolder.getInstance().jobHistoriesList,
								serviceName);
					}
				});

			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

		}
		/******************************************* AGENT **********************************************/
		else if (serviceName.equals(Constants.AGENT_SERVICE_URL)) {

			try {
				/************** Parser: Start ******************/
				AgentsParser aParser = new AgentsParser();
				aParser.parseData(responseData);
				/************** Parser: End ******************/

				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();
				Activity jobsActivity = (Activity) ViewTracker.getInstance()
						.getCurrentContext();

				jobsActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing(
								DataHolder.getInstance().agentsList, "");
					}
				});

			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

		}

		/******************************************* JOB HISTORY DATA REPORT **********************************************/
		else if (serviceName.equals(Constants.JOBHISTORYREPORT_SERVICE_URL)
				|| serviceName.equals(Constants.MORE_JOB_HISTORIES_REPORTS)) {

			try {
				/************** Parser: Start ******************/
				ReportsParser rParser = new ReportsParser();
				rParser.parseData(responseData, serviceName);
				/************** Parser: End ********************/

				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();
				Activity jobsActivity = (Activity) ViewTracker.getInstance()
						.getCurrentContext();

				jobsActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing(
								DataHolder.getInstance().reportsList, "");
					}
				});

			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

		}

		else if (serviceName.equals(Constants.COMPLETED_JOBS_ID)) {

			try {

				/************** Parser: Start ******************/
				BaseDashboardParser bdParser = new BaseDashboardParser();
				bdParser.parseCompletedJobsData(responseData);
				/************** Parser: End ********************/

				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();

				Activity dashboardActivity = (Activity) ViewTracker
						.getInstance().getCurrentContext();

				dashboardActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						synchronized (BaseResponseAnalyzer.class) {
							del.didFinishRequestProcessing(
									DataHolder.getInstance().completedJobsList,
									"completed_jobs");
						}
					}
				});

			} catch (ParseException e) {
				Log.e("Chart Parser error", "Error parsing Chart Data");

				e.printStackTrace();
			}

		}

		else if (serviceName.equals(Constants.TERMINATED_JOBS_ID)) {

			try {

				/************** Parser: Start ******************/
				BaseDashboardParser bdParser = new BaseDashboardParser();
				bdParser.parseTerminatedJobsData(responseData);
				/************** Parser: End ********************/

				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();

				Activity dashboardActivity = (Activity) ViewTracker
						.getInstance().getCurrentContext();

				dashboardActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing(
								DataHolder.getInstance().terminatedJobsList,
								"terminated_jobs");
					}
				});

			} catch (ParseException e) {

				Log.e("Chart Parser error", "Error parsing Chart Data");

				e.printStackTrace();
			}

		} else if (serviceName.equals(Constants.SUBMITTED_JOBS_ID)) {

			try {

				/************** Parser: Start ******************/
				BaseDashboardParser bdParser = new BaseDashboardParser();
				bdParser.parseSubmittedJobsData(responseData);
				/************** Parser: End ********************/

				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();

				Activity dashboardActivity = (Activity) ViewTracker
						.getInstance().getCurrentContext();

				dashboardActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing(
								DataHolder.getInstance().submittedJobsList,
								"submitted_jobs");
					}
				});
			} catch (ParseException e) {
				Log.e("Chart Parser error", "Error parsing Chart Data");
				e.printStackTrace();
			}
		}

		else if (serviceName.equals(Constants.AGENT_EVENT_PROCESSED_ID)) {

			try {
				/************** Parser: Start ******************/
				BaseDashboardParser bdParser = new BaseDashboardParser();
				bdParser.parseAgentEventProcessData(responseData);
				/************** Parser: End ********************/

				final ActionDelegate del = (ActionDelegate) ViewTracker
						.getInstance().getCurrentContext();

				Activity dashboardActivity = (Activity) ViewTracker
						.getInstance().getCurrentContext();

				dashboardActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						del.didFinishRequestProcessing(
								DataHolder.getInstance().agentEventsProcessList,
								"agent_event_processed_jobs");
					}
				});
			} catch (ParseException e) {

			}
		} else if (serviceName.equals(Constants.PUSH_NOTIFICATION_SERVICE_URL)) {
			System.err.println("RESPONSE DATA ================== "
					+ responseData);
		} else if (serviceName.equals(Constants.SIGN_OUT)) {
			Util.writeString(ViewTracker.getInstance().getCurrentContext(),
					Util.IS_LOGGED_IN, "NO");
		}

	}
}