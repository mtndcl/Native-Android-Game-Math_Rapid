package com.nazo.mathrapid.mainpagepanel;


        import android.graphics.Paint;
        import android.text.Layout;
        import android.text.StaticLayout;
        import android.text.TextPaint;



/**
 * Created by mockingbird on 3/1/2018.
 */

public class MyMulText {



    private  StaticLayout layout;
    private  float  textYCoordinate;
    private  float textXCoordinate;

    public  MyMulText(String text,float x,float y,float textsize,int linewidth,int textcolor){




        TextPaint textPaint = new TextPaint();

        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textsize);
        textPaint.setColor(textcolor);
        this.layout= new StaticLayout(text, textPaint, linewidth, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);


        this.textYCoordinate = y;

        //text will be drawn from left
        this.textXCoordinate =x;


    }

    public float getTextXCoordinate() {
        return textXCoordinate;
    }

    public float getTextYCoordinate() {
        return textYCoordinate;
    }

    public StaticLayout getLayout() {
        return layout;
    }

    public void setTextYCoordinateOK(float textYCoordinate) {
        this.textYCoordinate = textYCoordinate;
    }
}
