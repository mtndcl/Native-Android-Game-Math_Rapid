package com.nazo.mathrapid.mainpagepanel;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by mockingbird on 6/9/2018.
 */

public class MainPage   extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(new  MainPageView(this));


    }


}
