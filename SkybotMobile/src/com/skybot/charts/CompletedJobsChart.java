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

import com.skybot.activities.DashboardActivity;

import android.content.Context;
import android.graphics.Color;

public class CompletedJobsChart {
	// Context context;
	ArrayList<HashMap<String, String>> data;
	String key = "completed_jobs";

	public GraphicalView getChart(Object context,
			ArrayList<HashMap<String, String>> data) {
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		ChartSettings chartSettings = new ChartSettings();
		GetMaxAxis getMax = new GetMaxAxis();
		
		double[] y = getValue(data);
		double maxAxis = getMax.getMax(y, 1);
		String[] xLabels = getLabels(data);
		
		
		CategorySeries series = new CategorySeries("Completed Jobs");
		
		chartSettings.setBarChartSetting(mRenderer, maxAxis);
		
		chartSettings.addBarChartSeries(dataset, series, y);
		
		chartSettings.addBarChartXLabels(mRenderer, xLabels);
		
		renderer.setColor(Color.parseColor("#65BDE3"));
		
		mRenderer.setChartTitle("Completed Jobs");
		
//		mRenderer.setMargins(new int[] { 40, 20, 0, 0 });//
		
		mRenderer.setXAxisMax(8);
		mRenderer.setXAxisMin(0);
		
		dataset.addSeries(series.toXYSeries());
		mRenderer.addSeriesRenderer(renderer);
		
		chartSettings.addYlabelsColor(mRenderer, maxAxis);
		
		GraphicalView chartView = ChartFactory.getBarChartView(
				(Context) context, dataset, mRenderer, Type.DEFAULT);
		return chartView;

	}

	public double[] getValue(ArrayList<HashMap<String, String>> data) {

		double[] y = { 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < data.size(); i++) {
			y[i] = Double.parseDouble(data.get(i).get("real_value"));
			System.out.println(y[i]);
		}

		return y;
	}

	public String[] getLabels(ArrayList<HashMap<String, String>> data) {

		String[] x = { "", "", "", "", "", "", "" };

		for (int i = 0; i < data.size(); i++) {
			x[i] = data.get(i).get("label");
			System.out.println(x[i]);
		}

		return x;
	}

}
