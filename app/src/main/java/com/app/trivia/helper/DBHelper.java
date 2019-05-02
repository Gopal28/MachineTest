package com.app.trivia.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.app.trivia.model.Summary;
import java.util.ArrayList;

public class DBHelper implements CommonValues {

    private String TAG = DBHelper.class.getSimpleName();

    private DataBaseHelper myDBHelper;

    private SQLiteDatabase myDataBase;

    private Context myContext;

    private int DATABASE_VERSION = 1;

    public class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                // If you need to add a column
                if (newVersion > oldVersion) {

                    try {
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

    }

    public DBHelper(Context context) {
        myContext = context;
        myDBHelper = new DataBaseHelper(context);
        myDataBase = myDBHelper.getReadableDatabase();
        open();
    }

    /**
     * Function to make DB as readable and writable
     */
    private void open() {

        try {
            if (myDataBase == null) {
                myDataBase = myDBHelper.getWritableDatabase();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Function to Close the database
     */
    public void close() {

        try {
            Log.d(TAG, "mySQLiteDatabase Closed");

            // ---Closing the database---
            myDBHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Insert Game Details
     */
    public void insertGame(String aUserName, String aFirstAns, String aSecondAns) {

        try {
            Log.e("query", "insertGame");
            ContentValues values = new ContentValues();
            values.put(DATE_TIME, Helper.getCurrentDateTime());
            values.put(USER_NAME, aUserName);
            values.put(QUESTION_ANS1, aFirstAns);
            values.put(QUESTION_ANS2, aSecondAns);
            myDataBase.insert(HISTORY_TABLE_NAME, null, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Get History of game
     */
    public ArrayList<Summary> getGameHistory() {

        ArrayList<Summary> aHistoryList = new ArrayList<Summary>();

        try {

            String aQuery = "SELECT * FROM " + HISTORY_TABLE_NAME;

            Log.e("query", aQuery);

            Cursor aCursor = myDataBase.rawQuery(aQuery, null);

            aCursor.moveToFirst();

            if (aCursor.getCount() > 0) {

                while (!aCursor.isAfterLast()) {

                    Summary aSummaryDetail = new Summary();

                    aSummaryDetail.setDateTime(aCursor.getString(aCursor
                            .getColumnIndex(DATE_TIME)));
                    aSummaryDetail.setUserName(aCursor.getString(aCursor
                            .getColumnIndex(USER_NAME)));
                    aSummaryDetail.setSecondAns(aCursor.getString(aCursor
                            .getColumnIndex(QUESTION_ANS1)));
                    aSummaryDetail.setThirdAns(aCursor.getString(aCursor
                            .getColumnIndex(QUESTION_ANS2)));

                    aHistoryList.add(aSummaryDetail);

                    aCursor.moveToNext();
                }
            }
            aCursor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aHistoryList;
    }
}
