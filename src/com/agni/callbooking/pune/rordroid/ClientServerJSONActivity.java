package com.agni.callbooking.pune.rordroid;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import com.agni.callbooking.pune.rordroid.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ClientServerJSONActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	EditText username,password;
	Button login;
	CheckBox cbSave,cbLoad;
	String Username, Password;
	int hour, minute, day, month, year;
	int counter = 2;
	public final static String EXTRA_MESSAGE =  "com.agni.callbooking.rordroid.MESSAGE";
	public final static String EXTRA_MESSAGE1 =  "com.agni.callbooking.rordroid.MESSAGE1";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
      //  buildref = (EditText) findViewById(R.id.editTextbuild);
      //  buildref.setFocusable(false);
      //  buildref.setClickable(false);
       // recvdref = (EditText) findViewById(R.id.editTextrecvd);
       // recvdref.setFocusable(false);
       // recvdref.setClickable(false);
        
        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.bLogin);
        login.setOnClickListener(this);
        cbSave = (CheckBox) findViewById(R.id.cbSave);
        cbLoad = (CheckBox) findViewById(R.id.cbLoad);
        cbLoad.setOnClickListener(this);      
    }
        
        @Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
        	Username = username.getText().toString();
            Password = password.getText().toString();
            switch(v.getId()){
            case R.id.bLogin:
            	 if (cbSave.isChecked() == true){
                     boolean didItWork = true;
             			
             			try{
             			SaveValueLogin entry = new SaveValueLogin(ClientServerJSONActivity.this);
             			entry.open();
             			entry.createEntry(Username,Password);
             			entry.close();
             			
             			}catch(Exception e){
             				didItWork = false;
             				String error = e.toString();
             			/*	Dialog d = new Dialog(this);
             				d.setTitle("Hang On!!!");
             				TextView tv = new TextView(this);
             				tv.setText(error);
             				d.setContentView(tv);
             				d.show();*/
             				Toast.makeText(getApplicationContext(), "Error in saving Login Details", Toast.LENGTH_LONG).show();
             				
             			}finally{
             				if(didItWork){
             				/*	Dialog d = new Dialog(this);
             					d.setTitle("Success");
             					TextView tv = new TextView(this);
             					tv.setText("Remembered your username and password");
             					d.setContentView(tv);
             					d.show();*/
            	Toast.makeText(getApplicationContext(), "Remembered your username and password",Toast.LENGTH_LONG).show();
             				}
             			}
             		}   
    		// TODO Auto-generated method stub
        JSONObject jsonobj; // declared locally so that it destroys after serving its purpose
        jsonobj = new JSONObject();
        try {
        
        	long time = System.currentTimeMillis()/1000;
        	Timestamp tsTemp = new Timestamp(time);
        	String ts =  tsTemp.toString();
        	// lets add some headers (nested headers)
        	JSONObject header = new JSONObject();
        	jsonobj.put("user_name",Username); // Username
        	jsonobj.put("password",Password); //Password
        	// Display the contents of the JSON objects
        	//buildref.setText(jsonobj.toString(2));
        } catch (JSONException ex) {
        	///buildref.setText("Error Occurred while building JSON");
        	Toast.makeText(getApplicationContext(), "Error Occurred while building JSON", Toast.LENGTH_LONG).show();
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
        	
        	HttpParams httpParams = new BasicHttpParams();
        	int connection_Timeout = 5000;
        	HttpConnectionParams.setConnectionTimeout(httpParams, connection_Timeout);
        	HttpConnectionParams.setSoTimeout(httpParams, connection_Timeout);
        	DefaultHttpClient httpClient = new DefaultHttpClient(httpParams); 
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
     Toast.makeText(getApplicationContext(), resultstring + "\n\n" + httppostreq.toString().getBytes(), Toast.LENGTH_LONG).show();
        		//recvdref.setText(resultstring + "\n\n" + httppostreq.toString().getBytes());
        			if(resultstring.contentEquals("welcome")){
        				Intent i = new Intent(getApplicationContext(),Form.class);
            			i.putExtra(EXTRA_MESSAGE, Username);
            			i.putExtra(EXTRA_MESSAGE1, Password);
            			startActivity(i);
        			
        		}
        		else{
        			Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();	
        		}
        	}
        } catch (Exception e) {
      Toast.makeText(getApplicationContext(), "Error Occurred while building JSON", Toast.LENGTH_LONG).show();
        	//recvdref.setText("Error Occurred while processing JSON");
        	//recvdref.setText(e.getMessage());
		}
        break;
            case R.id.cbLoad:
                if(cbLoad.isChecked() == true){
                	 try{
                		 
                		 SaveValueLogin value = new  SaveValueLogin(this);
                		 value.open();
                		 String returnedUserName = value.getUsername();
                		 String returnedPassword = value.getPassword();
                		 value.close();
                		 username.clearComposingText();
                		 password.clearComposingText();
                		 username.setText(returnedUserName);
                		 password.setText(returnedPassword);
                		 
                		}catch(Exception e){
                			
                			String error = e.toString();
                		/*	Dialog d = new Dialog(this);
                			d.setTitle("Hang On!!!");
                			TextView tv = new TextView(this);
                			tv.setText(error);
                			d.setContentView(tv);
                			d.show();*/
 Toast.makeText(getApplicationContext(), "There is no previous Login Details to keep you Logged in", Toast.LENGTH_LONG).show();
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
    //public static final String wurl = "http://localhost/mnt/sdcard/book.json";
  //public static final String wurl = "http://192.168.23.23:3000/ws.json";
 public static final String wurl = "http://192.168.23.23:3000/auth.json";
 //public static final String wurl = "http://192.168.23.7:3000/auth.json";
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
}