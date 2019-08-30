package com.nazo.mathrapid.mainpagepanel;


        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Typeface;
        import android.text.TextPaint;

/**
 * Created by mockingbird on 2/28/2018.
 */

public class MyButton {




    private  float xcenter;
    private  float ycenter;
    private  float maxX;
    private  float maxY;
    private  String text;
    private  String id;

    /////////
    private Paint paint;
    private TextPaint textpaint;
    private  float left;
    private  float right;
    private  float top;
    private  float  bottom;
    private  float textYlocation;




    public MyButton(float xcenter, float ycenter, float maxX, float maxY, int color,String text, String id) {
        this.xcenter = xcenter;
        this.ycenter = ycenter;
        this.maxX = maxX;
        this.maxY = maxY;
        this.text = text;
        this.id = id;

        this.paint=new Paint();
        this.textpaint=new TextPaint();

        left=xcenter-maxX;
        right=xcenter+maxX;
        top=ycenter-maxY;
        bottom=ycenter+maxY;

        paint.setColor(color);

        textpaint.setColor(color);

        ///textpaint.setTypeface(paint.setTypeface(Typeface.create("Brush Script MT",Typeface.BOLD)));
        textpaint.setTextAlign(Paint.Align.CENTER);
        textYlocation = ((ycenter) - ((textpaint.descent() + textpaint.ascent()) / 2));


        switch (id){
            case  "close":
                textpaint.setTextSize(this.maxX);
                break;
            default:
                textpaint.setTextSize(this.maxX/2);
                break;
        }

    }

    public String getId() {
        return id;
    }

    public float getYcenter() {
        return ycenter;
    }

    public float getBottom() {
        return bottom;
    }

    public float getTop() {
        return top;
    }

    public float getRight() {
        return right;
    }

    public float getLeft() {
        return left;
    }

    public Paint getPaint() {
        return paint;
    }

    public float getMaxY() {
        return maxY;
    }

    public float getMaxX() {
        return maxX;
    }

    public String getText() {
        return text;
    }

    public float getXcenter() {
        return xcenter;
    }
    private int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    public float getTextYlocation() {
        return textYlocation;
    }

    public TextPaint getTextpaint() {
        return textpaint;
    }
}
