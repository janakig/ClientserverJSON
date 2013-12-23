package com.agni.callbooking.pune.rordroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FormView extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formview);
		
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo1);
		SaveValueForm info = new SaveValueForm(this);
		info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
	}
	

}