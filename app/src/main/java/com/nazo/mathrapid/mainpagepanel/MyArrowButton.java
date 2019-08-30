package com.nazo.mathrapid.mainpagepanel;

/**
 * Created by mockingbird on 2/24/2018.
 */

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by mockingbird on 2/24/2018.
 */

public class MyArrowButton {


    private  float  y;
    private Path path;
    private float radius;
    private Paint paint;
    private  float fakecenterX;

    private String id;
    private  int color;


    public MyArrowButton(float x, float y, float radius, int color, String id) {



        this.y=y;

        this.radius = radius;
        this.path = new Path();
        this.paint = new Paint();
        this.id = id;
        this.fakecenterX=x-radius/2;
        this.color=color;
        paint.setColor(color);





        switch (id.substring(0, 1)){

            case "l":
                path.moveTo(x - radius, y);
                path.lineTo(x, y - radius / 2);////2
                path.lineTo(x, y + radius / 2);////2
                path.moveTo(x - radius, y);
                break;
            case "r":

                path.moveTo(x + radius, y);
                path.lineTo(x, y + radius / 2);////
                path.lineTo(x, y - radius / 2);////2
                path.moveTo(x + radius, y);
                break;

            default:
                break;

        }


    }

    public Paint getPaint() {
        return paint;
    }

    public String getId() {
        return id;
    }

    public Path getPath() {
        return path;
    }

    public   float  getfakecenterX(){

        return   fakecenterX;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    public void setAplha(int aplha) {


        this.paint.setAlpha(aplha);
    }
}