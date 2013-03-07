package com.skybot.charts;

public class GetMaxAxis {
	
	public double getMax(double[] y,String key) {
		double maxValue = 0;
		double maxAxis = 0;
		
		
		for(int i=0; i<y.length;i++){ 
			maxValue = y[0];
			if(y[i] > maxValue) { 
				maxValue = y[i];
			}
		}
		System.out.println(maxValue);
		//maxAxis = (float) Math.ceil(maxValue);
		
		if(maxValue == 0) {
			maxAxis = 1;
		}
		
		if(maxAxis == (int)maxAxis) {
			maxAxis =maxAxis + 0.5	;
		}
	
		
		return maxAxis;
	}
	
	public double concatenateArrays(double[] x, double[] y, double[] z) {
		return 0;
		
	}
}

