package com.nazo.mathrapid.howtoplaypanel;


import android.app.Activity;
import android.os.Bundle;

public class Howtoplay  extends Activity {














    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(new GamepanelView(this));

    }



}
