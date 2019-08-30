package com.nazo.mathrapid;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.content.ContextCompat;

/**
 * Created by mockingbird on 6/25/2018.
 */
public class MyPhotos {



    private Bitmap photo;
    private  float   x;
    private  float y;
    private Paint paint;
    private  String id;


    public MyPhotos(Bitmap photo, float x, float y, int  color,String id) {
        this.photo = photo;
        this.x = x;


        this.id=id;
        this.y = y;
        this.paint = new Paint();
        ColorFilter filter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);


        paint.setColorFilter(filter);


    }

    public Bitmap getPhoto() {
        return photo;
    }

    public String getId() {
        return id;
    }



    public float getX() {
        return x;
    }



    public Paint getPaint() {
        return paint;
    }

    public   float  getBottom(){

        return this.y+photo.getHeight();
    }
    public   float  getRight(){

        return this.x+photo.getWidth();
    }
    public   float  getLeft(){

        return x;
    }
    public   float  getTop(){

        return this.y;
    }

    public float getY() {
        return y;
    }
}
