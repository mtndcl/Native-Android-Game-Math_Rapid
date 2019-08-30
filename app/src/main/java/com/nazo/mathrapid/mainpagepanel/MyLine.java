package com.nazo.mathrapid.mainpagepanel;

import android.graphics.Paint;

/**
 * Created by mockingbird on 6/14/2018.
 */

public class MyLine {






    private Paint paint;
    private  float startx;
    private  float starty;
    private  float stopx;
    private  float stopy;
    private  float maxwitdh;

    MyLine(float x, float y, float   maxwitdh   , float size, int color){

        this.startx=x;
        this.starty=y;
        this.stopx=x;
        this.stopy=y;
        this.maxwitdh=maxwitdh;


        this.paint=new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(size);




    }

    public float getwitdh() {
        return Math.abs(this.stopx-this.startx);
    }
    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getStartx() {
        return startx;
    }

    public void setStartx(float startx) {
        this.startx =  this.startx+startx;
    }

    public float getStarty() {
        return starty;
    }

    public void setStarty(float starty) {
        this.starty = this.starty+ starty;
    }

    public float getStopx() {
        return stopx;
    }

    public void setStopx(float stopx) {
        this.stopx = this.stopx+stopx;
    }

    public float getStopy() {
        return stopy;
    }

    public void setStopy(float stopy) {
        this.stopy =this.stopy+ stopy;
    }

    public float getMaxwitdh() {
        return maxwitdh;
    }


    public void growuphorizantal(float v) {


        this.startx=this.startx-v;
        this.stopx=this.stopx+v;
    }

    public void setbegin(float begin) {
        this.startx = begin;
        this.stopx= begin;
    }

    public void setwithdOK(float withdOK) {
        this.maxwitdh = withdOK;
    }
}
