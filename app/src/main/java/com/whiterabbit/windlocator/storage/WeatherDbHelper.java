/**********************************************************************************************************************************************************************
****** AUTO GENERATED FILE BY ANDROID SQLITE HELPER SCRIPT BY FEDERICO PAOLINELLI. ANY CHANGE WILL BE WIPED OUT IF THE SCRIPT IS PROCESSED AGAIN. *******
**********************************************************************************************************************************************************************/
package com.whiterabbit.windlocator.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.util.Date;

public class WeatherDbHelper {
    private static final String TAG = "Weather";

    private static final String DATABASE_NAME = "Weather.db";
    private static final int DATABASE_VERSION = 1;


    // Variable to hold the database instance
    protected SQLiteDatabase mDb;
    // Context of the application using the database.
    private final Context mContext;
    // Database open/upgrade helper
    private DbHelper mDbHelper;
    
    public WeatherDbHelper(Context context) {
        mContext = context;
        mDbHelper = new DbHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public WeatherDbHelper open() throws SQLException { 
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
                                                     
    public void close() {
        mDb.close();
    }

    public static final String ROW_ID = "_id";

    
    // -------------- NEARBYWEATHER DEFINITIONS ------------
    public static final String NEARBYWEATHER_TABLE = "NearbyWeather";
    
    public static final String NEARBYWEATHER_TIME_COLUMN = "time";
    public static final int NEARBYWEATHER_TIME_COLUMN_POSITION = 1;
    
    public static final String NEARBYWEATHER_WINDSPEED_COLUMN = "windSpeed";
    public static final int NEARBYWEATHER_WINDSPEED_COLUMN_POSITION = 2;
    
    public static final String NEARBYWEATHER_WINDDEGREE_COLUMN = "windDegree";
    public static final int NEARBYWEATHER_WINDDEGREE_COLUMN_POSITION = 3;
    
    public static final String NEARBYWEATHER_TEMPERATURE_COLUMN = "temperature";
    public static final int NEARBYWEATHER_TEMPERATURE_COLUMN_POSITION = 4;
    
    public static final String NEARBYWEATHER_CITYNAME_COLUMN = "cityName";
    public static final int NEARBYWEATHER_CITYNAME_COLUMN_POSITION = 5;
    
    public static final String NEARBYWEATHER_LATITUDE_COLUMN = "latitude";
    public static final int NEARBYWEATHER_LATITUDE_COLUMN_POSITION = 6;
    
    public static final String NEARBYWEATHER_LONGITUDE_COLUMN = "longitude";
    public static final int NEARBYWEATHER_LONGITUDE_COLUMN_POSITION = 7;
    
    public static final String NEARBYWEATHER_WEATHERENUM_COLUMN = "weatherEnum";
    public static final int NEARBYWEATHER_WEATHERENUM_COLUMN_POSITION = 8;
    
    public static final String NEARBYWEATHER_ID_COLUMN = "id";
    public static final int NEARBYWEATHER_ID_COLUMN_POSITION = 9;
    
    
    // -------------- FAVORITEWEATHER DEFINITIONS ------------
    public static final String FAVORITEWEATHER_TABLE = "FavoriteWeather";
    
    public static final String FAVORITEWEATHER_TIME_COLUMN = "time";
    public static final int FAVORITEWEATHER_TIME_COLUMN_POSITION = 1;
    
    public static final String FAVORITEWEATHER_WINDSPEED_COLUMN = "windSpeed";
    public static final int FAVORITEWEATHER_WINDSPEED_COLUMN_POSITION = 2;
    
    public static final String FAVORITEWEATHER_WINDDEGREE_COLUMN = "windDegree";
    public static final int FAVORITEWEATHER_WINDDEGREE_COLUMN_POSITION = 3;
    
    public static final String FAVORITEWEATHER_TEMPERATURE_COLUMN = "temperature";
    public static final int FAVORITEWEATHER_TEMPERATURE_COLUMN_POSITION = 4;
    
    public static final String FAVORITEWEATHER_CITYNAME_COLUMN = "cityName";
    public static final int FAVORITEWEATHER_CITYNAME_COLUMN_POSITION = 5;
    
    public static final String FAVORITEWEATHER_LATITUDE_COLUMN = "latitude";
    public static final int FAVORITEWEATHER_LATITUDE_COLUMN_POSITION = 6;
    
    public static final String FAVORITEWEATHER_LONGITUDE_COLUMN = "longitude";
    public static final int FAVORITEWEATHER_LONGITUDE_COLUMN_POSITION = 7;
    
    public static final String FAVORITEWEATHER_WEATHERENUM_COLUMN = "weatherEnum";
    public static final int FAVORITEWEATHER_WEATHERENUM_COLUMN_POSITION = 8;
    
    public static final String FAVORITEWEATHER_ID_COLUMN = "id";
    public static final int FAVORITEWEATHER_ID_COLUMN_POSITION = 9;
    
    


    // -------- TABLES CREATION ----------

    
    // NearbyWeather CREATION 
    private static final String DATABASE_NEARBYWEATHER_CREATE = "create table " + NEARBYWEATHER_TABLE + " (" +
                                "_id integer primary key autoincrement, " +
                                NEARBYWEATHER_TIME_COLUMN + " integer, " +
                                NEARBYWEATHER_WINDSPEED_COLUMN + " real, " +
                                NEARBYWEATHER_WINDDEGREE_COLUMN + " real, " +
                                NEARBYWEATHER_TEMPERATURE_COLUMN + " real, " +
                                NEARBYWEATHER_CITYNAME_COLUMN + " text, " +
                                NEARBYWEATHER_LATITUDE_COLUMN + " real, " +
                                NEARBYWEATHER_LONGITUDE_COLUMN + " real, " +
                                NEARBYWEATHER_WEATHERENUM_COLUMN + " integer, " +
                                NEARBYWEATHER_ID_COLUMN + " integer" +
                                ")";
    
    // FavoriteWeather CREATION 
    private static final String DATABASE_FAVORITEWEATHER_CREATE = "create table " + FAVORITEWEATHER_TABLE + " (" +
                                "_id integer primary key autoincrement, " +
                                FAVORITEWEATHER_TIME_COLUMN + " integer, " +
                                FAVORITEWEATHER_WINDSPEED_COLUMN + " real, " +
                                FAVORITEWEATHER_WINDDEGREE_COLUMN + " real, " +
                                FAVORITEWEATHER_TEMPERATURE_COLUMN + " real, " +
                                FAVORITEWEATHER_CITYNAME_COLUMN + " text, " +
                                FAVORITEWEATHER_LATITUDE_COLUMN + " real, " +
                                FAVORITEWEATHER_LONGITUDE_COLUMN + " real, " +
                                FAVORITEWEATHER_WEATHERENUM_COLUMN + " integer, " +
                                FAVORITEWEATHER_ID_COLUMN + " integer" +
                                ")";
    

    
    // ----------------NearbyWeather HELPERS -------------------- 
    public long addNearbyWeather (Date time, double windSpeed, double windDegree, double temperature, String cityName, double latitude, double longitude, int weatherEnum, long id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NEARBYWEATHER_TIME_COLUMN, time.getTime());
        contentValues.put(NEARBYWEATHER_WINDSPEED_COLUMN, windSpeed);
        contentValues.put(NEARBYWEATHER_WINDDEGREE_COLUMN, windDegree);
        contentValues.put(NEARBYWEATHER_TEMPERATURE_COLUMN, temperature);
        contentValues.put(NEARBYWEATHER_CITYNAME_COLUMN, cityName);
        contentValues.put(NEARBYWEATHER_LATITUDE_COLUMN, latitude);
        contentValues.put(NEARBYWEATHER_LONGITUDE_COLUMN, longitude);
        contentValues.put(NEARBYWEATHER_WEATHERENUM_COLUMN, weatherEnum);
        contentValues.put(NEARBYWEATHER_ID_COLUMN, id);
        return mDb.insert(NEARBYWEATHER_TABLE, null, contentValues);
    }

    public long updateNearbyWeather (long rowIndex,Date time, double windSpeed, double windDegree, double temperature, String cityName, double latitude, double longitude, int weatherEnum, long id) {
        String where = ROW_ID + " = " + rowIndex;
        ContentValues contentValues = new ContentValues();
        contentValues.put(NEARBYWEATHER_TIME_COLUMN, time.getTime());
        contentValues.put(NEARBYWEATHER_WINDSPEED_COLUMN, windSpeed);
        contentValues.put(NEARBYWEATHER_WINDDEGREE_COLUMN, windDegree);
        contentValues.put(NEARBYWEATHER_TEMPERATURE_COLUMN, temperature);
        contentValues.put(NEARBYWEATHER_CITYNAME_COLUMN, cityName);
        contentValues.put(NEARBYWEATHER_LATITUDE_COLUMN, latitude);
        contentValues.put(NEARBYWEATHER_LONGITUDE_COLUMN, longitude);
        contentValues.put(NEARBYWEATHER_WEATHERENUM_COLUMN, weatherEnum);
        contentValues.put(NEARBYWEATHER_ID_COLUMN, id);
        return mDb.update(NEARBYWEATHER_TABLE, contentValues, where, null);
    }

    public boolean removeNearbyWeather(long rowIndex){
        return mDb.delete(NEARBYWEATHER_TABLE, ROW_ID + " = " + rowIndex, null) > 0;
    }

    public boolean removeAllNearbyWeather(){
        return mDb.delete(NEARBYWEATHER_TABLE, null, null) > 0;
    }

    public Cursor getAllNearbyWeather(){
    	return mDb.query(NEARBYWEATHER_TABLE, new String[] {
                         ROW_ID,
                         NEARBYWEATHER_TIME_COLUMN,
                         NEARBYWEATHER_WINDSPEED_COLUMN,
                         NEARBYWEATHER_WINDDEGREE_COLUMN,
                         NEARBYWEATHER_TEMPERATURE_COLUMN,
                         NEARBYWEATHER_CITYNAME_COLUMN,
                         NEARBYWEATHER_LATITUDE_COLUMN,
                         NEARBYWEATHER_LONGITUDE_COLUMN,
                         NEARBYWEATHER_WEATHERENUM_COLUMN,
                         NEARBYWEATHER_ID_COLUMN
                         }, null, null, null, null, null);
    }

    public Cursor getNearbyWeather(long rowIndex) {
        Cursor res = mDb.query(NEARBYWEATHER_TABLE, new String[] {
                                ROW_ID,
                                NEARBYWEATHER_TIME_COLUMN,
                                NEARBYWEATHER_WINDSPEED_COLUMN,
                                NEARBYWEATHER_WINDDEGREE_COLUMN,
                                NEARBYWEATHER_TEMPERATURE_COLUMN,
                                NEARBYWEATHER_CITYNAME_COLUMN,
                                NEARBYWEATHER_LATITUDE_COLUMN,
                                NEARBYWEATHER_LONGITUDE_COLUMN,
                                NEARBYWEATHER_WEATHERENUM_COLUMN,
                                NEARBYWEATHER_ID_COLUMN
                                }, ROW_ID + " = " + rowIndex, null, null, null, null);

        if(res != null){
            res.moveToFirst();
        }
        return res;
    }
    
    // ----------------FavoriteWeather HELPERS -------------------- 
    public long addFavoriteWeather (Date time, double windSpeed, double windDegree, double temperature, String cityName, double latitude, double longitude, int weatherEnum, long id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FAVORITEWEATHER_TIME_COLUMN, time.getTime());
        contentValues.put(FAVORITEWEATHER_WINDSPEED_COLUMN, windSpeed);
        contentValues.put(FAVORITEWEATHER_WINDDEGREE_COLUMN, windDegree);
        contentValues.put(FAVORITEWEATHER_TEMPERATURE_COLUMN, temperature);
        contentValues.put(FAVORITEWEATHER_CITYNAME_COLUMN, cityName);
        contentValues.put(FAVORITEWEATHER_LATITUDE_COLUMN, latitude);
        contentValues.put(FAVORITEWEATHER_LONGITUDE_COLUMN, longitude);
        contentValues.put(FAVORITEWEATHER_WEATHERENUM_COLUMN, weatherEnum);
        contentValues.put(FAVORITEWEATHER_ID_COLUMN, id);
        return mDb.insert(FAVORITEWEATHER_TABLE, null, contentValues);
    }

    public long updateFavoriteWeather (long rowIndex,Date time, double windSpeed, double windDegree, double temperature, String cityName, double latitude, double longitude, int weatherEnum, long id) {
        String where = ROW_ID + " = " + rowIndex;
        ContentValues contentValues = new ContentValues();
        contentValues.put(FAVORITEWEATHER_TIME_COLUMN, time.getTime());
        contentValues.put(FAVORITEWEATHER_WINDSPEED_COLUMN, windSpeed);
        contentValues.put(FAVORITEWEATHER_WINDDEGREE_COLUMN, windDegree);
        contentValues.put(FAVORITEWEATHER_TEMPERATURE_COLUMN, temperature);
        contentValues.put(FAVORITEWEATHER_CITYNAME_COLUMN, cityName);
        contentValues.put(FAVORITEWEATHER_LATITUDE_COLUMN, latitude);
        contentValues.put(FAVORITEWEATHER_LONGITUDE_COLUMN, longitude);
        contentValues.put(FAVORITEWEATHER_WEATHERENUM_COLUMN, weatherEnum);
        contentValues.put(FAVORITEWEATHER_ID_COLUMN, id);
        return mDb.update(FAVORITEWEATHER_TABLE, contentValues, where, null);
    }

    public boolean removeFavoriteWeather(long rowIndex){
        return mDb.delete(FAVORITEWEATHER_TABLE, ROW_ID + " = " + rowIndex, null) > 0;
    }

    public boolean removeAllFavoriteWeather(){
        return mDb.delete(FAVORITEWEATHER_TABLE, null, null) > 0;
    }

    public Cursor getAllFavoriteWeather(){
    	return mDb.query(FAVORITEWEATHER_TABLE, new String[] {
                         ROW_ID,
                         FAVORITEWEATHER_TIME_COLUMN,
                         FAVORITEWEATHER_WINDSPEED_COLUMN,
                         FAVORITEWEATHER_WINDDEGREE_COLUMN,
                         FAVORITEWEATHER_TEMPERATURE_COLUMN,
                         FAVORITEWEATHER_CITYNAME_COLUMN,
                         FAVORITEWEATHER_LATITUDE_COLUMN,
                         FAVORITEWEATHER_LONGITUDE_COLUMN,
                         FAVORITEWEATHER_WEATHERENUM_COLUMN,
                         FAVORITEWEATHER_ID_COLUMN
                         }, null, null, null, null, null);
    }

    public Cursor getFavoriteWeather(long rowIndex) {
        Cursor res = mDb.query(FAVORITEWEATHER_TABLE, new String[] {
                                ROW_ID,
                                FAVORITEWEATHER_TIME_COLUMN,
                                FAVORITEWEATHER_WINDSPEED_COLUMN,
                                FAVORITEWEATHER_WINDDEGREE_COLUMN,
                                FAVORITEWEATHER_TEMPERATURE_COLUMN,
                                FAVORITEWEATHER_CITYNAME_COLUMN,
                                FAVORITEWEATHER_LATITUDE_COLUMN,
                                FAVORITEWEATHER_LONGITUDE_COLUMN,
                                FAVORITEWEATHER_WEATHERENUM_COLUMN,
                                FAVORITEWEATHER_ID_COLUMN
                                }, ROW_ID + " = " + rowIndex, null, null, null, null);

        if(res != null){
            res.moveToFirst();
        }
        return res;
    }
    

    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one. 
        @Override
        public void onCreate(SQLiteDatabase db) {      
            db.execSQL(DATABASE_NEARBYWEATHER_CREATE);
            db.execSQL(DATABASE_FAVORITEWEATHER_CREATE);
            
        }

        // Called when there is a database version mismatch meaning that the version
        // of the database on disk needs to be upgraded to the current version.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Log the version upgrade.
            Log.w(TAG, "Upgrading from version " + 
                        oldVersion + " to " +
                        newVersion + ", which will destroy all old data");
            
            // Upgrade the existing database to conform to the new version. Multiple 
            // previous versions can be handled by comparing _oldVersion and _newVersion
            // values.

            // The simplest case is to drop the old table and create a new one.
            db.execSQL("DROP TABLE IF EXISTS " + NEARBYWEATHER_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + FAVORITEWEATHER_TABLE + ";");
            
            // Create a new one.
            onCreate(db);
        }
    }
}

