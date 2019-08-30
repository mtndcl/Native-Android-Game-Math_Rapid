package com.nazo.mathrapid.mainpagepanel;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nazo.mathrapid.Contant;
import com.nazo.mathrapid.MyData;
import com.nazo.mathrapid.MyPhotos;
import com.nazo.mathrapid.R;
import com.nazo.mathrapid.aboutpanel.About;
import com.nazo.mathrapid.gamepanel.Gamepanel;
import com.nazo.mathrapid.howtoplaypanel.Howtoplay;

import java.lang.*;
import java.util.ArrayList;
import java.util.Locale;


public class MainPageView  extends View {















    private MyPhotos  sharephoto;
    private  MyPanel   myPanel;
    private MyPaint  myPaint;
    private   MyText  maintext;
    private ArrayList<Hexagon> myHexagons;
    private ArrayList<MyButton> myButtons;
    private ArrayList<MyText> myTexts;
    private MyText howtoplay;
    private ArrayList<MyVolumeBar> myVolumeBars;
    private ArrayList<MyArrowButton> myArrowButtons;
    private ArrayList<MyLine> myLines;
    private Process process  =new Process();
    private  ArrayList<String>  language;
    private   MyMulText  mulText;

    public  MainPageView(Context context){
        super(context);
    }
    @Override
     public    boolean   onTouchEvent(MotionEvent event){


        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()) {


            case MotionEvent.ACTION_DOWN:


                if (process.getMission()) {

                    switch (myPanel.getId()){

                        case  "rateuspanel":
                            RateusPanelProcess(x,y);

                            break;
                        case  "settigspanel":
                            settigsPanelProcess(x,y);

                            break;
                        case  "modepanel":
                            modePanelProcess(x,y);

                            break;
                    }
                }else{
                    for (Hexagon  gon: myHexagons){

                        if (isClickedHex(x,y,gon)){

                            switch (gon.getId()){
                                case "about":

                                   gotoAboutpanel();

                                    break;

                                case "settings":

                                    clickonsettingsbutton();

                                    break;
                                case "mode":

                                    clickonmodebutton();

                                    break;
                                case "play":

                                    gotogamepanel();

                                    break;
                            }
                            break;
                        }
                    }
                    
                    if (isharebuttonclicked(x,y)){
                        shareitis();
                    }
                    if (isTextclicked(x,y,howtoplay)){
                        Intent intent = new Intent(getContext(), Howtoplay.class);
                        getContext().startActivity(intent);
                    }
                }

            case MotionEvent.ACTION_MOVE:
                if (process.getMission()){


                    switch (myPanel.getId()){

                        case "settigspanel":

                            setvolume(x,y);
                            break;
                        default:
                            break;
                    }

                }

                return  true;
        }


        return   true;
    }
    private boolean isTextclicked(float x, float y, MyText text) {

       return Math.abs(text.getY()-y)<text.getBounds().height()  &&  Math.abs(text.getX()-x)<text.getBounds().width() ;
    }
    private boolean isharebuttonclicked(float x, float y) {



        return   x>sharephoto.getLeft()  &&   x  < sharephoto.getRight()  &&   y>sharephoto.getTop()  &&  y<sharephoto.getBottom();
    }
    public void shareitis() {


        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = getContext().getString(R.string.app_link);
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    private void  gotogamepanel() {
        Intent intent = new Intent(getContext(), Gamepanel.class);
        getContext().startActivity(intent);
    }
    private void modePanelProcess(float x, float y) {

        for (MyButton   button :  myButtons){

            if (isClickedButton(x,y,button)){
                switch (button.getId()){
                    case "close":
                        deletepanelinfo();
                        process.setMission(false);
                        break;
                    case "normal":

                        MyData.setFloatData("linecenterx",Contant.SCREEN_WIDHT*3/8,getContext());
                        MyData.setStringData("mode","normal",getContext());

                        for (MyLine   line :  myLines){
                            line.setbegin(Contant.SCREEN_WIDHT*3/8);
                            line.setwithdOK(Contant.SCREEN_WIDHT/8);
                        }
                        break;
                    case "advanced":
                        MyData.setStringData("mode","advanced",getContext());
                        MyData.setFloatData("linecenterx",Contant.SCREEN_WIDHT*5/8,getContext());
                        for (MyLine   line :  myLines){
                            line.setbegin(Contant.SCREEN_WIDHT*5/8);
                            line.setwithdOK(Contant.SCREEN_WIDHT/6);
                        }
                        break;
                }
            }
        }

        for (MyArrowButton   arrow: myArrowButtons){
            if (isClickedArrowButton(x,y,arrow)){
                int time=MyData.getInttData("time",2,getContext());
                switch (arrow.getId()){

                    case  "lefttime":



                            if (time>1) {
                                decreasetime();
                                for (MyArrowButton  arro:  myArrowButtons) {
                                    arro.getPaint().setAlpha(250);
                                }
                            }else{
                                arrow.getPaint().setAlpha(100);
                            }

                        break;
                    case  "righttime":

                            if (time<5){
                                incereasetime();
                                for (MyArrowButton  arro:  myArrowButtons) {
                                    arro.getPaint().setAlpha(250);
                                }
                            }else{
                                arrow.getPaint().setAlpha(100);
                            }



                        break;
                }
            }
        }
    }
    private void incereasetime() {

        for (MyText   text:  myTexts){

            if (text.getId().equals("time")){

                int time=MyData.getInttData("time",2,getContext());
                MyData.setIntData("time",time+1,getContext());
                text.setText(String.valueOf(time+1)+"  "+getContext().getString(R.string.minute_text));

                break;
            }
        }
    }
    private void decreasetime() {


        for (MyText   text:  myTexts){

            if (text.getId().equals("time")){

                int time=MyData.getInttData("time",2,getContext());
                MyData.setIntData("time",time-1,getContext());
                text.setText(String.valueOf(time-1)+"  "+getContext().getString(R.string.minute_text));
                break;
            }
        }
    }
    private boolean isClickedArrowButton(float x, float y, MyArrowButton myArrowButton) {


        return   Math.sqrt(Math.pow(x-myArrowButton.getfakecenterX(),2)+Math.pow(y-myArrowButton.getY(),2))<myArrowButton.getRadius();
    }
    private void settigsPanelProcess(Float x,Float y) {


        for (MyButton   button :  myButtons){

            if (isClickedButton(x,y,button)){
                switch (button.getId()){
                    case "close":
                        deletepanelinfo();
                        process.setMission(false);
                        break;
                }
            }
        }

        for (MyArrowButton   arrowButton :  myArrowButtons){

            if (isClickedArrowButton(x,y,arrowButton)){


                int  index=MyData.getInttData("index",0,getContext());
                switch (arrowButton.getId()){
                    case "left":

                        if (index>0){

                            index--;
                            SetLocale(language.get(index));
                            MyData.setIntData("index",index,getContext());
                            for (MyArrowButton  a: myArrowButtons){
                                a.getPaint().setAlpha(250);
                            }

                        }else {
                            arrowButton.getPaint().setAlpha(100);
                        }

                        break;
                    case "right":

                       if (index<language.size()-1){
                           index++;
                           SetLocale(language.get(index));
                           MyData.setIntData("index",index,getContext());
                           for (MyArrowButton  a: myArrowButtons){
                               a.getPaint().setAlpha(250);
                           }

                       }else{
                           arrowButton.getPaint().setAlpha(100);
                       }

                        break;
                }
                updatetext();
                break;
            }
        }
    }
    private void updatetext() {




        howtoplay.setText(getContext().getString(R.string.how_play_text));
        for (MyText text : myTexts){
            switch (text.getId()){
                case "volumetext":
                    text.setText(getContext().getString(R.string.volume_text));
                    break;
                case "languagetext":
                    text.setText(getContext().getString(R.string.language_text));
                    break;
                case "language":
                    text.setText(getContext().getString(R.string.language_name));
                    break;
               default:
                   break;

            }
        }
        for (Hexagon hex : myHexagons){
            switch (hex.getId()){
                case "about":
                    hex.setText(getContext().getString(R.string.about_text));
                    break;
                case "play":
                    hex.setText(getContext().getString(R.string.play_text));
                    break;
                case "mode":
                    hex.setText(getContext().getString(R.string.mode_text));
                    break;
                case "settings":
                    hex.setText(getContext().getString(R.string.settings_text));
                    break;
                default:
                    break;

            }
        }
    }
    private void setvolume(float x,float y) {

        for (MyVolumeBar  volumebar: myVolumeBars){

            if (isClickedvolumbar(x,y,volumebar)){


                volumebar.setFocusPoint(x);

                MyData.setFloatData("soundvolume",x,getContext());
                /////sound  volume
                ///float log1=(float)(Math.log(volumeBar.getrealWidth()-volumeBar.getselectedvolumbar())/Math.log(volumeBar.getrealWidth()));
                //nota.setVolume(1-log1,1-log1);
                break;

            }

        }

    }
    private boolean isClickedvolumbar(float x,float y,MyVolumeBar volumeBar) {


        float  extraspace=Contant.SCREEN_WIDHT/15;

        return x>volumeBar.getLeft()  &&  x<volumeBar.getRight()  && y>volumeBar.getTop()-extraspace  &&  y<volumeBar.getBottom()+extraspace;
    }
    private void gotoAboutpanel() {

        Log.d("new ınten", "onTouchEvent: ");
        Intent intent = new Intent(getContext(), About.class);
        getContext().startActivity(intent);
    }
    private void clickonsettingsbutton() {

        int textcolor=Color.rgb(60,18,69);
        float size=Contant.SCREEN_WIDHT/15;
        process.setMission(true);
        myPanel.setId("settigspanel");
        myPanel.setMaxHeightOK(Contant.SCREEN_HEIGHT*2/20);
        myPanel.setMaxWitdhOK(Contant.SCREEN_WIDHT/4);

        MyText  volumeytext=new MyText(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT*17/40,getContext().getString(R.string.volume_text),textcolor,size/2,1,"volumetext");
        myTexts.add(volumeytext);



        MyVolumeBar volumebar=new MyVolumeBar(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT*19/40,Contant.SCREEN_WIDHT/4,size/10,textcolor);
        volumebar.setFocusPoint(MyData.getFloatData("soundvolume",Contant.SCREEN_WIDHT/2,getContext()));
        myVolumeBars.add(volumebar);
        MyText  languagetext=new MyText(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT*21/40,getContext().getString(R.string.language_text),textcolor,size/2,1,"languagetext");
        myTexts.add(languagetext);
        MyText  languagename=new MyText(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT*23/40,getContext().getString(R.string.language_name),textcolor,size/3,1,"language");
        myTexts.add(languagename);

        MyArrowButton    left=new MyArrowButton(Contant.SCREEN_WIDHT*3/8,Contant.SCREEN_HEIGHT*23/40,size,textcolor,"left");
        myArrowButtons.add(left);
        MyArrowButton    right=new MyArrowButton(Contant.SCREEN_WIDHT*5/8,Contant.SCREEN_HEIGHT*23/40,size,textcolor,"right");
        myArrowButtons.add(right);

        MyButton   close=new MyButton(Contant.SCREEN_WIDHT*3/4,Contant.SCREEN_HEIGHT*8/20+size/2,size,size,textcolor,"X","close");
        myButtons.add(close);

    }
    private void clickonmodebutton() {


        int  time=MyData.getInttData("time",2,getContext());
        int textcolor=Color.rgb(60,18,69);
        float size=Contant.SCREEN_WIDHT/15;
        process.setMission(true);
        myPanel.setId("modepanel");
        myPanel.setMaxHeightOK(Contant.SCREEN_HEIGHT*2/20);
        myPanel.setMaxWitdhOK(Contant.SCREEN_WIDHT/4);
        MyText  timetext=new MyText(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT*35/80,getContext().getString(R.string.time_text),textcolor,size*4/5,1,"timetext");

        myTexts.add(timetext);
        MyText  showtime=new MyText(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT*19/40,Integer.toString(time)+"  "+getContext().getString(R.string.minute_text),textcolor,size/2,1,"time");
        myTexts.add(showtime);

        MyArrowButton    leftt=new MyArrowButton(Contant.SCREEN_WIDHT*3/8,Contant.SCREEN_HEIGHT*19/40,size,textcolor,"lefttime");
        myArrowButtons.add(leftt);
        MyArrowButton    rightt=new MyArrowButton(Contant.SCREEN_WIDHT*5/8,Contant.SCREEN_HEIGHT*19/40,size,textcolor,"righttime");
        myArrowButtons.add(rightt);
        MyText  diffu=new MyText(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT*43/80,getContext().getString(R.string.diffuculty_text),textcolor,size*4/5,1,"diffutext");
        myTexts.add(diffu);
        MyButton  normal=new MyButton(Contant.SCREEN_WIDHT*3/8,Contant.SCREEN_HEIGHT*23/40,size,size,textcolor,getContext().getString(R.string.normal_text),"normal");
        myButtons.add(normal);
        MyButton  advanced=new MyButton(Contant.SCREEN_WIDHT*5/8,Contant.SCREEN_HEIGHT*23/40,size,size,textcolor,getContext().getString(R.string.advanced_text),"advanced");
        myButtons.add(advanced);
      //  MyText  advanced=new MyText(Contant.SCREEN_WIDHT*5/8,Contant.SCREEN_HEIGHT*21/40,getContext().getString(R.string.normal_text),textcolor,size/2,1);
       // myTexts.add(advanced);
        MyLine  line=new MyLine(Contant.SCREEN_WIDHT*3/8,Contant.SCREEN_HEIGHT*23/40+size/4,Contant.SCREEN_WIDHT/8,size/8,textcolor);
        float  linecenterx=MyData.getFloatData("linecenterx",Contant.SCREEN_WIDHT*3/8,getContext());
        line.setbegin(linecenterx);
        myLines.add(line);
        MyButton   close=new MyButton(Contant.SCREEN_WIDHT*3/4,Contant.SCREEN_HEIGHT*8/20+size/2,size,size,textcolor,"X","close");
        myButtons.add(close);



        }
    private boolean isClickedHex(float x, float y,Hexagon gon) {

        return Math.sqrt(Math.pow((gon.getX()-x),2)+Math.pow(gon.getY()-y,2))<gon.getRadius();

    }
    private void RateusPanelProcess(float x ,float y) {



        for (MyButton  button : myButtons) {

            if ( isClickedButton(x, y, button)){

                switch (button.getId()) {

                    case "close":

                        process.setMission(false);
                        deletepanelinfo();
                        break;
                    case "yes":



                        String appPackageName = getContext().getPackageName(); // getPackageName() from Context or Activity object
                        try {
                           // getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.nazo.mathrapid")));
                            ///https://play.google.com/store/apps/details?id=com.mockingbird.magiccolors
                        } catch (android.content.ActivityNotFoundException anfe) {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                        MyData.setBooleanData("unrated",false,getContext());

                        deletepanelinfo();
                        float size=Contant.SCREEN_WIDHT/15;
                        int color=Color.argb(250,60,18,69);
                        MyButton   close=new MyButton(Contant.SCREEN_WIDHT-size/2,size,size,size,color,"X","close");
                        myButtons.add(close);
                        mulText=new MyMulText(getContext().getString(R.string.thanks_view),Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2,size*3/4, (int) (Contant.SCREEN_WIDHT*3/4),color);

                        mulText.setTextYCoordinateOK(Contant.SCREEN_HEIGHT/2-Contant.SCREEN_HEIGHT/12);

                        break;
                    case "notnow":

                        process.setMission(false);
                        deletepanelinfo();
                        break;

                }
                break;
            }
        }
    }
    private boolean isClickedButton(float x, float y, MyButton button) {

         return  Math.abs( x-button.getXcenter())<button.getMaxX()  &&    Math.abs( y-button.getYcenter())<button.getMaxX();
    }
    private void deletepanelinfo() {



        myPanel.reborn();
        for (int i=myButtons.size()-1;  i>=0; i--){
            myButtons.remove(i);
        }
        for (int i=myVolumeBars.size()-1;  i>=0; i--){
            myVolumeBars.remove(i);
        }
        for (int i=myTexts.size()-1;  i>=0; i--){
            myTexts.remove(i);
        }
        for (int i=myArrowButtons.size()-1;  i>=0; i--){
            myArrowButtons.remove(i);
        }
        for (int i=myLines.size()-1;  i>=0; i--){
            myLines.remove(i);
        }
    }
    @Override
    public void  onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (process.getInit()){
            initial();

            process.setInit(false);
        }
      //  canvas.drawColor(Color.WHITE);
        canvas.drawPaint(myPaint.getPaint());

        DrawHexagon(canvas);
        DrawShareButton(canvas);

        DrawHowPlay(canvas);

        if (process.getMission()){
            DrawMission(canvas);

        }


     //   canvas.drawText(String.valueOf(process.getMission()),100,200,new Paint());
        invalidate();
        //canvas.drawText(text.getText(),text.getX(),text.getYlocation(),text.getPaint());
    }
    private void DrawHowPlay(Canvas canvas) {




        if (howtoplay.getId().equals("howplay")){
                canvas.drawText(howtoplay.getText(),howtoplay.getX(),howtoplay.getYlocation(),howtoplay.getPaint());
        }

    }
    private void DrawShareButton(Canvas canvas) {


        canvas.drawBitmap(sharephoto.getPhoto(),sharephoto.getLeft(),sharephoto.getTop(),sharephoto.getPaint());

    }
    private void DrawMission(Canvas canvas) {


        if (myPanel.getWidth()<myPanel.getMaxwidth()){


            myPanel.setWitdh(Contant.SPEED*3);
        }
        if (myPanel.getHeight()<myPanel.getMaxheight()){
            myPanel.setHeight(Contant.SPEED*3);


        }



        canvas.drawRoundRect(myPanel.getLeft(),myPanel.getTop(),myPanel.getRight(),myPanel.getBottom(),45,45,myPanel.getPaint());
        canvas.drawRoundRect(myPanel.getLeft(),myPanel.getTop(),myPanel.getRight(),myPanel.getBottom(),45,45,myPanel.getPaintstroke());



        for (MyButton  button :  myButtons){


           // canvas.drawRoundRect(button.getLeft(),button.getTop(),button.getRight(),button.getBottom(),45,45,button.getPaint());
            canvas.drawText(button.getText(),button.getXcenter(),button.getTextYlocation(),button.getTextpaint());

        }


        for (MyText  text: myTexts){
            canvas.drawText(text.getText(),text.getX(),text.getYlocation(),text.getPaint());
        }
        for (MyArrowButton  arrow: myArrowButtons){
            canvas.drawPath(arrow.getPath(),arrow.getPaint());
        }
        switch (myPanel.getId()){

            case  "rateuspanel" :


                canvas.save();

                canvas.translate(mulText.getTextXCoordinate(),mulText.getTextYCoordinate());
                mulText.getLayout().draw(canvas);
                canvas.restore();
                break;
            case "settigspanel":


                for (MyVolumeBar  volumebar:  myVolumeBars ) {
                    canvas.drawRoundRect(volumebar.getLeft(), volumebar.getTop(), volumebar.getRight(), volumebar.getBottom(), 45, 45, volumebar.getPaint());
                    canvas.drawRoundRect(volumebar.getFocuspoint(), 45, 45, volumebar.getPaint());
                }
                break;

            case "modepanel":


                for (MyLine  line: myLines ) {

                    canvas.drawLine(line.getStartx() ,line.getStarty(),line.getStopx(),line.getStopy(),line.getPaint());

                    if (line.getwitdh()<line.getMaxwitdh()){


                        line.growuphorizantal(Contant.SPEED/2);
                    }
                }
                break;
        }


    }
    private void DrawHexagon(Canvas canvas) {



        for (Hexagon  hex:  myHexagons){


            canvas.drawPath(hex.getPath(),hex.getPaint());
            canvas.drawText(hex.getText(),hex.getX(),hex.getYtextlocation(),hex.getTextpaint());
        }



            canvas.drawText(maintext.getText(),maintext.getX(),maintext.getYlocation(),maintext.getPaint());



    }
    private void initial(){

        language=new ArrayList<>();
        language.add("en");
        language.add("tr");
        int  index=MyData.getInttData("index",0,getContext());
        SetLocale(language.get(index));

        Float  size=Contant.SCREEN_WIDHT/15;
        int color=Color.argb(250,60,18,69);
        /////initlazie
        ///myPaint=new MyPaint(Color.rgb(166, 250, 242),Color.rgb(245, 216, 55));
        myPaint=new MyPaint(Color.rgb(125, 206, 160),Color.rgb(88, 214, 141));
        ///Color.rgb(),Color.rgb(12, 243, 250)
        myPanel=new MyPanel(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2,Color.argb(240,88, 214, 141),0,0,"null");
        myLines=new ArrayList<>();


        myButtons=new ArrayList<>();
        myHexagons=new ArrayList<>();
        myTexts=new ArrayList<>();

        ///////////Add  language


        sharephoto=new MyPhotos(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.share), Math.round(size*2), Math.round(size*2)),Contant.SCREEN_WIDHT-size*5/2,Contant.SCREEN_HEIGHT-size*5/2,Color.argb(250,60,18,69),"share");
        myArrowButtons=new ArrayList<>();
       // myVariable=new MyVariable();
        myVolumeBars=new ArrayList<>();
        maintext=new MyText(Contant.SCREEN_WIDHT/2,0,getContext().getString(R.string.app_name),Color.argb(250,60,18,69),Contant.SCREEN_WIDHT*2/15,1,"maintext");

       /// MyShape  online=new MyShape(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2,Contant.SİZE*2,Color,getContext().getString(R.string.online_text),"onlinebutton");
        ///  public Hexagon(float x,float y, float radius ,int mycolor,String text,String id) {
        Hexagon  mode=new Hexagon(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2,size*2,color,getContext().getString(R.string.mode_text),"mode");
        myHexagons.add(mode);

        Hexagon  play=new Hexagon(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2-size*4,size*2,color,getContext().getString(R.string.play_text),"play");
        myHexagons.add(play);

        ////////////
        howtoplay=new MyText(size/4,Contant.SCREEN_HEIGHT-size,getContext().getString(R.string.how_play_text),Color.rgb(60,18,69),size*3/4,0,"howplay");

        Hexagon  settings=new Hexagon(Contant.SCREEN_WIDHT/2,mode.getY()+(Contant.SCREEN_HEIGHT-mode.getY())/2,size*3/2,color,getContext().getString(R.string.settings_text),"settings");
        myHexagons.add(settings);

        Hexagon about=new Hexagon(Contant.SCREEN_WIDHT/2,settings.getY()+settings.getRadius()*2,size*3/2,color,getContext().getString(R.string.about_text),"about");
        myHexagons.add(about);

        ///////////////set maintext y loacatoın
        maintext.setYlocation(play.getY()/2-size*3/2);


        //////////////////////




        int  openednumber=MyData.getInttData("openednumber",0,getContext());
        Boolean  unrated=MyData.getBooleanData("unrated",true,getContext());



        //myPanel.set(Contant.SCREEN_WIDHT/2);
        if (ShowRateus(openednumber)  &&  unrated){


            process.setMission(true);
            mulText=new MyMulText(getContext().getString(R.string.rate_us),Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2,size*3/4, (int) (Contant.SCREEN_WIDHT*3/4),color);
            myPanel.setMaxHeightOK(Contant.SCREEN_HEIGHT);
            myPanel.setMaxWitdhOK(Contant.SCREEN_WIDHT);
            myPanel.setId("rateuspanel");
            mulText.setTextYCoordinateOK(Contant.SCREEN_HEIGHT/2-Contant.SCREEN_HEIGHT/12);
            MyButton   yes=new MyButton(Contant.SCREEN_WIDHT/4,Contant.SCREEN_HEIGHT/2+Contant.SCREEN_HEIGHT/15*2,size,size,color,getContext().getString(R.string.rate_it_text),"yes");
            MyButton   notnow=new MyButton(Contant.SCREEN_WIDHT*3/4,Contant.SCREEN_HEIGHT/2+Contant.SCREEN_HEIGHT/15*2,size,size,color,getContext().getString(R.string.not_now_text),"notnow");
            MyButton   close=new MyButton(Contant.SCREEN_WIDHT-size/2,size,size,size,color,"X","close");


            myButtons.add(notnow);
            myButtons.add(yes);
            myButtons.add(close);

        }
        MyData.setIntData("openednumber",openednumber+1,getContext());



    }
    private void SetLocale(String  lang) {



        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);




    }
    private boolean ShowRateus(int openednumber) {

        for (int i=1;  i<10; i++){
            if (openednumber==Math.pow(2,i)){
                return true;
            }
        }
        return   false;
    }
    private Bitmap  getResizedBitmap(Bitmap bitmap, int newwitdh, int newHeight){
        Bitmap  resizedBitmap=Bitmap.createBitmap(newwitdh,newHeight,Bitmap.Config.ARGB_8888);
        float scaleX=newwitdh/(float)bitmap.getWidth();
        float scaleY=newHeight/(float)bitmap.getHeight();

        float pivotX=0;
        float pivotY=0;
        Matrix scaleMatrix=new Matrix();
        scaleMatrix.setScale(scaleX,scaleY,pivotX,pivotY);

        Canvas canvas=new Canvas(resizedBitmap);

        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap,0,0,new Paint(Paint.FILTER_BITMAP_FLAG));

        return  resizedBitmap;
    }
}



