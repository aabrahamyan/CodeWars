package com.skybot.adapters;

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


public class AgentEventProcessedChart {
	
	public GraphicalView getChart(Object context) {
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); 
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		
		double[] y = {0.7,0.3,0.4,0.4,0.5,1};
		CategorySeries series = new CategorySeries("Manual");	
		for(int i=0;i<y.length;i++) {
			
			series.add("Bar " + (i+1), y[i]);
			
		}
		
		double[] y1 = {0.68,0.25,0.35,0.35,0.45,0.95};
		CategorySeries series2 = new CategorySeries("File");
		for(int i=0;i<y1.length;i++) {
			
			series2.add(y1[i]);
			
		}
		
		double[] y3 = {0.6,0.2,0.3,0.3,0.4,0.9};
		CategorySeries series3 = new CategorySeries("Directory");
		for(int i = 0; i <y3.length;i++) {
			series3.add(y3[i]);
		}
		
		double[] y4 = {0.55,0.15,0.25,0.25,0.35,0.85};
		CategorySeries series4 = new CategorySeries("Process");
		for(int i=0;i<y4.length;i++) {
			series4.add(y4[i]);
		}
		
		/** Adding Series to dataset**/
		
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		dataset.addSeries(series3.toXYSeries());
		dataset.addSeries(series4.toXYSeries());
		
		/** Adding text labels for x axis coordinates**/
	    for(int i=0;i<y.length;i++) {
	    	
	    	mRenderer.addXTextLabel(i+1,"2" + "-" +i );
	    	
	    }
	    mRenderer.setXLabels(0);//Removing x axis values, only text labels are visible
		
		/** Settings for whole layout **/
		
		//mRenderer.setAxesColor(Color.BLACK);
		mRenderer.setApplyBackgroundColor(true);
		
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
		
		mRenderer.setMargins(new int[] {10,20,30,40});
		
	    mRenderer.setPanEnabled(false, false);
	    mRenderer.setZoomEnabled(false,false); //Zoom disable
	    
	    mRenderer.setBarSpacing(0.3); // Distance between bar groups
	    mRenderer.setShowGrid(true);
	    
	    mRenderer.setChartTitle("Agent Events Processed");
	    mRenderer.setLabelsTextSize(20); 
	    mRenderer.setYLabelsPadding(-20);
	    
	    mRenderer.setLegendHeight(50);
	    
	    /**X axis settings**/
	    mRenderer.setXAxisMax(7);
	    mRenderer.setXAxisMin(0);
	    mRenderer.setChartTitleTextSize(35);
	   // mRenderer.setXLabelsColor(Color.BLACK);
	    			
	    /**Y axis settings **/
//	    mRenderer.addYTextLabel(0.5, "0.5");
//	    mRenderer.addYTextLabel(1, "16546454645456456");
//	    mRenderer.setYLabels(0);
// 		mRenderer.setYLabelsColor(0, Color.GRAY);
//	    mRenderer.setYAxisMax(1.5);
//	    mRenderer.setYAxisMin(0);
	    mRenderer.setShowGridX(true);//Shows gridlines for Y axis
	    mRenderer.setLegendTextSize(20);
//	    mRenderer.setGridColor(Color.GRAY);
	    
	    mRenderer.setYLabelsPadding(-20);
	    
	    /** Multiple BarcHart customization **/
	    //Cancelled
	    
	    XYSeriesRenderer renderer = new XYSeriesRenderer();	    
	    renderer.setChartValuesTextSize(20);
//	    renderer.setDisplayChartValues(true);
//	    renderer.setChartValuesTextAlign(Align.CENTER);
//	    renderer.setChartValuesSpacing(10);
	    renderer.setColor( Color.parseColor("#65BDE3"));
	    
	    XYSeriesRenderer renderer2 = new XYSeriesRenderer();
	    renderer2.setChartValuesTextSize(20);
//	    renderer2.setDisplayChartValues(true);
//	    renderer2.setChartValuesTextAlign(Align.CENTER);
//	    renderer2.setChartValuesSpacing(10);
	    renderer2.setColor(Color.parseColor("#FCCA76"));
	    
	    XYSeriesRenderer renderer3 = new XYSeriesRenderer();
	    renderer3.setChartValuesTextSize(20);
//	    renderer3.setDisplayChartValues(true);
//	    renderer3.setChartValuesTextAlign(Align.LEFT);
//	    renderer3.setChartValuesSpacing(10);
	    renderer3.setColor(Color.parseColor("#BADC8C"));
	    
	    XYSeriesRenderer renderer4 = new XYSeriesRenderer();
	    renderer4.setChartValuesTextSize(20);
//	    renderer4.setDisplayChartValues(true);
//	    renderer4.setChartValuesTextAlign(Align.LEFT);
//	    renderer4.setChartValuesSpacing(10);
	    renderer4.setColor(Color.parseColor("#C0C0C0"));
	    
	    /**Legend Generator**/
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.addSeriesRenderer(renderer2);
	    mRenderer.addSeriesRenderer(renderer3);
	    mRenderer.addSeriesRenderer(renderer4);
	    
	    mRenderer.setInScroll(true); //Disable fetch animation
	    
	    GraphicalView chartView = ChartFactory.getBarChartView((Context) context, dataset,mRenderer, Type.STACKED);
		return chartView;
		
	}
	
}
