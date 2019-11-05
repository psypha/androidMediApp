package com.app.o.convert;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mymedicare2.db";
    private static final int DATABASE_VERSION = 2;
    public static final String PATIENT_TABLE_RESULTS = "results";
    public static final String PATIENT_COLUMN_ID = "_id";
    public static final String PATIENT_COLUMN_TEMP = "Temp";
    public static final String PATIENT_COLUMN_BPL = "BPlow";
    public static final String PATIENT_COLUMN_BPH = "BPHigh";
    public static final String PATIENT_COLUMN_HR = "HeartRate";



    public DAO(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + PATIENT_TABLE_RESULTS +
                        "(" + PATIENT_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        PATIENT_COLUMN_TEMP + " INTEGER, " +
                        PATIENT_COLUMN_BPL + " INTEGER, " +
                        PATIENT_COLUMN_BPH + " INTEGER" +
                        PATIENT_COLUMN_HR + " INTEGER)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PATIENT_TABLE_RESULTS);
        onCreate(db);
    }

    public boolean insertPatientResults(Integer Temp, Integer BPLow, Integer BPHigh, Integer HR) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PATIENT_COLUMN_TEMP, Temp);
        contentValues.put(PATIENT_COLUMN_BPL, BPLow);
        contentValues.put(PATIENT_COLUMN_BPH, BPHigh);
        contentValues.put(PATIENT_COLUMN_HR, HR);

        db.insert(PATIENT_TABLE_RESULTS, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PATIENT_TABLE_RESULTS);
        return numRows;
    }


    public List<viewResults> getAllResults() {
        List<viewResults>  listResults = new ArrayList<viewResults>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM results", null);
        if (res.moveToFirst()) {
            do {
                viewResults ViewResults = new viewResults();
                ViewResults.setId(Integer.parseInt(res.getString(0)));
                ViewResults.setFirstName(res.getString(1));
                ViewResults.setLastName(res.getString(2));
                // Adding NameVO to list
                listResults.add(ViewResults);
            } while (res.moveToNext());
        }
        // return NameVO list
        return listResults;
    }
       //System.out.println(res);
       // return res;

}