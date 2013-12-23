package com.agni.callbooking.pune.rordroid;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Form extends Activity implements OnClickListener{

	/** Called when the activity is first created. */
	Spinner vehicleclass,vehicletype,packageclass,filterid,language; //Drop down lists
	
	String[] vclass = {"Economy","Comfort","SUV"}; //drop down list of vehicle class
	
	String[] vtypeEconomy ={"INDC AC"}; //drop down list of economy vehicle type
	
	String[] vtypeComfort = {"INDIGO XL", "LOGAN","INDIGO AC","IKON","Swift Dzire","Fiesta",
			"VENTURE","Etios"};//drop down list of comfort vehicle type
	
	String[] vtypeSuv = {"Tempo Traveller", "Xylo","QUAL AC", "QUAL","INNOVA",
			"INNOVA AC"};//drop down list of SUV vehicle type
	
	String[] packagesEconomy = {"L1","CJ","CE","N1","C6","N4","CB"};//drop down list of economy package type
	
	String[] packagesComfort = {"L3","N8","L2","N7","C7","C8","CC",
			"CF","N3","N5","N6"};//drop down list of comfort package type
	
	String[] packageSuv = {"L4","CJ","CH","CI","CK"};//drop down list of suv package type
	
	String[] filterId = {"NORMAL","ROBERT-BOSCH","BMTC","SIEMENS-BIAL","CARPOOL","WIPRO",
			"COMMUTEEASY","BIAL","BLR GOLF CLUB-VIP","VIP CUST","BULK BOOKING","Drivers Own Booking",
			"KOACON 2010","Airlift","VIV INDIA 2010","GAIL PLASTASIA","TAXI","BLR TRUF CLUB-VIP",
			"BLR TRUF CLUB-VIP","CENTUARY CLUB-VIP","SADASHIVANAGAR CLUB-VIP"," BLR-CLUB","Airlift Cash",
			"Airlift Credit","Bigtech","VIP Columbia asia","Astra Zeneca","taxiforsure","CLINTEC",
			"TOTAL RECALL"," ROCK N INDIA","ACPE7 EVENT"};//drop down list of filter id
	String[] languageSpoken = {"English"};
	EditText phone;
	EditText firstname,lastname;
	EditText count;
	EditText client,user;
	Button submit,save,load;
	DatePicker date;
	TimePicker time;
	AutoCompleteTextView address,pickup,drop;
	CheckBox confirm_address;
	String Phone, fName, lName, Address, Pickup, Drop, Time, Language,Client,User;
	String VehicleClass ="Economy";
	String FilterId = "NORMAL";
	String type = "";
	String Package = "";
	int hour, minute, day, month, year;
	int pcounter; 
	int vcounter;
	int seats;
	String ts;
	int Confirm_Address;
	int vehicle_count;
	SharedPreferences sharedPrefs;
	String username;
	String password;
	Intent intent;
	boolean didItWork = true;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //buildref = (EditText) findViewById(R.id.editTextbuild);
        //buildref.setFocusable(false);
       // buildref.setClickable(false);
        //recvdref = (EditText) findViewById(R.id.editTextrecvd);
        //recvdref.setFocusable(false);
       // recvdref.setClickable(false);
       // build = (TextView) findViewById(R.id.textViewbuild);
        //recvd = (TextView) findViewById(R.id.textViewrecvd);
        //declaration of edit texts, buttons, time & date picker etc... 
      		phone = (EditText) findViewById(R.id.etPhone);
      		firstname = (EditText) findViewById(R.id.etFirstName);
      		lastname = (EditText) findViewById(R.id.etLastName);
      		time = (TimePicker) findViewById(R.id.timePicker1);
      		date = (DatePicker) findViewById(R.id.datePicker1);
      		count = (EditText) findViewById(R.id.etCount);
      		//client = (EditText) findViewById(R.id.etClient);
      		submit = (Button) findViewById(R.id.bSubmit);
      		submit.setOnClickListener(this); 
      		save = (Button) findViewById(R.id.bSave);
      		save.setOnClickListener(this);
      		load = (Button) findViewById(R.id.bLoad);
      		load.setOnClickListener(this);
      		confirm_address = (CheckBox) findViewById(R.id.checkBox1);
      		final String[] ListPlaces= getResources().getStringArray(R.array.listplacesPune);
      		
      		
      		
      		ArrayAdapter<String> addradapter = new ArrayAdapter<String>
      		(Form.this,android.R.layout.simple_dropdown_item_1line,ListPlaces);
      		address = (AutoCompleteTextView) findViewById(R.id.actaddress);
      		address.setAdapter(addradapter);
      		address.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					Address = parent.getItemAtPosition(pos).toString();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
      			
      		});
      		
      		ArrayAdapter<String> pickupadapter = new ArrayAdapter<String>
      		(Form.this,android.R.layout.simple_dropdown_item_1line,ListPlaces);
      		pickup = (AutoCompleteTextView) findViewById(R.id.etPickUp);
      		pickup.setAdapter(pickupadapter);
      		pickup.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					Pickup = parent.getItemAtPosition(pos).toString();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
      			
      		});
      		
      		ArrayAdapter<String> dropadapter = new ArrayAdapter<String>
      		(Form.this,android.R.layout.simple_dropdown_item_1line,ListPlaces);
      		drop = (AutoCompleteTextView) findViewById(R.id.etDrop);
      		drop.setAdapter(dropadapter);
      		drop.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					Drop = parent.getItemAtPosition(pos).toString();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
      			
      		});
     		

      				
      		ArrayAdapter<String> langadapter = new ArrayAdapter<String> //drop down list of vehicle class
    		(Form.this,android.R.layout.simple_spinner_item,languageSpoken);
      		
      		language = (Spinner) findViewById(R.id.spLanguage);
      		language.setAdapter(langadapter);
      		language.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					Language = parent.getItemAtPosition(pos).toString();
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
      			
      		});
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String> //drop down list of vehicle class
		(Form.this,android.R.layout.simple_spinner_item,vclass);
        
        vehicleclass = (Spinner) findViewById(R.id.SpVehicleClass1);
        vehicleclass.setAdapter(adapter);
        vehicleclass.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				 VehicleClass =parent.getItemAtPosition(pos).toString();
				 
				 
				 switch(pos){
				 case 0:
					 
					 ArrayAdapter<String> adapter2 = new ArrayAdapter<String>//drop down list of economy vehicle type
						(Form.this,android.R.layout.simple_spinner_item,vtypeEconomy);
				        vehicletype = (Spinner) findViewById(R.id.spVehicleType);
				        vehicletype.setAdapter(adapter2);
				        vehicletype.setOnItemSelectedListener(new OnItemSelectedListener(){

							@Override
							public void onItemSelected(AdapterView<?> parent, View view,
									int pos, long id) {
								// TODO Auto-generated method stub
								type = parent.getItemAtPosition(pos).toString();
								seats = 4;		
								
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub
								
							}
				        	
				        });
				        
				        
				        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
						(Form.this,android.R.layout.simple_spinner_item,packagesEconomy);//drop down list of economy package type
				        packageclass = (Spinner) findViewById(R.id.spPackageType);
				        packageclass.setAdapter(adapter3);
				        packageclass.setOnItemSelectedListener(new OnItemSelectedListener(){

							@Override
							public void onItemSelected(AdapterView<?> parent, View view,
									int pos, long id) {
								// TODO Auto-generated method stub
								Package = parent.getItemAtPosition(pos).toString();
								
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub
								
							}
				        	
				        });
					 break;
				 case 1:
					 ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
			    		(Form.this,android.R.layout.simple_spinner_item,vtypeComfort);//drop down list of comfort vehicle type
			            vehicletype = (Spinner) findViewById(R.id.spVehicleType);
			            vehicletype.setAdapter(adapter4);
			            vehicletype.setOnItemSelectedListener(new OnItemSelectedListener(){

			    			@Override
			    			public void onItemSelected(AdapterView<?> parent, View view,
			    					int pos, long id) {
			    				// TODO Auto-generated method stub
			    				type = parent.getItemAtPosition(pos).toString();
			    				switch(pos){
			    				case 0:
			    					seats = 4;
			    					break;
			    				case 1:
			    					seats = 4;
			    					break;
			    				case 2:
			    					seats = 4;
			    					break;
			    				case 3:
			    					seats = 4;
			    					break;
			    				case 4:
			    					seats = 4;
			    					break;
			    				case 5:
			    					seats = 4;
			    					break;
			    				case 6:
			    					seats = 4;
			    					break;
			    				case 7:
			    					seats = 4;
			    					break;		
			    				}
			    			}

			    			@Override
			    			public void onNothingSelected(AdapterView<?> arg0) {
			    				// TODO Auto-generated method stub
			    				
			    			}
			            	
			            });
			            
			            
			            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>
			    		(Form.this,android.R.layout.simple_spinner_item,packagesComfort);//drop down list of comfort package type
			            packageclass = (Spinner) findViewById(R.id.spPackageType);
			            packageclass.setAdapter(adapter5);
			            packageclass.setOnItemSelectedListener(new OnItemSelectedListener(){

			    			@Override
			    			public void onItemSelected(AdapterView<?> parent, View view,
			    					int pos, long id) {
			    				// TODO Auto-generated method stub
			    				Package = parent.getItemAtPosition(pos).toString();
			    			}

			    			@Override
			    			public void onNothingSelected(AdapterView<?> arg0) {
			    				// TODO Auto-generated method stub
			    				
			    			}
			            	
			            });
					 break;
				 case 2:
					 ArrayAdapter<String> adapter6 = new ArrayAdapter<String>
			    		(Form.this,android.R.layout.simple_spinner_item,vtypeSuv);//drop down list of SUV vehicle type
			            vehicletype = (Spinner) findViewById(R.id.spVehicleType);
			            vehicletype.setAdapter(adapter6);
			            vehicletype.setOnItemSelectedListener(new OnItemSelectedListener(){

			    			@Override
			    			public void onItemSelected(AdapterView<?> parent, View view,
			    					int pos, long id) {
			    				// TODO Auto-generated method stub
			    				type = parent.getItemAtPosition(pos).toString();
			    				switch(pos){
			    				case 0:
			    					seats = 15;
			    					break;
			    				case 1:
			    					seats = 6;
			    					break;
			    				case 2:
			    					seats = 9;
			    					break;
			    				case 3:
			    					seats = 9;
			    					break;
			    				case 4:
			    					seats = 6;
			    					break;
			    				case 5:
			    					seats = 6;
			    					break;
			    				}
			    			}

			    			@Override
			    			public void onNothingSelected(AdapterView<?> arg0) {
			    				// TODO Auto-generated method stub
			    				
			    			}
			            	
			            });
			            
			            
			            ArrayAdapter<String> adapter7 = new ArrayAdapter<String>
			    		(Form.this,android.R.layout.simple_spinner_item,packageSuv);//drop down list of suv package type
			            packageclass = (Spinner) findViewById(R.id.spPackageType);
			            packageclass.setAdapter(adapter7);
			            packageclass.setOnItemSelectedListener(new OnItemSelectedListener(){

			    			@Override
			    			public void onItemSelected(AdapterView<?> parent, View view,
			    					int pos, long id) {
			    				// TODO Auto-generated method stub
			    				Package = parent.getItemAtPosition(pos).toString();
			    			}

			    			@Override
			    			public void onNothingSelected(AdapterView<?> arg0) {
			    				// TODO Auto-generated method stub
			    				
			    			}
			            	
			            });
					 break;
					 
				 }
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
        	
        } );
        
        
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>//drop down list of filter id
		(Form.this,android.R.layout.simple_spinner_item,filterId);
        filterid = (Spinner) findViewById(R.id.spFilterId);
        filterid.setAdapter(adapter1);
        filterid.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				FilterId = parent.getItemAtPosition(pos).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
		
   
    }
    
  
	
    @SuppressLint("NewApi")
	public void onClick(View v) {
		// TODO Auto-generated method stub
    	//getting the information from feilds and converting it to required feild
		 
		 switch (v.getId()){
		 case R.id.bSave:
			 
			 Phone = phone.getText().toString();	
				fName = firstname.getText().toString();
				lName = lastname.getText().toString();	
				//Client = client.getText().toString();
				Address = address.getText().toString();
				Pickup = pickup.getText().toString();
				Drop = drop.getText().toString();
				hour = time.getCurrentHour();
				minute = time.getCurrentMinute();
				day = date.getDayOfMonth();
				month = date.getMonth() + 1;
				year = date.getYear();
				pcounter = Integer.parseInt(count.getText().toString());
				Time = String.format("%d-%02d-%d %02d:%02d:00",year,month,day,hour,minute);
				
 			try{
 			SaveValueForm entry = new SaveValueForm(Form.this);
 			entry.open();
 			entry.createEntry(Phone,fName,lName,Address,Pickup,Drop,pcounter);
 			entry.close();
 			
 			}catch(Exception e){
 				didItWork = false;
 				/*String error = e.toString();
 				Dialog d = new Dialog(this);
 				d.setTitle("Hang On!!!");
 				TextView tv = new TextView(this);
 				tv.setText(error);
 				d.setContentView(tv);
 				d.show();*/	
       Toast.makeText(getApplicationContext(), "Error while Saving your Booked Information", Toast.LENGTH_LONG).show();  
        		
 				
 			}finally{
 				if(didItWork){
 				/*	Dialog d = new Dialog(this);
 					d.setTitle("Success");
 					TextView tv = new TextView(this);
 					tv.setText("Your Booking has been Saved");
 					d.setContentView(tv);
 					d.show();*/	
 	       Toast.makeText(getApplicationContext(), "Your Booking has been Saved\n Thank You", Toast.LENGTH_LONG).show();  
 	        		
 					
 				}
 			}
			 break;
		 case R.id.bLoad:
			 
			 try{
        		 
        		 SaveValueForm value = new  SaveValueForm(this);
        		 value.open();
        		 String returnedPhoneno = value.getPhoneno();
        		 String returnedFirstname = value.getFirstname();
        		 String returnedLastname = value.getLastname();
        		 String returnedAddress = value.getAddress();
        		 String returnedPickup = value.getPickup();
        		 String returnedDrop = value.getDrop();
        		 String returnedPasscount = value.getPassCount();
        		// String returnedClient = value.getClient();	 
        		 value.close();
        		 phone.clearComposingText();
        		 phone.setText(returnedPhoneno);
        		 firstname.clearComposingText();
        		 firstname.setText(returnedFirstname);
        		 lastname.clearComposingText();
        		 lastname.setText(returnedLastname);
        		 address.clearComposingText();
        		 address.setText(returnedAddress);
        		 pickup.clearComposingText();
        		 pickup.setText(returnedPickup);
        		 drop.clearComposingText();
        		 drop.setText(returnedDrop);
        		 count.clearComposingText();
        		 count.setText(returnedPasscount);
        		// client.clearComposingText();
        		 //client.setText(returnedClient);		 
        	}catch(Exception e){
        			
        			String error = e.toString();
        		/*	Dialog d = new Dialog(this);
        			d.setTitle("Hang On!!!");
        			TextView tv = new TextView(this);
        			tv.setText(error);
        			d.setContentView(tv);
        			d.show();*/
      Toast.makeText(getApplicationContext(), "Error while loding your previous Booked Information", Toast.LENGTH_LONG).show();  
            		
        		}
			 break;
			 
		 case R.id.bSubmit:	
			 	Phone = phone.getText().toString();	
				fName = firstname.getText().toString();
				lName = lastname.getText().toString();	
				//Client = client.getText().toString();
				Address = address.getText().toString();
				Pickup = pickup.getText().toString();
				Drop = drop.getText().toString();
				hour = time.getCurrentHour();
				minute = time.getCurrentMinute();
				day = date.getDayOfMonth();
				month = date.getMonth() + 1;
				year = date.getYear();
				pcounter = Integer.parseInt(count.getText().toString());
				maths simple = new maths();
				vehicle_count = simple.docalculation(pcounter, vcounter,seats);
				intent= getIntent();
				username = intent.getStringExtra(ClientServerJSONActivity.EXTRA_MESSAGE).toString();
				password = intent.getStringExtra(ClientServerJSONActivity.EXTRA_MESSAGE1).toString();
				
				
	      		
				
				if (confirm_address.isChecked() == true){
					Confirm_Address = 2;
				}else{
					Confirm_Address = 1;
					}
			
				 Time = String.format("%d-%02d-%02d %02d:%02d:00",year,month,day,hour,minute);		
		
		if(confirm_address.isChecked() == false){
				Toast.makeText(getApplicationContext(), "please confirm the address before you proceed", Toast.LENGTH_LONG).show();
			}	
			else if(vehicle_count == 0 || pcounter == 0){
		  Toast.makeText(getApplicationContext(), "please do enter valid passenger count", Toast.LENGTH_LONG).show();
	  }
	else if (Phone.length() != 10){
	   		Toast.makeText(getApplicationContext(), "Please enter phone number correctly. It should be 10 digit number.", Toast.LENGTH_LONG).show();
	 }
		else if(fName.isEmpty() == true){
				Toast.makeText(getApplicationContext(), "Please do enter customer's First Name", Toast.LENGTH_LONG).show();
			}
	 // else if(Client.isEmpty() == true){
		 // Toast.makeText(getApplicationContext(), "Please enter the client name",Toast.LENGTH_LONG).show();
		 // }
	  else if(Address.isEmpty() == true){
		 Toast.makeText(getApplicationContext(), "Please do enter your current address", Toast.LENGTH_LONG).show(); 
	  }
	  else if(Pickup.isEmpty() == true){
		  Toast.makeText(getApplicationContext(), "Please do enter pickup location", Toast.LENGTH_LONG).show();
	  }
	  else if(Drop.isEmpty() == true){
		  Toast.makeText(getApplicationContext(), "Please do enter drop location", Toast.LENGTH_LONG).show();
	  }
	  
	  else{
		//creation of json object
        JSONObject jsonobj; // declared locally so that it destroys after serving its purpose
        jsonobj = new JSONObject();
        try {
        	
        	// lets add some headers (nested headers)
        	jsonobj.put("phone1", Phone); //phone no.
        	jsonobj.put("customer_name", fName); //customer name
        	jsonobj.put("pickup_address1",Address);//address
        	jsonobj.put("pickup_location",Pickup); //pickup location
        	jsonobj.put("drop_location", Drop); //drop location
        	jsonobj.put("required_vehicle_class",VehicleClass); //vehicle class
        	jsonobj.put("requested_pickup_time", Time); //pickup time
        	jsonobj.put("passenger_count",pcounter); //passenger count
        	jsonobj.put("required_vehicle_type", type); //vehicle type
        	jsonobj.put("assigned_package_id", Package); //package type
        	jsonobj.put("vehicles_count",vehicle_count); //vehicle count 
        	jsonobj.put("filter_id", FilterId); //filter id
        	jsonobj.put("call_centre_instructions", "cab"); //call center instructions
        	jsonobj.put("call_status_id", Confirm_Address);
        	jsonobj.put("language", Language);
        	jsonobj.put("cba", username);
        	//jsonobj.put("last_modified_by",username);
        
        	
        	// Display the contents of the JSON objects
        	//build.setVisibility(View.VISIBLE);
        	//buildref.setVisibility(View.VISIBLE);
        	//Dialog d = new Dialog(this);
    		//d.setTitle("Sent Information");
    		//TextView tv = new TextView(this);
        	//tv.setText(jsonobj.toString(2));
        	//d.setContentView(tv);
    		//d.show();
        } catch (JSONException ex) {
        	//build.setVisibility(View.VISIBLE);
        	//buildref.setVisibility(View.VISIBLE);
        	//Dialog d = new Dialog(this);
    		//d.setTitle("Sent Information");
    		//TextView tv = new TextView(this);
        	//tv.setText("Error Occurred while building JSON");
        	//d.setContentView(tv);
    		//d.show();
        	ex.printStackTrace();
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
        	/*	Dialog d = new Dialog(this);
        		d.setTitle("Booked Information");
        		TextView tv = new TextView(this);
        		tv.setText(resultstring + "\n\n" + httppostreq.toString().getBytes());
        		d.setContentView(tv);
        		d.show();*/
        		
        		AlertDialog builder = new AlertDialog.Builder(Form.this)
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
        		
        		//Toast.makeText(getApplicationContext(), resultstring + "\n" + resultstring.length() + "\n" + resultstring.substring(47,resultstring.length()),Toast.LENGTH_LONG).show();
        		String strbuf = "Your Call has Booked Thank You and your PNR is ";
        		if(resultstring.startsWith(strbuf)){
        		String PNR = resultstring.substring(47,resultstring.length());
        		try{
         			SaveValuePNR entry = new SaveValuePNR(Form.this);
         			entry.open();
         			entry.createEntry(PNR,Pickup,Drop);
         			entry.close();
         			
         			}catch(Exception e){
         				didItWork = false;
         				String error = e.toString();
         				/*Dialog d1 = new Dialog(this);
         				d1.setTitle("Hang On!!!");
         				TextView tv1 = new TextView(this);
         				tv1.setText(error);
         				d1.setContentView(tv1);
         				d1.show();*/
         				
         				
                		Toast.makeText(getApplicationContext(), "Error in saving the PNR number", Toast.LENGTH_LONG).show();  
                		
         				
         			}finally{
         				if(didItWork){
         					/*Dialog d2 = new Dialog(this);
         					d2.setTitle("Success");
         					TextView tv2 = new TextView(this);
         					tv2.setText("Your PNR number has been saved with pickup and drop location");
         					d2.setContentView(tv2);
         					d2.show();*/		
 Toast.makeText(getApplicationContext(), "Your PNR number has been saved with pickup and drop location", Toast.LENGTH_LONG).show();  
                    		
         					
         				}
         			}
        		}
        	}
        } catch (Exception e) {
        	//recvd.setVisibility(View.VISIBLE);
    		//recvdref.setVisibility(View.VISIBLE);
        	//Dialog d = new Dialog(this);
    		//d.setTitle("Booked Information");
    		//TextView tv = new TextView(this);
        	//tv.setText("Error Occurred while processing JSON");
        	//tv.setText(e.getMessage());
        	//d.setContentView(tv);
    		//d.show();
        	e.printStackTrace();
		}
        } 
		break;
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
    public static final String wurl = "http://192.168.23.23:3000/ws.json";
    //public static final String wurl = "http://192.168.23.7:3000/ws.json";
    //public static final String wurl = "http://192.168.23.20/PhpProject4/test.json";
   // public static final String wurl = "http://192.168.23.20:8080/";
     //public static final String wurl = "http://192.168.23.20:46303/jsonproject/";
      //public static final String wurl =  "http://api.androidhive.info/contacts/";
    //public static final String wurl = "http://192.168.100.4/testmysql.php?test=true";

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp=getMenuInflater();
		blowUp.inflate(R.menu.form_menu, menu);
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
			
		case R.id.loginentry:
			Intent q = new Intent(getApplicationContext(), LoginEntry.class);
			startActivity(q);
			break;
		case R.id.bookingentry:
			Intent r = new Intent(getApplicationContext(), FormEntry.class);
			startActivity(r);
		    break;
		case R.id.CancelCall:
			Intent s = new Intent(getApplicationContext(), BookedCancel.class);
			startActivity(s);
			break;
		case R.id.PnrList:
			Intent t = new Intent(getApplicationContext(), PNRList.class);
			startActivity(t);
			break;
        case R.id.exit:
			finish();
			break;
		}
		return false;
	}	
}

	
