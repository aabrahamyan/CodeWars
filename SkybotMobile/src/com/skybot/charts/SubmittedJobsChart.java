package com.skybot.charts;

import java.util.ArrayList;
import java.util.HashMap;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

public class SubmittedJobsChart {
	String key = "terminated_jobs";
	
	public GraphicalView getChart(Object context, ArrayList<HashMap<String, String>> data) {
		
		XYMultipleSeriesDataset dataset= new XYMultipleSeriesDataset();
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		ChartSettings chartSettings = new ChartSettings();
		GetMaxAxis getMax = new GetMaxAxis();
		
		double[] values = getArray(data, "real_value");
		double maxAxis = getMax.getMax(values,1);
		String[] xLabels = getLabels(data);
		
		TimeSeries series = new TimeSeries("Jobs Submitted");
		
		chartSettings.setLineChartSettings(mRenderer, renderer, maxAxis);
		
		chartSettings.addLineChartSeries(series, values);
		
		chartSettings.addLineChartXLabels(mRenderer, values, xLabels); 
		
		//mRenderer.setMargins(new int[] { 40, 30, 0, 0 });//
		
		mRenderer.setChartTitle("Submitted Jobs");
		
		dataset.addSeries(series);
		mRenderer.addSeriesRenderer(renderer);
		GraphicalView chartView = ChartFactory.getLineChartView((Context) context, dataset, mRenderer);
		return chartView;
		
	}
	
	public double[] getArray(ArrayList<HashMap<String, String>> data, String key) {
		double[] valueArray = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		for(int i=0;i<data.size();i++) {
			valueArray[i] = Double.parseDouble(data.get(i).get(key)) ;
		}
		return valueArray;
	}
	
	public String[] getLabels(ArrayList<HashMap<String, String>> data) {
		
		String[] x = {"","","","","","","","","","","","","","","",""};
		
		for(int i=0;i<data.size();i++) {
			x[i] = data.get(i).get("label");
			System.out.println(x[i]);
		}
		
		return x;
	}
}
