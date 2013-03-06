package com.skybot.serivce.parser.dataholder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author aabraham
 * 
 */
public class DataHolder {

	private static DataHolder dataHolder;

	/*********************** Data Arrays **********************/
	public ArrayList<HashMap<String, String>> jobsList;
	public ArrayList<HashMap<String, String>> jobHistoriesList;
	public ArrayList<HashMap<String, String>> agentsList;
	public ArrayList<HashMap<String, String>> reportsList;

	public ArrayList<HashMap<String, String>> completedJobsList;
	public ArrayList<HashMap<String, String>> submittedJobsList;
	public ArrayList<HashMap<String, String>> terminatedJobsList;
	public ArrayList<HashMap<String, String>> agentEventsProcessList;
	
	public DataHolder() {
		jobsList = new ArrayList<HashMap<String, String>>();
		jobHistoriesList = new ArrayList<HashMap<String, String>>();
		agentsList = new ArrayList<HashMap<String, String>>();
		reportsList = new ArrayList<HashMap<String, String>>();
		completedJobsList = new ArrayList<HashMap<String, String>>();
		submittedJobsList = new ArrayList<HashMap<String, String>>();
		terminatedJobsList = new ArrayList<HashMap<String, String>>();
		agentEventsProcessList = new ArrayList<HashMap<String, String>>();
	}

	public static DataHolder getInstance() {
		if (dataHolder == null) {
			dataHolder = new DataHolder();
		}

		return dataHolder;
	}

	/**
	 * Deletes all data in order to have fresh Data Holder
	 */
	public void emptyDataSet() {
		jobsList.clear();
		jobHistoriesList.clear();
		agentsList.clear();
		reportsList.clear();

		completedJobsList.clear();
		submittedJobsList.clear();
		terminatedJobsList.clear();
		agentEventsProcessList.clear();
	}

	// ------------------------- Particular Lists Cleaning: Start
	// -----------------//
	public void emptyJobsList() {
		jobsList.clear();
	}

	public void emptyJobHistoriesList() {
		jobHistoriesList.clear();
	}

	public void emptyAgentsList() {
		agentsList.clear();
	}

	public void emptyReportsList() {
		reportsList.clear();
	}

	public void emptyCompletedJobsList() {
		completedJobsList.clear();
	}

	public void emptySubmittedJobsList() {
		submittedJobsList.clear();
	}

	public void emptyTerminatedJobsList() {
		terminatedJobsList.clear();
	}

	public void emptyAgentEventProcessesList() {
		agentEventsProcessList.clear();
	}

	// ------------------------- Particular Lists Cleaning: Start
	// -----------------//

	/***************************** Setters/Getters *******************/

	/**
	 * 
	 * @return jobsList
	 */
	public ArrayList<HashMap<String, String>> getJobsList() {
		return jobsList;
	}

	/**
	 * 
	 * @param jobsList
	 */
	public void setJobsList(ArrayList<HashMap<String, String>> jobsList) {
		this.jobsList = jobsList;
	}

	/**
	 * 
	 * @return jobHistoriesList
	 */
	public ArrayList<HashMap<String, String>> getJobHistoriesList() {
		return jobHistoriesList;
	}

	/**
	 * 
	 * @param jobHistoriesList
	 */
	public void setJobHistoriesList(
			ArrayList<HashMap<String, String>> jobHistoriesList) {
		this.jobHistoriesList = jobHistoriesList;
	}

	/**
	 * 
	 * @return agentsList
	 */
	public ArrayList<HashMap<String, String>> getAgentsList() {
		return agentsList;
	}

	/**
	 * 
	 * @param agentsList
	 */
	public void setAgentsList(ArrayList<HashMap<String, String>> agentsList) {
		this.agentsList = agentsList;
	}

	/**
	 * 
	 * @return reportsList
	 */
	public ArrayList<HashMap<String, String>> getReportsList() {
		return reportsList;
	}

	/**
	 * 
	 * @param reportsList
	 */
	public void setReportsList(ArrayList<HashMap<String, String>> reportsList) {
		this.reportsList = reportsList;
	}

	/**
	 * 
	 * @return completedJobsList
	 */
	public ArrayList<HashMap<String, String>> getCompletedJobsList() {
		return completedJobsList;
	}

	/**
	 * 
	 * @param completedJobsList
	 */
	public void setCompletedJobsList(
			ArrayList<HashMap<String, String>> completedJobsList) {
		this.completedJobsList = completedJobsList;
	}

	/**
	 * 
	 * @return submittedJobsList
	 */
	public ArrayList<HashMap<String, String>> getSubmittedJobsList() {
		return submittedJobsList;
	}

	/**
	 * 
	 * @param submittedJobsList
	 */
	public void setSubmittedJobsList(
			ArrayList<HashMap<String, String>> submittedJobsList) {
		this.submittedJobsList = submittedJobsList;
	}

	/**
	 * 
	 * @return terminatedJobsList
	 */
	public ArrayList<HashMap<String, String>> getTerminatedJobsList() {
		return terminatedJobsList;
	}

	/**
	 * 
	 * @param terminatedJobsList
	 */
	public void setTerminatedJobsList(
			ArrayList<HashMap<String, String>> terminatedJobsList) {
		this.terminatedJobsList = terminatedJobsList;
	}

	/**
	 * 
	 * @return agentEventsProcessList
	 */
	public ArrayList<HashMap<String, String>> getAgentEventsProcessList() {
		return agentEventsProcessList;
	}

	/**
	 * 
	 * @param agentEventsProcessList
	 */
	public void setAgentEventsProcessList(
			ArrayList<HashMap<String, String>> agentEventsProcessList) {
		this.agentEventsProcessList = agentEventsProcessList;
	}

}
