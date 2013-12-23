package com.agni.callbooking.pune.rordroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Welcome extends Activity implements OnClickListener{

	Button next,exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		next = (Button) findViewById(R.id.bnext);
		exit = (Button) findViewById(R.id.bexit);
		next.setOnClickListener(this);
		exit.setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp=getMenuInflater();
		blowUp.inflate(R.menu.welcome_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.aboutUs:
			Intent a=new Intent(getApplicationContext(),About.class);
			startActivity(a);
			break;
		 case R.id.exit:
				finish();
				break;
			}
		return false;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bnext:
		Intent i = new Intent(getApplicationContext(),ClientServerJSONActivity.class);
		//Intent i = new Intent(getApplicationContext(),Form.class);
		startActivity(i);
		break;
		case R.id.bexit:
			finish();
			break;
		}
	}

}
