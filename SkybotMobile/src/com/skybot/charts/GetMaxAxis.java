package com.skybot.charts;

public class GetMaxAxis {
	
	public double getMax(double[] y) {
		double maxValue = 0;
		float maxAxis;
		for(int i=0; i<y.length;i++){ 
			maxValue = y[0];
			if(y[i] > maxValue) { 
				maxValue = y[i];
			}
		}
		System.out.println(maxValue);
		maxAxis = (float) Math.ceil(maxValue);
		
		if(maxAxis == 0) {
			maxAxis = 1;
		}
		
		return maxAxis;
	}
}

