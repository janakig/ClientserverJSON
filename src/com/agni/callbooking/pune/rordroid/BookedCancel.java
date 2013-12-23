package com.agni.callbooking.pune.rordroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class BookedCancel extends Activity implements OnClickListener{

	/** Called when the activity is first created. */
	
	Spinner CallTermType;
	String[] calltermtype = {"Successful drop",
			 "Accident",
			 "Passenger Cancellation",
			 "Vehicle Breakdown",
			 "Dummy Call",
			 "Call Disconnected",
			 "Enquiry",
			 "Driver Not Responding",
			 "Driver Reached Late",
			 "Vehicle Not Available",
			 "Address Not Located"};//drop down list of call term type
	
	AutoCompleteTextView pnr;
	EditText reason;
	Button submit;
	
	String PNR, Reason;
	String CALLTERMTYPE ="Successful drop";
	Intent intent;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel);
       // buildref = (EditText) findViewById(R.id.editTextbuild);
       // buildref.setFocusable(false);
       // buildref.setClickable(false);
       // recvdref = (EditText) findViewById(R.id.editTextrecvd);
      //  recvdref.setFocusable(false);
      //  recvdref.setClickable(false);
     //   build = (TextView) findViewById(R.id.textViewbuild);
     //   recvd = (TextView) findViewById(R.id.textViewrecvd);
        
        
        SaveValuePNR info = new SaveValuePNR(this);
		info.open();
		List<String> data = info.getPNR();
		//Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_LONG).show();
		List<String> array = new ArrayList<String>();
		array.add(data + "\n");
		info.close();
        //declaration of edit texts, buttons etc... 
        
      		
      		reason = (EditText) findViewById(R.id.etCallTerminateReason);
      		submit = (Button) findViewById(R.id.bCancel);
      		submit.setOnClickListener(this); 
      		
      		ArrayAdapter<String> pnrlist = new ArrayAdapter<String>
      		(BookedCancel.this,android.R.layout.simple_dropdown_item_1line,data);
      		pnr = (AutoCompleteTextView) findViewById(R.id.actvPNR);
      		pnr.setAdapter(pnrlist);
      		pnr.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					PNR = parent.getItemAtPosition(pos).toString().trim();
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
      			
      		});
      					
      		ArrayAdapter<String> langadapter = new ArrayAdapter<String> //drop down list of calltermtype
    		(BookedCancel.this,android.R.layout.simple_spinner_item,calltermtype);
      		
      		CallTermType = (Spinner) findViewById(R.id.spCallTermType);
      		CallTermType.setAdapter(langadapter);
      		CallTermType.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					CALLTERMTYPE = parent.getItemAtPosition(pos).toString();
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
      			
      		});
        
    }
					
    @Override			        
    @SuppressLint("NewApi")
	public void onClick(View v) {
		// TODO Auto-generated method stub
    	//getting the information from feilds and converting it to required feild
		 
			 	PNR = pnr.getText().toString();	
				Reason = reason.getText().toString();
			
		//creation of json object
        JSONObject jsonobj; // declared locally so that it destroys after serving its purpose
        jsonobj = new JSONObject();
        try {
        	
        	// lets add some headers (nested headers)
        	jsonobj.put("celcabs_pickup_id",PNR); //phone no.
        	jsonobj.put("call_term_type",CALLTERMTYPE);//address
        	jsonobj.put("call_term_reason",Reason); //customer name
        	//jsonobj.put("last_modified_by",username);
        
        	
        	// Display the contents of the JSON objects
        	//build.setVisibility(View.VISIBLE);
        	//buildref.setVisibility(View.VISIBLE);
        	//buildref.setText(jsonobj.toString(2));
        } catch (JSONException ex) {
        	//build.setVisibility(View.VISIBLE);
        	//buildref.setVisibility(View.VISIBLE);
        	//buildref.setText("Error Occurred while building JSON");
        	ex.printStackTrace();
        	AlertDialog builder1 = new AlertDialog.Builder(BookedCancel.this)
    		.setTitle("Error")
    		.setMessage("Error Occurred while processing Your Cancelling Information")  
    		.setCancelable(false)  
    		.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
    		    public void onClick(DialogInterface dialog, int which)   
    		    {  
    		          // Perform Your Task Here--When Yes Is Pressed.  
    		          dialog.cancel();  
    		    }  
    		})    
    		.show();  
        }
       
        // Now lets begin with the server part
     try {
        	DefaultHttpClient httpclient = new DefaultHttpClient();
        	HttpPost httppostreq = new HttpPost(wurl);
        	StringEntity se = new StringEntity(jsonobj.toString());
        	se.setContentType("application/json;charset=UTF-8");
        	se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
        	httppostreq.setEntity(se);
        	HttpResponse httpresponse = httpclient.execute(httppostreq);
        	HttpEntity resultentity = httpresponse.getEntity();
        	if(resultentity != null) {
        		InputStream inputstream = resultentity.getContent();
        		Header contentencoding = httpresponse.getFirstHeader("Content-Encoding");
        		if(contentencoding != null && contentencoding.getValue().equalsIgnoreCase("gzip")) {
        			inputstream = new GZIPInputStream(inputstream);
        		}
        		
        		String resultstring = convertStreamToString(inputstream);
        		inputstream.close();
        		resultstring = resultstring.substring(1,resultstring.length()-1);
        		//recvd.setVisibility(View.VISIBLE);
        		//recvdref.setVisibility(View.VISIBLE);
        		//recvdref.setText(resultstring + "\n\n" + httppostreq.toString().getBytes());
        		
        		AlertDialog builder = new AlertDialog.Builder(BookedCancel.this)
        		.setTitle("Booked Information")
        		.setMessage(resultstring + "\n\n" + httppostreq.toString().getBytes())  
        		.setCancelable(false)  
        		.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
        		    public void onClick(DialogInterface dialog, int which)   
        		    {  
        		          // Perform Your Task Here--When Yes Is Pressed.  
        		          dialog.cancel();  
        		    }  
        		})    
        		.show(); 
        		
        		if(resultstring.contentEquals("Your booked call has been cancelled")){ 
        			try{
        				
        				 Long lRow1 = Long.parseLong(PNR);
        				 SaveValuePNR ex1 = new SaveValuePNR(this);
        				 ex1.open();
        				 ex1.deleteEntry(lRow1);
        				 ex1.close();
        				}catch(Exception e){
        					
        					String error = e.toString();
        					/*Dialog d = new Dialog(this);
        					d.setTitle("Hang On!!!");
        					TextView tv = new TextView(this);
        					tv.setText(error);
        					d.setContentView(tv);
        					d.show();*/
       Toast.makeText(getApplicationContext(), "Error in deleting cancelled PNR from the list", Toast.LENGTH_LONG).show();  
        	        		
        				}	
        		}
        	}
        } catch (Exception e) {
        	//recvd.setVisibility(View.VISIBLE);
    		//recvdref.setVisibility(View.VISIBLE);
        	//recvdref.setText("Error Occurred while processing JSON");
        	//recvdref.setText(e.getMessage());
        	
        	AlertDialog builder1 = new AlertDialog.Builder(BookedCancel.this)
    		.setTitle("Error")
    		.setMessage("Error Occurred while processing JSON")  
    		.setCancelable(false)  
    		.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
    		    public void onClick(DialogInterface dialog, int which)   
    		    {  
    		          // Perform Your Task Here--When Yes Is Pressed.  
    		          dialog.cancel();  
    		    }  
    		})    
    		.show();  
        	
		}
    
     
     
     
        } 
		

    private String convertStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Stream Exception", Toast.LENGTH_SHORT).show();
        }
        return total.toString();
    }
    public EditText buildref;
    public EditText recvdref;
    public TextView build;
    public TextView recvd;
    //public static final String wurl = "http://localhost/mnt/sdcard/book.json";
    public static final String wurl = "http://192.168.23.23:3000/cc.json";
    //public static final String wurl = "http://192.168.23.7:3000/ws.json";
    //public static final String wurl = "http://192.168.23.20/PhpProject4/test.json";
   // public static final String wurl = "http://192.168.23.20:8080/";
     //public static final String wurl = "http://192.168.23.20:46303/jsonproject/";
      //public static final String wurl =  "http://api.androidhive.info/contacts/";
    //public static final String wurl = "http://192.168.100.4/testmysql.php?test=true";

	
}

	

