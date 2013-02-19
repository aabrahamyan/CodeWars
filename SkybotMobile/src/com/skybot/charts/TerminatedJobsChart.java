package com.skybot.charts;
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
	
	public GraphicalView getChart(Object context) {
		
		/** Y coordinates Array for each BarChart**/
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
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		dataset.addSeries(series3.toXYSeries());
		/** --dataset--**/
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); 
		
		/** Settings for whole layout **/
		
		mRenderer.setAxesColor(Color.BLACK);
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
	    mRenderer.setPanEnabled(false, false);
	    mRenderer.setZoomEnabled(false,false); //Zoom disable
	    mRenderer.setBarSpacing(0.3); // Distance between bar groups
	    mRenderer.setShowGrid(true);
	    mRenderer.setChartTitle("Terminated Jobs");
	    mRenderer.setLabelsTextSize(20);
	    /**X axis settings**/
	    
	    mRenderer.setXAxisMax(7);
	    mRenderer.setXAxisMin(0);
	    mRenderer.setChartTitleTextSize(30);
	    mRenderer.setXLabelsColor(Color.BLACK);
	    			
	    
	    /** Adding text labels for x axis coordinates**/
	   
	    for(int i=0;i<y.length;i++) {
	    	
	    	mRenderer.addXTextLabel(i+1,"2" + "-" +i );
	    	
	    }
	    
	    mRenderer.setXLabels(0);//Removing x axis values, only text labels are visible
	    /**Y axis settings **/
	    
	    mRenderer.addYTextLabel(0.5, "0.5");
	    mRenderer.addYTextLabel(1, "1");
	    mRenderer.setYLabelsAlign(Align.RIGHT);
	    mRenderer.setYLabels(0);
	    mRenderer.setYLabelsColor(0, Color.BLACK);
	    mRenderer.setYAxisMax(1);
	    mRenderer.setYAxisMin(0);
	    mRenderer.setShowGridX(true);//Shows gridlines for Y axis
	    mRenderer.setGridColor(Color.GRAY);
	    
	    
	    /** Multiple BarcHart customization **/
	    //Cancelled
	    
	    XYSeriesRenderer renderer = new XYSeriesRenderer();	    
	    renderer.setChartValuesTextSize(20);
	    renderer.setDisplayChartValues(true);
	    renderer.setChartValuesTextAlign(Align.CENTER);
	    renderer.setChartValuesSpacing(10);
	    renderer.setColor( Color.parseColor("#65BDE3"));
	    
	    //Failed
	    
	    XYSeriesRenderer renderer2 = new XYSeriesRenderer();
	    renderer2.setChartValuesTextSize(20);
	    renderer2.setDisplayChartValues(true);
	    renderer2.setChartValuesTextAlign(Align.CENTER);
	    renderer2.setChartValuesSpacing(10);
	    renderer2.setColor(Color.parseColor("#FCCA76"));
	    
	    //Error
	    
	    XYSeriesRenderer renderer3 = new XYSeriesRenderer();
	    renderer3.setChartValuesTextSize(20);
	    renderer3.setDisplayChartValues(true);
	    renderer3.setChartValuesTextAlign(Align.LEFT);
	    renderer3.setChartValuesSpacing(10);
	    renderer3.setColor(Color.parseColor("#BADC8C"));
	    
	   /** Adding Single renderers to multiple renderer **/
	    
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.addSeriesRenderer(renderer2);
	    mRenderer.addSeriesRenderer(renderer3);	   
	    
	    mRenderer.setInScroll(true);		//Setting swipe standart animation  
	    GraphicalView chartView = ChartFactory.getBarChartView((Context) context, dataset,mRenderer, Type.DEFAULT);
		return chartView;
		
	}
		
}
