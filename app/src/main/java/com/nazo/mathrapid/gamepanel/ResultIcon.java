package com.nazo.mathrapid.gamepanel;

import android.graphics.Paint;

import com.nazo.mathrapid.Contant;

/**
 * Created by mockingbird on 6/22/2018.
 */

public class ResultIcon {





    private Paint  paint;
    private  Long  start;
    private  String icon;
    private  float size;
    private  float  textYlocation;
    private  float maxsize;

    private  Paint strokepaint;
    ResultIcon(){






        this.maxsize=Contant.SCREEN_WIDHT/3;
        this.size=Contant.SCREEN_WIDHT/20;
        icon="✓";
        this.paint=new Paint();
        this.strokepaint=new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        textYlocation = ((Contant.SCREEN_HEIGHT/2) - ((paint.descent() + paint.ascent()) / 2));
        strokepaint.setTextAlign(Paint.Align.CENTER);
        strokepaint.setStrokeWidth(Contant.SCREEN_WIDHT/255);
        strokepaint.setStyle(Paint.Style.STROKE);
        textYlocation = ((Contant.SCREEN_HEIGHT/2) - ((strokepaint.descent() + strokepaint.ascent()) / 2));
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size  + this.size ;
        paint.setTextSize(this.size);
        strokepaint.setTextSize(this.size);
        textYlocation = ((Contant.SCREEN_HEIGHT/2) - ((paint.descent() + paint.ascent()) / 2));

    }

    public float getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(float maxsize) {
        this.maxsize = maxsize;
    }

    public   float  getX(){
        return Contant.SCREEN_WIDHT/2;
    }
    public  float  gettextYlocation(){
        return textYlocation;
    }

    public Paint getPaint() {


        return paint;
    }

    public Paint getStrokepaint() {
        return strokepaint;
    }

    public void setSizeOK(int sizeOK) {
        this.size = sizeOK;
        paint.setTextSize(this.size);
        strokepaint.setTextSize(this.size);
        textYlocation = ((Contant.SCREEN_HEIGHT/2) - ((paint.descent() + paint.ascent()) / 2));
    }
}
