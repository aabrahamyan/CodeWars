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


public class TerminatedJobsChart {
	
	public GraphicalView getChart(Object context, ArrayList<HashMap<String, String>> data) {
		
		for(int i=0;i<data.size();i++) {
			System.out.println(data.get(i));
		}
		
		//double[] y = getArray(data, "real_canceled_value");
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); 
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		
		double[] y = { 0.6,0.8,0.1,0.9,0.7,0.7 };
		CategorySeries series = new CategorySeries("Canceled");	
		for(int i=0;i<y.length;i++) {
			
			series.add("Bar " + (i+1), y[i]);
			
		}
		
		double[] y1 = {0,0,0,0,0.7,0.7};
		CategorySeries series2 = new CategorySeries("Failed");
		for(int i=0;i<y1.length;i++) {
			
			series2.add(y1[i]);
			
		}
		
		double[] y3 = {0,0.2,0.3,0.7,0.8,0.5};
		CategorySeries series3 = new CategorySeries("Error");
		for(int i = 0; i <y3.length;i++) {
			series3.add(y3[i]);
		}
		
		/** Adding Series to dataset**/
		
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		dataset.addSeries(series3.toXYSeries());
		
		
		
		/** Adding text labels for x axis coordinates**/
	    for(int i=0;i<y.length;i++) {
	    	
	    	mRenderer.addXTextLabel(i+1,"2" + "-" +i );
	    	
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
	    mRenderer.setLabelsTextSize(15); 
	    
	    mRenderer.setLegendHeight(50);
	    
	    /**X axis settings**/
	    
	    mRenderer.setXAxisMax(7);
	    mRenderer.setXAxisMin(0);
	    mRenderer.setChartTitleTextSize(20);
	    			
	    /**Y axis settings **/
	    
	    mRenderer.setYAxisMax(1.5);
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
	
	public double[] getArray(ArrayList<HashMap<String, String>> data, String key) {
		double[] valueArray = {0,0,0,0,0,0,0};
		
		for(int i=0;i<data.size();i++) {
			valueArray[i] = Double.parseDouble(data.get(i).get(key)) ;
		}
		return valueArray;
	}
		
}
