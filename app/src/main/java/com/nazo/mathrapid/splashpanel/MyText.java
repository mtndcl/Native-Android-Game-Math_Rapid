package com.nazo.mathrapid.splashpanel;

/**
 * Created by mockingbird on 2/22/2018.
 */

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.nazo.mathrapid.Contant;


public  class MyText {



    private  float   x;
    private   float  y;
    private   String  text;
    private Paint paint;


    private  float  ytextloaction;
    MyText(float x, float y, String text, int mycolor, float size, int align) {



        this.x = x;
        this.y = y;
        this.text = text;
        this.paint = new Paint();
        this.paint.setTextSize(Contant.SCREEN_HEIGHT/5);

        this.paint.setStrokeWidth(Contant.SCREEN_WIDHT/20);

        /////////////ytextloaction
        ytextloaction=(y - ((paint.descent() + paint.ascent()) / 2));
        ///////////PAINT
       paint.setTypeface(Typeface.create("Brush Script MT",Typeface.BOLD));

        if (align==0){
            paint.setTextAlign(Paint.Align.LEFT);
        }else   if (align==1){
            paint.setTextAlign(Paint.Align.CENTER);
        }
        else{
            paint.setTextAlign(Paint.Align.RIGHT);
        }
        paint.setTextSize(size);
        paint.setColor(mycolor);

    }


    public String getText() {
        return text;
    }



    public float getX() {
        return x;
    }


    public Paint getPaint() {

        return paint;
    }



    private     int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getYlocation() {
        return ytextloaction;
    }

    public void setYlocation(float ylocation) {


        this.y=ylocation;

        ytextloaction=(y - ((paint.descent() + paint.ascent()) / 2));
    }

    public void setYOK(float YOK) {
        this.y = YOK;
        ytextloaction=(y - ((paint.descent() + paint.ascent()) / 2));
    }

    public void setnewline() {
        this.y = y+paint.descent() - paint.ascent();
        ytextloaction=(y - ((paint.descent() + paint.ascent()) / 2));
    }
}
