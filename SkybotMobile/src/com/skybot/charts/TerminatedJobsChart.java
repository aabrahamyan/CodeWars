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
import android.graphics.Paint.Align;
import android.util.Log;


public class TerminatedJobsChart {
	
	public GraphicalView getChart(Object context, ArrayList<HashMap<String, String>> data) {
		
		String[] xLabel = getLabels(data);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		
		double[] y1 = getValue(data, "real_canceled_value");
		
		CategorySeries series = new CategorySeries("Canceled");	
		for(int i=0;i<y1.length;i++) {
			
			series.add("Bar " + (i+1), y1[i]);
			
		}
		
		double[] y2 = getValue(data, "real_failed_value");
		CategorySeries series2 = new CategorySeries("Failed");
		for(int i=0;i<y2.length;i++) {
			
			series2.add(y2[i]);
			
		}
		
		double[] y3 = getValue(data, "real_error_value");
		CategorySeries series3 = new CategorySeries("Error");
		
		for(int i = 0; i <y3.length;i++) {
			series3.add(y3[i]);
		}
		
		GetMaxAxis getMax = new GetMaxAxis();
		
		
	    double maxAxis = getMax.getMax(y1, y2, y3);
		/** Adding Series to dataset**/
		
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		dataset.addSeries(series3.toXYSeries());
		
		/** Adding text labels for x axis coordinates**/
	    for(int i=0;i<y1.length;i++) {
	    	
	    	mRenderer.addXTextLabel(i+1,xLabel[i]);
	    	
	    }
	    mRenderer.setXLabels(0);//Removing x axis values, only text labels are visible
		
		/** Settings for whole layout **/
		
		mRenderer.setApplyBackgroundColor(true);
		
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
		
	    mRenderer.setPanEnabled(false, false);
	    mRenderer.setZoomEnabled(false,false); //Zoom disable
	    
	    mRenderer.setBarSpacing(0.3); // Distance between bar groups
	    mRenderer.setShowGrid(true);
	    
	    mRenderer.setChartTitle("Terminated Jobs");
	    mRenderer.setLabelsTextSize(20); 
	    
	    mRenderer.setLegendHeight(50);
	    
	    //mRenderer.setMargins(new int[] {0,10,10,0});
	    /**X axis settings**/
	    
	    mRenderer.setXAxisMax(8);
	    mRenderer.setXAxisMin(0);
	    mRenderer.setChartTitleTextSize(20);
	    			
	    /**Y axis settings **/
	    mRenderer.setYLabelsPadding(10);
	    mRenderer.setYAxisMax(maxAxis);
	    mRenderer.setYAxisMin(0);
	    mRenderer.setShowGridX(true);//Shows gridlines for Y axis
	    mRenderer.setLegendTextSize(20);	
	    mRenderer.setYLabelsAlign(Align.RIGHT);
	    //mRenderer.setYLabelsPadding(-20);
	    
	    /** Multiple BarcHart customization **/
	    //Cancelled
	    
	    XYSeriesRenderer renderer = new XYSeriesRenderer();	    
	    renderer.setColor( Color.parseColor("#65BDE3"));
	 
	    XYSeriesRenderer renderer2 = new XYSeriesRenderer();
	    renderer2.setColor(Color.parseColor("#FCCA76"));
	    
	    XYSeriesRenderer renderer3 = new XYSeriesRenderer();
	    renderer3.setColor(Color.parseColor("#BADC8C"));
	    
	   /** Adding Single renderers to multiple renderer **/
	    
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.addSeriesRenderer(renderer2);
	    mRenderer.addSeriesRenderer(renderer3);
	    
	    mRenderer.setInScroll(true);		//Setting swipe standart animation  
	    GraphicalView chartView = ChartFactory.getBarChartView((Context) context, dataset,mRenderer, Type.DEFAULT);
		return chartView;
		
	}
	
	public double[] getValue(ArrayList<HashMap<String, String>> data, String key) {
		double[] valueArray = {0,0,0,0,0,0,0};
		
		for(int i=0;i<data.size();i++) {
			valueArray[i] = Double.parseDouble(data.get(i).get(key));
		}
		return valueArray;
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
