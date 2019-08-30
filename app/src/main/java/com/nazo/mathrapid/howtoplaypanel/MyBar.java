package com.nazo.mathrapid.howtoplaypanel;

/**
 * Created by mockingbird on 6/20/2018.
 */


        import android.graphics.Paint;
        import android.graphics.RectF;

/**
 * Created by mockingbird on 3/9/2018.
 */

public class MyBar {

    private  float right;
    private  float  left;
    private  float  top;
    private  float  bottom;
    private Paint  paint;


    MyBar(float left ,float top ,float right ,float bottom,int color,String id){


        this.right=right;
        this.left=left;
        this.top=top;
        this.bottom=bottom;
        this.paint=new Paint();


        paint.setColor(color);

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

    public   float   getcenterY(){
        return   (bottom-top)/2;
    }




}
