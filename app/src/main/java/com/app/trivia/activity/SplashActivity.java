package com.app.trivia.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.app.trivia.R;
import com.app.trivia.helper.CommonValues;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SplashActivity extends AppCompatActivity implements CommonValues {

    private DBInsert myDBLoadTask = null;

    private String myDBFileName;

    private FragmentActivity myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalsh_activity);
        myContext = SplashActivity.this;
        getSupportActionBar().hide();
        myDBFileName = myContext.getDatabasePath(DATABASE_NAME).toString();
        if (!isDBExist(myDBFileName)) {
            myDBLoadTask = new DBInsert();
            myDBLoadTask.execute();

        } else {

            Handler aHoldScreen = new Handler();
            aHoldScreen.postDelayed(new Runnable() {

                public void run() {

                    callMainScreen();

                }
            }, SPLASH_TIME_OUT);
        }


    }

    private boolean isDBExist(String aDBFileName) {

        boolean aStatus = false;

        SQLiteDatabase aCheck = null;
        try {

            File file = new File(aDBFileName);
            if (file.exists() && !file.isDirectory()) {
                aCheck = SQLiteDatabase.openDatabase(aDBFileName, null,
                        SQLiteDatabase.OPEN_READONLY
                                | SQLiteDatabase.NO_LOCALIZED_COLLATORS);

                aStatus = (aCheck != null) ? true : false;

                if (aStatus)
                    aCheck.close();
            } else {
                aStatus = false;
            }

        } catch (SQLiteException e) {
            e.printStackTrace();

        }
        return aStatus;
    }

    class DBInsert extends AsyncTask<Void, Void, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Long doInBackground(Void... params) {

            constructNewFileFromResources(myDBFileName);
            return null;
        }

        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            callMainScreen();


        }

        public void constructNewFileFromResources(String DBFile) {

            try {
                String packageName = getApplicationContext().getPackageName();

                String appDatabaseDirectory = String.format(
                        "/data/data/%s/databases", packageName);
                (new File(appDatabaseDirectory)).mkdir();
                OutputStream dos = new FileOutputStream(appDatabaseDirectory + "/"
                        + DATABASE_NAME);

                InputStream dis = getResources().openRawResource(
                        DB_RAW_RESOURCES_ID);
                byte[] buffer = new byte[1028];
                while ((dis.read(buffer)) > 0) {
                    dos.write(buffer);
                }
                dos.flush();
                dos.close();
                dis.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    private void callMainScreen() {

        Intent aIntent = null;

        aIntent = new Intent(getApplicationContext(),
                MainActivity.class);

        aIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(aIntent);

        finish();


    }
}
