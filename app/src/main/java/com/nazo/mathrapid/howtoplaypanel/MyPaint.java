package com.nazo.mathrapid.howtoplaypanel;

import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.nazo.mathrapid.Contant;

/**
 * Created by mockingbird on 6/20/2018.
 */


public class MyPaint {


    private Paint paint;



    public MyPaint( int initcolor, int secondcolor) {


        this.paint = new Paint();
       // Shader shader = new LinearGradient(Contant.SCREEN_WIDHT/2,0,Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT, initcolor,secondcolor, Shader.TileMode.REPEAT);
       // paint.setShader(shader);
        paint.setColor(initcolor);
    }

    public Paint getPaint() {
        return paint;
    }
}