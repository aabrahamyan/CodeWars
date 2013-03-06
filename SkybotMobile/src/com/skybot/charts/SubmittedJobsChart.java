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
import android.graphics.Paint.Align;
import android.util.Log;

public class SubmittedJobsChart {
	
	public GraphicalView getChart(Object context, ArrayList<HashMap<String, String>> data) {
		
		if(data == null) {
			Log.e("Null", "Data is null");
		}
		else {
			Log.e("Null", "Data exist");
		}
		
		XYMultipleSeriesDataset dataset= new XYMultipleSeriesDataset();
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		GetMaxAxis getMax = new GetMaxAxis();
		
		TimeSeries series = new TimeSeries("Jobs Submitted");
		
		int[] x = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		double[] values = getArray(data, "real_value");
		String[] xLabels = getLabels(data);
		
		double maxAxis = getMax.getMax(values);
		//double[] values = {0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,6};
		
		for(int i=0; i<values.length;i++) {
			series.add(x[i],values[i]);
		}
		
		for(int i=0;i<values.length;i++) {
			
		    	mRenderer.addXTextLabel(i+1,xLabels[i] );
		}
		
		mRenderer.setXLabelsAngle(-30);
		/**   Line Customization   **/
		
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setColor(Color.parseColor("#65BDE3"));
		renderer.setFillPoints(true);
		renderer.setLineWidth(5);
		
		/**Layout Customisation**/
		
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
		mRenderer.setPointSize(10);
		mRenderer.setPanEnabled(false);
		mRenderer.setZoomEnabled(false);
		mRenderer.setShowGridX(true);
		mRenderer.setShowLegend(false); //Disable legend in Chart
		mRenderer.setLabelsTextSize(15);
		mRenderer.setChartTitle("Submitted Jobs");
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setXLabels(0);
		/**Axis Value Limits**/
		mRenderer.setXAxisMin(0);
		mRenderer.setXAxisMax(17);
		mRenderer.setYAxisMax(4.5); //y max +1 Important
		
		mRenderer.setYLabelsAlign(Align.RIGHT);
		/** Distance between Y axis and labels**/
		
		dataset.addSeries(series);
		
		/** Layout Customisation**/
		mRenderer.setInScroll(true); //Disable fetch animation
		
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
