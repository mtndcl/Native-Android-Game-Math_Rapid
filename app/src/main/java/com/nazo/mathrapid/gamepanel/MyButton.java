package com.nazo.mathrapid.gamepanel;

/**
 * Created by mockingbird on 6/20/2018.
 */

import android.graphics.Paint;



        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Typeface;


/**
 * Created by mockingbird on 3/10/2018.
 */
public class MyButton {



    private  float  left;
    private  float right;
    private  float  top;
    private  float  bottom;
    private Paint paint;
    private  float size;
    private  float xcenter;
    private  float ycenter;
    private  String text;
    private  float textYlocation;
    private  Paint  textpaint;
    private  int color;


    private  String id;
    MyButton(float  xcenter,float ycenter,float size ,int color, String text,String  id){




        this.color=color;
        this.id=id;
        this.xcenter=xcenter;
        this.ycenter=ycenter;
        this.size=size;
        this.right=xcenter;
        this.left=xcenter;
        this.top=ycenter;
        this.bottom=ycenter;
        this.text=text;
        this.paint=new Paint();

        paint.clearShadowLayer();
        paint.setStrokeWidth(size);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        this.textpaint=new Paint();

        textpaint.setTextAlign(Paint.Align.CENTER);
        textpaint.setColor(lighter(color,0.8f));
        textpaint.setTextSize(this.size * 2 / this.text.length());
        textpaint.setTypeface(paint.setTypeface(Typeface.create("Brush Script MT",Typeface.BOLD)));

        textYlocation = ((ycenter) - ((textpaint.descent() + textpaint.ascent()) / 2));
        paint.setColor(color);


    }


    public String getId() {
        return id;
    }

    public Paint getTextpaint() {
        return textpaint;
    }

    public float getTextYlocation() {
        return textYlocation;
    }

    public String getText() {
        return text;
    }


    public void setXcenterOK(float xcenter) {
        this.xcenter =  xcenter;
    }

    public void setYcenterOK(float ycenter) {
        this.ycenter = ycenter;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getBottom() {
        return bottom;
    }

    public float getTop() {
        return top;
    }

    public Paint getPaint() {
        return paint;
    }


    public float getSize() {
        return size;
    }


    public float getXcenter() {
        return xcenter;
    }

    public void setsize(float size) {

        this.size=size+this.size;
        this.right=xcenter+this.size;
        this.left=xcenter-this.size;
        this.top=ycenter-this.size;
        this.bottom=ycenter+this.size;
        paint.setStrokeWidth(this.size/10);
        textpaint.setTextSize(this.size * 2 / this.text.length());
        this.textYlocation = ((ycenter) - ((textpaint.descent() + textpaint.ascent()) / 2));
    }


    private int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    public float getYcenter() {
        return ycenter;
    }

    public void setsizeOK(int sizeOK) {
        this.size = sizeOK;
    }

    public void setalpha(int alpha) {

        color =Color.argb(alpha, Color.red(color),  Color.green(color),  Color.blue(color));
        paint.setColor(color);
    }

    public int getColor() {
        return color;
    }
}
