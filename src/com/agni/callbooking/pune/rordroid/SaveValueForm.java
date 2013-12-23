package com.agni.callbooking.pune.rordroid;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SaveValueForm {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_PHONENO = "phoneno";
	public static final String KEY_FIRSTNAME = "firstname";
	public static final String KEY_LASTNAME = "lastname";
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_PICKUP = "pickup";
	public static final String KEY_DROPLOC = "droploc";
	public static final String KEY_PASCOUNT = "passenger_count";
	
	
	private static final String DATABASE_NAME = "RordroidForm";
	static final String DATABASE_TABLE = "form";
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
					 KEY_PHONENO + " TEXT NOT NULL, " +
					 KEY_FIRSTNAME + " TEXT NOT NULL, " +
					 KEY_LASTNAME + " TEXT NOT NULL, " +
					 KEY_ADDRESS + " TEXT NOT NULL, " +
					 KEY_PICKUP + " TEXT NOT NULL, " +
					 KEY_DROPLOC + " TEXT NOT NULL, " +
					 KEY_PASCOUNT + " TEXT NOT NULL);"
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
			onCreate(db);
			
		}
		
	}
	
	public SaveValueForm(Context c){
		ourContext = c;
	}
	
	public SaveValueForm open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourdatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String phoneno, String firstname, String lastname,
			String address,String pickup,String drop, int passcount) {
		// TODO Auto-generated method stub
	ContentValues cv = new ContentValues();
	cv.put(KEY_PHONENO, phoneno);
	cv.put(KEY_FIRSTNAME,firstname);
	cv.put(KEY_LASTNAME,lastname);
	cv.put(KEY_ADDRESS,address);
	cv.put(KEY_PICKUP,pickup);
	cv.put(KEY_DROPLOC,drop);
	cv.put(KEY_PASCOUNT,passcount);
	return ourdatabase.insert(DATABASE_TABLE, null, cv);
	
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PHONENO,KEY_FIRSTNAME,KEY_LASTNAME,
				KEY_ADDRESS,KEY_PICKUP,KEY_DROPLOC,KEY_PASCOUNT};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				              null, null, null, null, null);
		String result = " ";
		
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iPhoneno = c.getColumnIndex(KEY_PHONENO);
		int iFirstname = c.getColumnIndex(KEY_FIRSTNAME);
		int iLastname = c.getColumnIndex(KEY_LASTNAME);
		int iAddress = c.getColumnIndex(KEY_ADDRESS);
		int iPickup = c.getColumnIndex(KEY_PICKUP);
		int iDrop = c.getColumnIndex(KEY_DROPLOC);
		int iPasscount = c.getColumnIndex(KEY_PASCOUNT);
		
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + "		"+ c.getString(iPhoneno) + " 		" + c.getString(iFirstname) +
					" 		" + c.getString(iLastname) +" 		" + c.getString(iAddress) +" 		" + c.getString(iPickup) +
					" 		" + c.getString(iDrop) +" 			" + c.getString(iPasscount) +" 		" +"\n";
				
		}
		return result;
	}

	public String getPhoneno() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PHONENO,KEY_FIRSTNAME,KEY_LASTNAME,
				KEY_ADDRESS,KEY_PICKUP,KEY_DROPLOC,KEY_PASCOUNT};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				             null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String phoneno = c.getString(1);
			return phoneno;
		}
		return null;
	}

	
	public String getFirstname() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PHONENO,KEY_FIRSTNAME,KEY_LASTNAME,
				KEY_ADDRESS,KEY_PICKUP,KEY_DROPLOC,KEY_PASCOUNT};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String Firstname = c.getString(2);
			return Firstname;
		}
		return null;
	}
	
	public String getLastname() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PHONENO,KEY_FIRSTNAME,KEY_LASTNAME,
				KEY_ADDRESS,KEY_PICKUP,KEY_DROPLOC,KEY_PASCOUNT};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String Lastname = c.getString(3);
			return Lastname;
		}
		return null;
	}
	
	public String getAddress() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PHONENO,KEY_FIRSTNAME,KEY_LASTNAME,
				KEY_ADDRESS,KEY_PICKUP,KEY_DROPLOC,KEY_PASCOUNT};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String Address = c.getString(4);
			return Address;
		}
		return null;
	}
	
	public String getPickup() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PHONENO,KEY_FIRSTNAME,KEY_LASTNAME,
				KEY_ADDRESS,KEY_PICKUP,KEY_DROPLOC,KEY_PASCOUNT};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String Pickup = c.getString(5);
			return Pickup;
		}
		return null;
	}
	
	public String getDrop() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PHONENO,KEY_FIRSTNAME,KEY_LASTNAME,
				KEY_ADDRESS,KEY_PICKUP,KEY_DROPLOC,KEY_PASCOUNT};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String Drop = c.getString(6);
			return Drop;
		}
		return null;
	}
	
	public String getPassCount() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PHONENO,KEY_FIRSTNAME,KEY_LASTNAME,
				KEY_ADDRESS,KEY_PICKUP,KEY_DROPLOC,KEY_PASCOUNT};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String passcount = c.getString(7);
			return passcount;
		}
		return null;
	}
	

	/*public String getClient() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_PHONENO,KEY_FIRSTNAME,KEY_LASTNAME,
				KEY_ADDRESS,KEY_PICKUP,KEY_DROPLOC,KEY_PASCOUNT};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String client = c.getString(8);
			return client;
		}
		return null;
	}*/
	
	

	public void UpdateEntry(Long lRow,String phoneno, String firstname, String lastname,
			String address,String pickup,String drop, int passcount, String client) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_PHONENO, phoneno);
		cvUpdate.put(KEY_FIRSTNAME, firstname);
		cvUpdate.put(KEY_LASTNAME, lastname);
		cvUpdate.put(KEY_ADDRESS, address);
		cvUpdate.put(KEY_PICKUP,pickup);
		cvUpdate.put(KEY_DROPLOC, drop);
		cvUpdate.put(KEY_PASCOUNT, passcount);	
		ourdatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
		
	}

	public void deleteEntry(Long lRow1) throws SQLException {
		// TODO Auto-generated method stub
		ourdatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
		
	}
	
	
	
}


