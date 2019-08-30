package com.nazo.mathrapid.gamepanel;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;



/**
 * Created by mockingbird on 3/1/2018.
 */

public class MulLineText {



    private  StaticLayout layout;
    private  float  textYCoordinate;
    private  float textXCoordinate;

    public  MulLineText(String text,float x,float y,float textsize,int linewidth){




        TextPaint textPaint = new TextPaint();


        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textsize);
        this.layout= new StaticLayout(text, textPaint, linewidth, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);




        this.textYCoordinate = y;

        //text will be drawn from left
        this.textXCoordinate =x;


    }
    private int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }
    public float getTextXCoordinate() {
        return textXCoordinate;
    }

    public float getTextYCoordinate() {
        return textYCoordinate;
    }

    public StaticLayout getLayout() {
        return layout;
    }

}