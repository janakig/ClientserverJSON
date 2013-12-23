package com.agni.callbooking.pune.rordroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginView extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginview);
		
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		SaveValueLogin info = new SaveValueLogin(this);
		info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
	}
	

}