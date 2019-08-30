package com.nazo.mathrapid.splashpanel;

/**
 * Created by mockingbird on 6/9/2018.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.nazo.mathrapid.Contant;
import com.nazo.mathrapid.mainpagepanel.MainPage;


public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {





        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Contant.SCREEN_WIDHT=dm.widthPixels;
        Contant.SCREEN_HEIGHT=dm.heightPixels;

        Contant.SPEED= (float) (Math.round(((Contant.SCREEN_HEIGHT * ((double)5))/1000)*1000.0)/1000.0);

        super.onCreate(savedInstanceState);
        setContentView(new  SplashView(this));





        startHeavyProcessing();

    }

    private void startHeavyProcessing(){
        new LongOperation().execute("");
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //some heavy processing resulting in a Data String
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "whatever result you have";
        }

        @Override
        protected void onPostExecute(String result) {
            Intent i = new Intent(Splash.this, MainPage.class);
            startActivity(i);
            finish();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}

