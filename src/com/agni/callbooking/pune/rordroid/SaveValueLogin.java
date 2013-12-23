package com.agni.callbooking.pune.rordroid;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SaveValueLogin {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	
	private static final String DATABASE_NAME = "RordroidLogin";
	static final String DATABASE_TABLE = "login";
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
					KEY_USERNAME + " TEXT NOT NULL, " +
					KEY_PASSWORD + " TEXT NOT NULL);"
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
			onCreate(db);
			
		}
		
	}
	
	public SaveValueLogin(Context c){
		ourContext = c;
	}
	
	public SaveValueLogin open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourdatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String username, String password) {
		// TODO Auto-generated method stub
	ContentValues cv = new ContentValues();
	cv.put(KEY_USERNAME, username);
	cv.put(KEY_PASSWORD, password);
	return ourdatabase.insert(DATABASE_TABLE, null, cv);
	
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_USERNAME,KEY_PASSWORD};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				              null, null, null, null, null);
		String result = " ";
		
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iUserName = c.getColumnIndex(KEY_USERNAME);
		int iPassword = c.getColumnIndex(KEY_PASSWORD);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + "								"+ c.getString(iUserName) + "						   					" + c.getString(iPassword) + "\n";
				
		}
		return result;
	}

	public String getUsername() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_USERNAME,KEY_PASSWORD};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				             null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String username = c.getString(1);
			return username;
		}
		return null;
	}

	
	public String getPassword() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_USERNAME,KEY_PASSWORD};
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns, 
				            null, null, null, null, null);
		if(c != null){
			c.moveToLast();
			String password = c.getString(2);
			return password;
		}
		return null;
	}

	public void UpdateEntry(Long lRow,String mVehicleId, String mUserName, String mPassword) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_USERNAME, mUserName);
		cvUpdate.put(KEY_PASSWORD, mPassword);
		ourdatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
		
	}

	public void deleteEntry(Long lRow1) throws SQLException {
		// TODO Auto-generated method stub
		ourdatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
		
	}
	
	public boolean Login(String username,String password)
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


