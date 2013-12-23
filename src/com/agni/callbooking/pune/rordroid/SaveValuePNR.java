package com.agni.callbooking.pune.rordroid;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SaveValuePNR {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_PNR = "pnr";
	public static final String KEY_PICKUP = "pickup_address";
	public static final String KEY_DROP = "drop_address";
	
	private static final String DATABASE_NAME = "RordroidPNR";
	static final String DATABASE_TABLE = "PNRLIST";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper  ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourdatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		//SQLiteDatabase ourdatabase;
		//ourdatabase = DbHelper("MDT.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_PNR + " TEXT NOT NULL, " +
					KEY_PICKUP + " TEXT NOT NULL, " +
					KEY_DROP + " TEXT NOT NULL);"
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
			onCreate(db);
			
		}
		
	}
	
	public SaveValuePNR(Context c){
		ourContext = c;
	}
	
	public SaveValuePNR open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourdatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String pnr, String pickup, String drop) {
		// TODO Auto-generated method stub
	ContentValues cv = new ContentValues();
	cv.put(KEY_PNR, pnr);
	cv.put(KEY_PICKUP, pickup);
	cv.put(KEY_DROP, drop);
	return ourdatabase.insert(DATABASE_TABLE, null, cv);
	
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PNR,KEY_PICKUP, KEY_DROP};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				              null, null, null, null, null);
		String result = " ";
		
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int ipnr = c.getColumnIndex(KEY_PNR);
		int ipickup = c.getColumnIndex(KEY_PICKUP);
		int idrop = c.getColumnIndex(KEY_DROP);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + "\t\t\t"+ c.getString(ipnr) + "\t\t\t\t" + c.getString(ipickup) +"\t\t\t\t" +c.getString(idrop)+ "\n";
				
		}
		return result;
	}

	public List<String> getPNR()  {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PNR,KEY_PICKUP, KEY_DROP};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				             null, null, null, null, null);
		
		List<String> array = new ArrayList<String>();
		int ipnr = c.getColumnIndex(KEY_PNR);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){ 
			    String pnr = c.getString(ipnr);
			    array.add(pnr);	    
		}
		return array;
		
		
	}

	
	public String getPickup() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PNR,KEY_PICKUP, KEY_DROP};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String pickup = c.getString(2);
			return pickup;
		}
		return null;
	}

	public String getDrop() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PNR,KEY_PICKUP, KEY_DROP};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String drop = c.getString(3);
			return drop;
		}
		return null;
	}
	
	public void UpdateEntry(Long lRow,String mPNR, String mPickup, String mDrop) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_PNR, mPNR);
		cvUpdate.put(KEY_PICKUP, mPickup);
		cvUpdate.put(KEY_DROP, mDrop);
		ourdatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
		
	}

	public void deleteEntry(Long lRow1) throws SQLException {
		// TODO Auto-generated method stub
		ourdatabase.delete(DATABASE_TABLE, KEY_PNR + "=" + lRow1, null);
		
	}
	
/*	public boolean Login(String username,String password)
	{
	    
	    Cursor c = ourdatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE "
                + KEY_USERNAME + "='" + username +"'AND "+KEY_PASSWORD+"='"+password+"'" ,  null);
	        if (c.getCount()>0)
	            return true;
	         else
	            return false;
	   
	   
	}
	 public boolean validateUser(String username, String password){
         Cursor c = ourdatabase.rawQuery(
                  "SELECT * FROM " + DATABASE_TABLE + " WHERE "
                          + KEY_USERNAME + "='" + username +"'AND "+KEY_PASSWORD+"='"+password+"'" ,  null);
         if (c.getCount()>0)
            return true;
         else
            return false;
      }
	 
	/* public String getVehicle() {
			// TODO Auto-generated method stub
			String[] columns = new String[]{KEY_ROWID,KEY_USERNAME,KEY_PASSWORD};
			Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
					              null, null, null, null, null);
			String result = " ";
			
			
			int iRow = c.getColumnIndex(KEY_ROWID);
			int iUserName = c.getColumnIndex(KEY_USERNAME);
			//int iPassword = c.getColumnIndex(KEY_PASSWORD);
			int i=2;
				c.moveToPosition(i);
				result = result + "	" + c.getString(iVehicle_id) + "\n";
				
	 
			return result;
			
		}*/
}


