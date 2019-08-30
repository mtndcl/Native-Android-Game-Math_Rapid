package com.nazo.mathrapid.mainpagepanel;

/**
 * Created by mockingbird on 6/9/2018.
 */



/**
 * Created by mockingbird on 2/22/2018.
 */


import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.Typeface;

/**
 * Created by mockingbird on 2/21/2018.
 */

public class Hexagon {


    private  float x;
    private  float y;
    private Path path;
    private float radius;
    private Paint paint;
    private  String text;
    private  Paint  textpaint;
    private  float textYlocation;
    private  String id;

    public Hexagon(float x,float y, float radius ,int mycolor,String text,String id) {


        this.x=x;

        this.path = new Path();
        this.radius = radius;
        this.paint=new Paint();
        this.text=text;
        this.y=y;



        this.text=text;
        this.textpaint=new Paint();

        this.id=id;

        float triangleHeight = (float) (Math.sqrt(3) * radius / 2);





        path.moveTo(x - radius, y);
        path.lineTo(x - radius / 2, y - triangleHeight);////2
        path.lineTo(x + radius / 2, y - triangleHeight);////3
        path.lineTo(x + radius, y);///4
        path.lineTo(x + radius / 2, y + triangleHeight);/////5
        path.lineTo(x - radius / 2, y + triangleHeight);/////6
        path.lineTo(x - radius, y);



        paint.setColor(mycolor);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(radius / 10);


        textpaint.setStrokeWidth(this.radius / 3);

        textpaint.setTypeface(paint.setTypeface(Typeface.create("Brush Script MT",Typeface.BOLD)));


        // textpaint.setTextSize(this.radius * 3 / this.text.length());

        textpaint.setTextAlign(Paint.Align.CENTER);


        textpaint.setColor(mycolor);

        //textpaint.setColor(lighter(mycolor,0.5f));
        textpaint.setTextSize(this.radius * 2 / this.text.length());
      // textpaint.setTypeface(paint.setTypeface(Typeface.create("Brush Script MT",Typeface.BOLD)));
        textYlocation = ((y) - ((textpaint.descent() + textpaint.ascent()) / 2));




    }


    public void textchanged() {

        textpaint.setTextSize(this.radius * 2 / this.text.length());
        textYlocation = ((y) - ((textpaint.descent() + textpaint.ascent()) / 2));
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Paint getPaint() {

        return paint;
    }

    public Path getPath() {
        return path;
    }



    public String getText() {
        return text;
    }

    public float getX() {
        return x;
    }

    public  float   getYtextlocation(){


        return     textYlocation;
    }

    public Paint getTextpaint() {
        return textpaint;
    }

    public String getId() {
        return id;
    }



    public float getY() {
        return y;
    }


    private int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
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
        textpaint.setTextSize(this.radius * 2 / this.text.length());
        // textpaint.setTypeface(paint.setTypeface(Typeface.create("Brush Script MT",Typeface.BOLD)));
        textYlocation = ((y) - ((textpaint.descent() + textpaint.ascent()) / 2));
    }


}