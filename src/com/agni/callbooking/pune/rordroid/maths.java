package com.agni.callbooking.pune.rordroid;

public class maths {
	
	public int docalculation(int x,int y, int seats){
		if(x <= 4){
			y = 1;	
		}else {
			int remainder =x % seats;
			int quotient = x / seats;
			
			if(remainder == 0){
				y = (int)quotient;
			}else{
			y = quotient+1;
			}
		}
		return y;
		
	}

}
