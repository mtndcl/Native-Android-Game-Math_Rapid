package com.nazo.mathrapid.gamepanel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.nazo.mathrapid.R;

/**
 * Created by mockingbird on 6/19/2018.
 */

public class Gamepanel  extends Activity {











    public   static  View  gamepanelView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

           gamepanelView=new GamepanelView(this);
        setContentView(gamepanelView);

    }
}
