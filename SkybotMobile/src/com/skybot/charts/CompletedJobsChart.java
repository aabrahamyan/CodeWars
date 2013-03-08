package com.skybot.charts;

import java.util.ArrayList;
import java.util.HashMap;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

public class CompletedJobsChart {
	//Context context;
	ArrayList<HashMap<String, String>> data;
	String key = "completed_jobs";
	
	
	public GraphicalView getChart(Object context, ArrayList<HashMap<String, String>> data) {
		
		
		
		double[] y = getValue(data);
		String[] xLabels = getLabels(data);
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		
		//double[] y = {0.2,0.3,0.4,0.5,0.9,0,0.3};
		CategorySeries series = new CategorySeries("Completed Jobs");
		
		for(int i=0; i<y.length;i++) {
			series.add(y[i]);
		}
		
		/**Setting Labels For X Axis**/
		
		for(int i=0;i<xLabels.length;i++) {
	    	mRenderer.addXTextLabel(i+1,xLabels[i] );
	    }
		mRenderer.setXLabels(0);
		
		GetMaxAxis getAxis = new GetMaxAxis();
		double maxAxis = getAxis.getMax(y,"completed_jobs");
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		dataset.addSeries(series.toXYSeries());
		
		/** Single BarChart customisation**/
		renderer.setDisplayChartValues(true);
		renderer.setColor(Color.parseColor("#65BDE3"));
		
		/** Layout Settings**/
		
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
		
	    mRenderer.setPanEnabled(false, false);
	    mRenderer.setZoomEnabled(false,false); //Zoom disable
	    
	    mRenderer.setBarSpacing(0.3); // Distance between bar groups
	    mRenderer.setShowGrid(true);
	   
	    mRenderer.setChartTitle("Completed Jobs");
	    mRenderer.setChartTitleTextSize(20);
		
		mRenderer.setBarSpacing(0.5);
		mRenderer.setLabelsTextSize(15);
		mRenderer.setInScroll(true);
		 
		mRenderer.addSeriesRenderer(renderer);
		/**Distance between y axis and labels**/
		
		//mRenderer.setYLabelsAlign(Align.LEFT);
		mRenderer.setYLabelsPadding(10);
		
		/** X axis customisation settings**/

		mRenderer.setXAxisMax(8);
		mRenderer.setXAxisMin(0);
		
		/** Y axis customisation Settings**/
		
		mRenderer.setYAxisMax(maxAxis);
		mRenderer.setYAxisMin(0);
		
		mRenderer.setShowLegend(false);
		
	    GraphicalView chartView = ChartFactory.getBarChartView((Context) context, dataset, mRenderer,Type.DEFAULT);
	    Log.i("CompletedJobs", "CompletedJobs");
		return chartView;
		
	}

	public double[] getValue(ArrayList<HashMap<String, String>> data) {
		
			double[] y = {0,0,0,0,0,0,0};
			for(int i=0;i<data.size();i++) {
				y[i] =Double.parseDouble(data.get(i).get("real_value"));
				System.out.println(y[i]);
			}
			
			return y;
		}
	
	public String[] getLabels(ArrayList<HashMap<String, String>> data) {
		
		String[] x = {"","","","","","",""};
		
		for(int i=0;i<data.size();i++) {
			x[i] = data.get(i).get("label");
			System.out.println(x[i]);
		}
		
		return x;
	}
	
	
	
}
