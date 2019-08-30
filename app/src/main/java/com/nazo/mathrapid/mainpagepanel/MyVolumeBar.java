package com.nazo.mathrapid.mainpagepanel;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by mockingbird on 2/25/2018.
 */

public class MyVolumeBar {



    private float left;
    private float right;
    private float top;
    private float bottom;
    private  Paint paint;

    private  int color;

    private  float height;
    private  float  width;
    private RectF focuspoint;



    protected MyVolumeBar(float x, float y, float width, float height, int color) {


        this.height=height;
        this.width=width;

        this.focuspoint =new RectF((int)(x-height*2),(int)(y-height*4),(int)(x+height*2),(int)(y+height*4));

        this.color=color;
        this.paint=new Paint();
        paint.setColor(color);


        this.bottom=y+height;
        this.top=y-height;
        this.left=x-width;
        this.right=x+width;


    }


    public float getrealWidth() {
        return width*2;
    }
    public   float  getselectedvolumbar(){

        return   focuspoint.centerX()-left;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public RectF getFocuspoint() {
        return focuspoint;
    }
    public float getLeft() {
        return left;
    }


    public float getRight() {
        return right;
    }

    public float getTop() {
        return top;
    }


    public float getBottom() {
        return bottom;
    }


    public void setPaint(Paint paint) {
        this.paint = paint;
    }







    public Paint getPaint() {
        return paint;
    }


    public void setFocusPoint(float x) {


        focuspoint.right=x+height*2;
        focuspoint.left=x-height*2;


    }
}
