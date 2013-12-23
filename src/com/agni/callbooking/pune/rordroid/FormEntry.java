package com.agni.callbooking.pune.rordroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FormEntry extends Activity implements OnClickListener{

	EditText rowid;
	Button view,delete;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entry);
		
		rowid = (EditText) findViewById(R.id.etRow);
		view = (Button) findViewById(R.id.bView);
		delete = (Button) findViewById(R.id.bDelete);
		view.setOnClickListener(this);
		delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bView:
			Intent i = new Intent(getApplicationContext(), FormView.class);
			startActivity(i);
			break;
		case R.id.bDelete:
			 try{
				 String sRow1 = rowid.getText().toString();
				 Long lRow1 = Long.parseLong(sRow1);
				 SaveValueForm ex1 = new SaveValueForm(this);
				 ex1.open();
				 ex1.deleteEntry(lRow1);
				 ex1.close();
				}catch(Exception e){
					
					String error = e.toString();
					Dialog d = new Dialog(this);
					d.setTitle("Hang On!!!");
					TextView tv = new TextView(this);
					tv.setText(error);
					d.setContentView(tv);
					d.show();
				}
				 break;
					
				}
			
			
		}
		
	}
	


