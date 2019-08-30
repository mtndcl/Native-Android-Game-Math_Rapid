package com.nazo.mathrapid.splashpanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;


import com.nazo.mathrapid.Contant;
import com.nazo.mathrapid.R;

import java.lang.*;
import java.util.ArrayList;

/**
 * Created by mockingbird on 6/9/2018.
 */
public  class   SplashView   extends View {



//////////private MyText(float x, float y,  String text, int mycolor, float size,int  align) {
///myPaint=new MyPaint(Color.rgb(178, 250, 244),Color.rgb(84, 246, 231));



    private  SplashInfo   info;

    private MyPaint  myPaint=new MyPaint(Color.rgb(125, 206, 160),Color.rgb(88, 214, 141));

   private ArrayList<MyText>   myTexts=new ArrayList<>();
    private Process process=new Process();

    public  SplashView(Context context){



        super(context);

        // this.setFocusable(true);
    }
    @Override
    public void  onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (process.getInit()){
            initial();
            process.setInit(false);

        }
       /// canvas.drawColor(Color.WHITE);

        canvas.drawPaint(myPaint.getPaint());


        for (MyText  text:  myTexts){
            canvas.drawText(text.getText(),text.getX(),text.getYlocation(),text.getPaint());

            if (text.getPaint().getAlpha()<250){
                text.getPaint().setAlpha(info.getAlpha());
                info.setAlpha(1);
                invalidate();
            }


        }

    }

    private  void  initial(){



        info=new SplashInfo();
        Float  size=4*Contant.SCREEN_WIDHT/15;
        int  color=Color.argb(0,60,18,69);
        MyText  part1=new MyText(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/3-size/2,"MATH",color,size*4/5,1);
        MyText  part2=new MyText(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/3+size/2,"RAPID",color,size,1);
        myTexts.add(part1);
        myTexts.add(part2);


        new CountDownTimer(5000, 300) {

            public void onTick(long millisUntilFinished) {




                float size=Contant.SCREEN_WIDHT/15;


                switch (info.getCount()){




                    case 0:
                        MyText  one=new MyText(Contant.SCREEN_WIDHT/2-size*3/2,Contant.SCREEN_HEIGHT*3/4,"+",Color.argb(0,60,18,69),size,1);
                        one.getPaint().setAlpha(info.getAlpha());
                        myTexts.add(one);
                        break;
                    case 1:
                        MyText  two=new MyText(Contant.SCREEN_WIDHT/2-size/2,Contant.SCREEN_HEIGHT*3/4,"-",Color.argb(0,60,18,69),size,1);
                        two.getPaint().setAlpha(info.getAlpha());
                        myTexts.add(two);
                        break;
                    case 2:
                        MyText  three=new MyText(Contant.SCREEN_WIDHT/2+size/2,Contant.SCREEN_HEIGHT*3/4,"x",Color.argb(0,60,18,69),size,1);
                        three.getPaint().setAlpha(info.getAlpha());
                        myTexts.add(three);
                        break;
                    case 3:
                        MyText  four=new MyText(Contant.SCREEN_WIDHT/2+size*3/2,Contant.SCREEN_HEIGHT*3/4,"÷",Color.argb(0,60,18,69),size,1);
                        four.getPaint().setAlpha(info.getAlpha());
                        myTexts.add(four);
                        break;


                }
                info.setCount(1);
                // mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {


            }
        }.start();
    }

}