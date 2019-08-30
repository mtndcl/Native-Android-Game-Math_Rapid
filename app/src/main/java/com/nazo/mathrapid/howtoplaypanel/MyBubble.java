package com.nazo.mathrapid.howtoplaypanel;

/**
 * Created by mockingbird on 6/20/2018.
 */

        import android.graphics.Color;
        import android.graphics.Paint;

        import com.nazo.mathrapid.Contant;

/**
 * Created by mockingbird on 2/25/2018.
 */

public class MyBubble {




    private  String  text;
    private  float  left;
    private  float right;
    private  float  top;
    private  float  bottom;
    private  Paint  paint;

    private  int color;
    private  float   xcenter;
    private  Paint textpaint;
    private  Paint strokepaint;
    private  float textYlocation;
    private  float  ycenter;
    private  float  size;
    private  String  id;
    private  int  locationindex;

    public MyBubble(float xcenter, float ycenter, int color, String text, String id,int locationindex) {


        this.ycenter=ycenter;
        this.xcenter=xcenter;
        this.text=text;
        this.left=xcenter;
        this.right=xcenter;
        this.locationindex=locationindex;
        this.top=ycenter;
        this.bottom=ycenter;
        this.color=color;
        this.paint=new Paint();
        this.textpaint=new Paint();
        this.strokepaint=new Paint();
        textpaint.setColor(lighter(color,1.0f));
        textpaint.setTextSize((10/this.text.length())*4/5);
        textpaint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(color);
        this.id=id;
        textYlocation = ((ycenter) - ((textpaint.descent() + textpaint.ascent()) / 2));
        strokepaint.setColor(lighter(color,1.0f));
        strokepaint.setStrokeWidth(Contant.SCREEN_WIDHT*2/250);
        strokepaint.setStyle(Paint.Style.STROKE);









    }


    public int getLocationindex() {
        return locationindex;
    }

    public void setSize(float size) {
        this.size=this.size+size;
        this.left=xcenter-this.size/2;
        this.right=xcenter+this.size/2;
        this.top=ycenter-this.size/2;
        this.bottom=ycenter+this.size/2;
        textpaint.setTextSize((this.size/this.text.length())*4/5);
        textYlocation = ((ycenter) - ((textpaint.descent() + textpaint.ascent()) / 2));
    }
    public void setSizeOK(float size) {
        this.size = size;
        this.left=xcenter;
        this.right=xcenter;
        this.top=ycenter;
        this.bottom=ycenter;

    }



    public float getSize() {
        return size;
    }

    public Paint getStrokepaint() {
        return strokepaint;
    }

    public float getYcenter() {
        return ycenter;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getId() {
        return id;
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

    public Paint getPaint() {
        return paint;
    }

    public String getText() {
        return text;
    }

    public float getXcenter() {
        return xcenter;
    }

    public Paint getTextpaint() {
        return textpaint;
    }

    public float getTextYlocation() {
        return textYlocation;
    }

    private int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }




    public void resetbubble() {
        this.size=0;

        ///textpaint.setTextSize((this.size/this.text.length())*4/5);
        //textYlocation = ((ycenter) - ((textpaint.descent() + textpaint.ascent()) / 2));
    }

    public int getColor() {
        return this.color;
    }

    public void setXOK(float XOK) {



        this.xcenter = XOK;
    }

    public void setXOKandYOK(float v, float v1) {
        this.xcenter=v;
        this.ycenter=v1;
    }

    public void setText(String text) {
        this.text = text;
    }


}
