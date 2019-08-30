package com.nazo.mathrapid.howtoplaypanel;

import android.graphics.Color;
import android.graphics.Paint;

import com.nazo.mathrapid.Contant;

/**
 * Created by mockingbird on 6/23/2018.
 */

public class MyPanel {


    private float xcenter;
    private float ycenter;
    private float width;
    private float height;
    private Paint paint;
    private Paint paintstroke;
    private float maxwidth;
    private float maxheight;
    private String id;
    private float left;
    private float right;
    private float top;
    private float bottom;


    public MyPanel(float xcenter, float ycenter, int color, float maxwidth, float maxheight, String id) {


        this.xcenter = xcenter;
        this.ycenter = ycenter;
        this.width = 0;
        this.height = 0;
        this.paint = new Paint();
        this.paintstroke = new Paint();
        this.left = xcenter;
        this.right = xcenter;
        this.top = ycenter;
        this.bottom = ycenter;
        this.maxwidth = maxwidth;
        this.maxheight = maxheight;
        this.id = id;

        paint.setColor(color);
        paint.setAlpha(150);
        paintstroke.setColor(color);
        paintstroke.setStrokeWidth(Contant.SCREEN_WIDHT/200);
        paintstroke.setStyle(Paint.Style.STROKE);

        //paintstroke.setAlpha(150);
        //paintstroke.setStrokeWidth(Contant.SCREEN_WIDHT/200);
       // paintstroke.setColor(lighter(color,1.f));




        //paintstroke.setColor(lighter(color, 0.5f));
        //paintstroke.setStyle(Paint.Style.STROKE);
    }


    public Paint getPaintstroke() {
        return paintstroke;
    }

    public void setWitdh(float witdh) {
        this.width = this.width + witdh;
        this.right = xcenter + this.width;
        this.left = xcenter - this.width;


    }

    public void setHeight(float height) {
        this.height = this.height + height;


        this.top = ycenter - this.height;
        this.bottom = ycenter + this.height;


    }

    public float getMaxheight() {
        return maxheight;
    }

    public float getMaxwidth() {
        return maxwidth;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Paint getPaint() {
        return paint;
    }

    public float getRight() {

        return right;
    }

    public float getLeft() {

        return left;
    }

    public float getTop() {

        return top;
    }

    public float getBottom() {

        return bottom;
    }

    public float getYcenter() {

        return ycenter;
    }


    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r, 255),
                Math.min(g, 255),
                Math.min(b, 255));
    }

    private int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }


    public void reborn() {

        this.width = 0;
        this.height = 0;
    }

    public void setMaxHeightOK(float heightOK) {
        this.maxheight = heightOK;
    }


    public void setMaxWitdhOK(float maxWitdhOK) {
        this.maxwidth = maxWitdhOK;
    }

    public void setCenterXandY(float v, float v1) {

        this.xcenter=v;
        this.ycenter=v1;
    }
}