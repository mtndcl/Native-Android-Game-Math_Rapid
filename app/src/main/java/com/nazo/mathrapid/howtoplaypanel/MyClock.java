package com.nazo.mathrapid.howtoplaypanel;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.nazo.mathrapid.Contant;
import com.nazo.mathrapid.MyData;

/**
 * Created by mockingbird on 6/23/2018.
 */

public class MyClock {



    private  Paint strokepaint;
    private Paint paint;
    private RectF rect;
    private  long  starttime;
    private  int time;
    private  float size;
    private  float x;
    private   float y;

    private  float remaintime;
    MyClock(float  x,float  y ,int color, int  time){



        this.x=x;
        this.y=y;
        this.size= 0;

        this.time= time;
        this.starttime=System.currentTimeMillis();
        this.paint=new Paint();
        this.strokepaint=new Paint();
        this.paint.setColor(color);
        this.strokepaint.setStrokeWidth(size/10);
        this.strokepaint.setStyle(Paint.Style.STROKE);
        strokepaint.setColor(lighter(color,0.8f));
        this.rect=new RectF(x-size,y-size,x+size,y+size);
    }

    public Paint getStrokepaint() {
        return strokepaint;
    }

    public void setStrokepaint(Paint strokepaint) {
        this.strokepaint = strokepaint;
    }

    private int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }
    public RectF getRect() {
        return rect;
    }

    public float getSweepangle(){


       long  remaintime=time*60000-( System.currentTimeMillis() - this.starttime);
        remaintime=  -(360*remaintime)/(time*60000);
    return remaintime;
    }

    public float getSize() {
        return size;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }



    public void setsize(float size) {

        this.size = this.size +size;
        this.strokepaint.setStrokeWidth(this.size/30);
        this.rect.left=x- this.size;
        this.rect.top=y- this.size;
        this.rect.right=x+this.size;
        this.rect.bottom=y+ this.size;

    }

    public void setStartTimeOK(long startTimeOK) {
        this.starttime = startTimeOK;
    }


}
