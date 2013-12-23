package com.agni.callbooking.pune.rordroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PNRList  extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pnrview);
		
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo2);
		SaveValuePNR info = new SaveValuePNR(this);
		info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
	}
	

}