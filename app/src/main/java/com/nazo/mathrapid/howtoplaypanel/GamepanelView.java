package com.nazo.mathrapid.howtoplaypanel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.nazo.mathrapid.Contant;
import com.nazo.mathrapid.MyData;
import com.nazo.mathrapid.MyPhotos;
import com.nazo.mathrapid.R;
import com.nazo.mathrapid.gamepanel.Gamepanel;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


import java.util.Random;
import java.util.Set;

/**
 * Created by mockingbird on 6/19/2018.
 */
public class GamepanelView extends View {




    private  MyPanel  myPanel;
    private  MyClock  myClock;
    private  MyBubble   resultbubble;
    private  ArrayList<MyBar>   mybars;
    private  ArrayList<String>   myQuestion;
    private  ArrayList<MyText>  myTexts;
    private  ArrayList<MyButton>   mybuttons;
    private Random r;
    private    ArrayList<String>   operations;
    private    ArrayList<MyLocation>   mylocations;
    private    ArrayList<MyBubble>   myBubbles;
    private    ArrayList<MyBubble>   downstatebubbles;
    private  ArrayList<String>  useranswer;
    private  ResultIcon icon;


    private  MyPaint  myPaint;
    private  Info  info;
    private   int color;



    private Process  process=new Process();
    public  GamepanelView(Context context){
        super(context);


    }
    public    boolean   onTouchEvent(MotionEvent event) {


        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {


            case MotionEvent.ACTION_DOWN:


                if (process.getMission()){

                        for (MyText   text:  myTexts){
                            if (x>Contant.SCREEN_WIDHT/4  &&  x<Contant.SCREEN_WIDHT*3/4  &&  y>Contant.SCREEN_HEIGHT/2-text.getSize() &&   y<Contant.SCREEN_HEIGHT/2+text.getSize()){
                                Intent intent = new Intent(getContext(), Gamepanel.class);
                                getContext().startActivity(intent);

                            }
                        }

                }
        }
        return false;
    }
    private boolean isTextclicked(float x, float y, MyText text) {

        return          Math.sqrt(Math.pow(text.getX()-x,2)+Math.pow(text.getY()-y,2)) <text.getSize();
    }
    private void clickondownstatebubbles(MyBubble bubble, int i) {



        if (i==downstatebubbles.size()-1) {
            int index = empty_locationindex();
            MyBubble newbuble = new MyBubble(mylocations.get(index).getXcenter(), mylocations.get(index).getYcenter(), bubble.getPaint().getColor(), bubble.getText(), bubble.getId(), index);
            setBubblecolor(newbuble);
            myBubbles.add(newbuble);

            useranswer.remove(useranswer.size()-1);

            if (isStringEqual(info.getSelected(),"number")){
                info.setSelected("operation");
            }else{
                info.setSelected("number");
            }

            if (isStringEqual(bubble.getText(),"!")){
                downstatebubbles.get(0).resetbubble();
                downstatebubbles.get(0).setXOK(Contant.SCREEN_WIDHT/2-Contant.SCREEN_WIDHT/4);
            }

            downstatebubbles.remove(i);
            setColorundobutton();

        }else{
            downstatebubbles.get(i).setSizeOK(0);
        }
    }
    private void deleteAll() {


        for (int i=downstatebubbles.size()-1;i>=0; i--){
            downstatebubbles.remove(i);
        }
        for (int i=useranswer.size()-1;i>=0; i--){
            useranswer.remove(i);
        }

    }
    private void clickonundobutton() {


                undonormal();



    }
    private void undonormal() {

        if (info.getUndo()  &&   useranswer.size()>=3){


            int  deleteindex =(myBubbles.size()-1);
            mylocations.get(myBubbles.get(deleteindex).getLocationindex()).setUsedOK(-1);
            myBubbles.remove(deleteindex);


            for (int  i=0 ;i<=2;i++){


                int  lastindex=useranswer.size()-1;
                int  location=empty_locationindex();
                if (isOperation(useranswer.get(lastindex))){
                    MyBubble  bubble=new MyBubble( mylocations.get(location).getXcenter(), mylocations.get(location).getYcenter(),color,useranswer.get(lastindex),"operation",location);
                    setBubblecolor(bubble);
                    myBubbles.add(bubble);

                }else {
                    MyBubble  bubble=new MyBubble( mylocations.get(location).getXcenter(), mylocations.get(location).getYcenter(),color,useranswer.get(lastindex),"number",location );
                    setBubblecolor(bubble);
                    myBubbles.add(bubble);
                }
                useranswer.remove(lastindex);

            }

        }
        setColorundobutton();


    }
    private  void setColorundobutton(){
        for (MyButton  button:  mybuttons){
            switch (button.getId()){
                case  "undo":

                        if (downstatebubbles.size()==0){

                            if (useranswer.size()==2 ){
                                if (isStringEqual(useranswer.get(1),"!")){

                                    info.setUndo(true);
                                    button.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                                }
                            }else if (useranswer.size()>=3){

                                info.setUndo(true);
                                button.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                            }else {

                                info.setUndo(false);
                                button.getPaint().setStyle(Paint.Style.STROKE);
                            }
                        }else{

                            info.setUndo(false);
                            button.getPaint().setStyle(Paint.Style.STROKE);
                        }


                    return;
            }

        }
    }
    private  void  setBubblecolor(MyBubble bubble){
        bubble.getPaint().setColor(lighter(color,0.5f));
        bubble.getStrokepaint().setColor(color);
        bubble.getTextpaint().setColor(color);
    }
    private void resetcolorAll() {


        color=Color.argb(255,r.nextInt(50),r.nextInt(50),r.nextInt(50));

        for (MyBar  bar:  mybars) {

            bar.getPaint().setColor(lighter(color,0.4f));

        }
        for (MyBubble  bubble:  myBubbles) {

            bubble.getPaint().setColor(lighter(color,0.5f));
            bubble.getStrokepaint().setColor(color);
            bubble.getTextpaint().setColor(color);

        }
        for (MyButton  button:  mybuttons) {

            button.getPaint().setColor(color);

        }

        resultbubble.getPaint().setColor(lighter(color,0.5f));
        resultbubble.getStrokepaint().setColor(color);
        resultbubble.getTextpaint().setColor(color);

        myPaint.getPaint().setColor(lighter(color,0.7f));

        icon.getPaint().setColor(lighter(color,0.5f));
        icon.getStrokepaint().setColor(color);

        myClock.getPaint().setColor(color);

        myPanel.getPaint().setColor(color);
        myPanel.getPaint().setAlpha(150);
        myPanel.getPaintstroke().setColor(color);
        //icon.getTextpaint().setColor(color);
    }
    @Override
    public   void  onDraw(Canvas canvas){
        super.onDraw(canvas);

        if (process.getInit()){
            initial();
            process.setInit(false);
        }
        //canvas.drawColor(Color.argb(255,255,255,255));
        canvas.drawPaint(myPaint.getPaint());
        drawBarsandelements(canvas);
        drawresultBubble(canvas);
        drawBubles(canvas);
        drawDownBubble(canvas);





        if (info.getisSolved()){
            drawIcon(canvas);
        }

        if (process.getMission()){
            drawMission(canvas);

        }

        HowtoPlayProcess();










        invalidate();

    }
    private void HowtoPlayProcess() {

        if (info.gettick()) {
        switch (info.getCount()){

            case 2:
                  clickonBubble(myBubbles.get(0), 0);
                break;

            case 3:
                clickonBubble(myBubbles.get(0), 0);
                break;
            case 4:
                clickonBubble(myBubbles.get(0), 0);
                break;
            case 5:
                for (MyButton  butto:  mybuttons){
                    if (butto.getId().equals("equal")){
                        butto.setsizeOK(0);
                        clickonequalbutton();
                        break;
                    }
                }
                break;
            ////SECONDQUESTION
            case 7:
                clickonBubble(myBubbles.get(0), 0);
                break;
            case 8:
                clickonBubble(myBubbles.get(2), 2);
                break;
            case 9:
                clickonBubble(myBubbles.get(1), 1);
                break;
            case 10:
                for (MyButton  butto:  mybuttons){
                    if (butto.getId().equals("equal")){
                        butto.setsizeOK(0);
                        clickonequalbutton();
                        break;
                    }
                }
                break;
            case 11:
                clickonBubble(myBubbles.get(2), 2);
                break;
            case 12:
                clickonBubble(myBubbles.get(0), 0);
                break;
            case 13:
                clickonBubble(myBubbles.get(0), 0);
                break;
            case 14:
                for (MyButton  butto:  mybuttons){
                    if (butto.getId().equals("equal")){
                        butto.setsizeOK(0);
                        clickonequalbutton();
                        break;
                    }
                }
                break;
            case 15:
                for (MyButton  butto:  mybuttons){
                    if (butto.getId().equals("undo")){
                        butto.setsizeOK(0);
                        clickonundobutton();
                        break;
                    }
                }

                break;
            case 16:
                for (MyButton  butto:  mybuttons){
                    if (butto.getId().equals("undo")){
                        butto.setsizeOK(0);
                        clickonundobutton();
                        break;
                    }
                }
                break;
            case 17:
                clickonBubble(myBubbles.get(0), 0);
                break;
            case 18:
                clickonBubble(myBubbles.get(0), 0);
                break;
            case 19:

                clickondownstatebubbles(downstatebubbles.get(1), 1);
                break;
            case 20:

                clickondownstatebubbles(downstatebubbles.get(0), 0);
                break;
            case 21:

                clickonBubble(myBubbles.get(2), 2);
                break;
            case 22:

                clickonBubble(myBubbles.get(2), 2);
                break;
            case 23:

                clickonBubble(myBubbles.get(2), 2);
                break;
            case 24:
                for (MyButton  butto:  mybuttons){
                    if (butto.getId().equals("equal")){
                        butto.setsizeOK(0);
                        clickonequalbutton();
                        break;
                    }
                }
                break;
            case 25:

                clickonBubble(myBubbles.get(0), 0);
                break;
            case 26:

                clickonBubble(myBubbles.get(0), 0);
                break;
            case 27:

                clickonBubble(myBubbles.get(0), 0);
                break;
            case 28:
                for (MyButton  butto:  mybuttons){
                    if (butto.getId().equals("equal")){
                        butto.setsizeOK(0);
                        clickonequalbutton();
                        break;
                    }
                }

                break;
            case 30:

                    process.setMission(true);


                break;
            default:
                    break;

            }


            info.settick(false);
        }
    }
    private void drawMission(Canvas canvas) {



        canvas.drawRoundRect(myPanel.getLeft(),myPanel.getTop(),myPanel.getRight(),myPanel.getBottom(),45,45,myPanel.getPaint());
       canvas.drawRoundRect(myPanel.getLeft(),myPanel.getTop(),myPanel.getRight(),myPanel.getBottom(),45,45,myPanel.getPaintstroke());


        if (myPanel.getWidth()<myPanel.getMaxwidth()){
            myPanel.setWitdh(Contant.SPEED*2);

        }
        if (myPanel.getHeight()<myPanel.getMaxheight()){
            myPanel.setHeight(Contant.SPEED*3);

        }

        for (MyText text:  myTexts){
            canvas.drawText(text.getText(),text.getX(),text.getYlocation(),text.getPaint());
        }

    }
    private void drawIcon(Canvas canvas) {

      
        canvas.drawText(icon.getIcon(),icon.getX(),icon.gettextYlocation(),icon.getPaint());
        canvas.drawText(icon.getIcon(),icon.getX(),icon.gettextYlocation(),icon.getStrokepaint());



        if (icon.getSize()<icon.getMaxsize()){
            icon.setSize(Contant.SPEED*3);

        }
        if (  (System.currentTimeMillis() - icon.getStart())/1000==1 ){
            info.setisSolved(false);



            if (info.getAskedquestion()<1){

                info.setAskedquestion(1);
                deleteQuestiion();
                myQuestion.add("4");myQuestion.add("÷");myQuestion.add("2");myQuestion.add("+");myQuestion.add("2");


                SetLocations();
                setBubbles();
                resetcolorAll();

                Log.d(String.valueOf(info.getAskedquestion()), "drawIcon: ");
            }else{

                process.setMission(true);
            }

        }


    }
    private void deleteQuestiion() {


        for (int  i=myQuestion.size()-1; i>=0;i--){
            myQuestion.remove(i);
        }
    }
    private void drawDownBubble(Canvas canvas) {

        for (MyBubble   bubble:   downstatebubbles  ) {


            if (bubble.getSize()<Contant.SCREEN_WIDHT/15*2){
                bubble.setSize(Contant.SPEED*2);
            }
            canvas.drawOval(bubble.getLeft(), bubble.getTop(), bubble.getRight(), bubble.getBottom(),bubble.getPaint());
            canvas.drawOval(bubble.getLeft(), bubble.getTop(), bubble.getRight(), bubble.getBottom(), bubble.getStrokepaint());
            canvas.drawText(bubble.getText(), bubble.getXcenter(), bubble.getTextYlocation(), bubble.getTextpaint());


        }
    }
    private void drawBubles(Canvas canvas) {

        for (MyBubble   bubble:   myBubbles  ) {


            if (bubble.getSize()<Contant.SCREEN_WIDHT/15*2){
                bubble.setSize(Contant.SPEED*2);
            }
            canvas.drawOval(bubble.getLeft(), bubble.getTop(), bubble.getRight(), bubble.getBottom(),bubble.getPaint());
            canvas.drawOval(bubble.getLeft(), bubble.getTop(), bubble.getRight(), bubble.getBottom(), bubble.getStrokepaint());
            canvas.drawText(bubble.getText(), bubble.getXcenter(), bubble.getTextYlocation(), bubble.getTextpaint());


        }
    }
    private void drawresultBubble(Canvas canvas) {


        if (resultbubble.getSize()<Contant.SCREEN_WIDHT/15*2){
            resultbubble.setSize(Contant.SPEED*2);
        }
        canvas.drawOval(resultbubble.getLeft(), resultbubble.getTop(), resultbubble.getRight(), resultbubble.getBottom(), resultbubble.getPaint());


        canvas.drawOval(resultbubble.getLeft(), resultbubble.getTop(), resultbubble.getRight(), resultbubble.getBottom(), resultbubble.getStrokepaint());
        canvas.drawText(resultbubble.getText(), resultbubble.getXcenter(), resultbubble.getTextYlocation(), resultbubble.getTextpaint());
    }
    private void drawBarsandelements(Canvas canvas){








        for (MyBar   bar: mybars){

            canvas.drawRect(bar.getLeft(),bar.getTop(),bar.getRight(),bar.getBottom(),bar.getPaint());


        }
        for (MyButton   button: mybuttons){

            canvas.drawOval(button.getLeft(),button.getTop(),button.getRight(),button.getBottom(),button.getPaint());
            canvas.drawText(button.getText(),button.getXcenter(),button.getTextYlocation(),button.getTextpaint());

            if (button.getSize()<Contant.SCREEN_HEIGHT/35){


                button.setsize(Contant.SPEED);

            }
            if (button.getId().equals("skip")){
                if (info.getRemainskiptime()<0  &&  !info.getUseskip()){

                    button.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                    info.setUseskip(true);
                }
            }
        }





        if (!info.getGameover()) {
            canvas.drawArc(myClock.getRect(), 270, myClock.getSweepangle(), true, myClock.getPaint());
            canvas.drawArc(myClock.getRect(), 270, myClock.getSweepangle(), true, myClock.getStrokepaint());
            ///canvas.drawText(String.valueOf(myClock.getSweepangle()),100,200,new Paint());
            if (myClock.getSize() < Contant.SCREEN_WIDHT / 15) {
                myClock.setsize(Contant.SPEED);
            }


            /*
            * if (myClock.getSweepangle()>= 0) {


                    GameFinishPanel();
            }*/
        }


    }
    private void initial() {

         color=Color.rgb(207,167,76);
         myPaint=new MyPaint(lighter(color,0.7f),Color.rgb(207,167,76));
         mybars=new ArrayList<>();
         mybuttons=new ArrayList<>();
         myQuestion=new ArrayList<>();
       //  lineTexts=new ArrayList<>();
         r=new Random();
         operations=new ArrayList<>();
        // myPhotoses=new ArrayList<>();
         info=new Info();
         mylocations=new ArrayList<>();
         myBubbles=new ArrayList<>();
         myTexts=new ArrayList<>();

         downstatebubbles=new ArrayList<>();
         useranswer=new ArrayList<>();
         myPanel=new MyPanel(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2,color,0,0,"null");
        icon=new ResultIcon();

            MyText text=new MyText(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2,getContext().getString(R.string.touch_to_play_text),Color.WHITE,Contant.SCREEN_WIDHT/15,1,"text");
            myTexts.add(text);
         MyBar upbar=new MyBar(0,0, Contant.SCREEN_WIDHT,Contant.SCREEN_HEIGHT/10,lighter(color,0.4f),"up");
         MyBar   bottombar=new MyBar(0,Contant.SCREEN_HEIGHT*17/20,Contant.SCREEN_WIDHT,Contant.SCREEN_HEIGHT,lighter(color,0.4f),"bottom");
         myClock=new MyClock(Contant.SCREEN_WIDHT/2,upbar.getcenterY(),color,MyData.getInttData("time",2,getContext()));
         mybars.add(upbar);
         mybars.add(bottombar);
         MyButton skipbutton=new MyButton(upbar.getcenterY()*4/5,upbar.getcenterY(),0,color,getContext().getString(R.string.skip_text),"skip");
         MyButton undobutton=new MyButton(Contant.SCREEN_WIDHT-upbar.getcenterY()*4/5,upbar.getcenterY(),0,color,getContext().getString(R.string.undo_text),"undo");
         //  MyButton hintbutton=new MyButton(size,size*16,0,Contant.COLOR,getContext().getString(R.string.hint_text),"hintbutton");
         MyButton equalbutton=new MyButton(Contant.SCREEN_WIDHT-upbar.getcenterY()*4/5,bottombar.getTop()-upbar.getcenterY(),0,color,"=","equal");
         mybuttons.add(skipbutton);
         mybuttons.add(undobutton);
         //   mybuttons.add(hintbutton);
         mybuttons.add(equalbutton);

         /////////////////////7
         resultbubble= new MyBubble(Contant.SCREEN_WIDHT/2,upbar.getcenterY()*7/2,lighter(color,0.2f),"null","mainbubble",-1);

        myPanel.reborn();


        myPanel.setMaxHeightOK(Contant.SCREEN_HEIGHT/2);
        myPanel.setMaxWitdhOK(Contant.SCREEN_WIDHT/2);
        //myPanel.getPaint().setColor(color);
      //  myPanel.getPaint().setAlpha(150);


            new CountDownTimer(31000, 1000) {

            public void onTick(long millisUntilFinished) {


                info.settick(true);
                info.setCount(1);
                // mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {


            }
        }.start();

        myQuestion.add("1");
        myQuestion.add("+");
        myQuestion.add("2");

        ///myquestoın full up with questıpon

        //////set location
        SetLocations();
        ////for all string fix up a ball
        setBubbles();

        setupfirstcolor();

        resetcolorAll();
      //  resultbubble.setText(String.valueOf(getQestionResult()));




    }
    private void setupfirstcolor() {
        for (MyBar  bar:  mybars) {

            bar.getPaint().setColor(lighter(color,0.4f));

        }
        for (MyBubble  bubble:  myBubbles) {

            bubble.getPaint().setColor(lighter(color,0.5f));
            bubble.getStrokepaint().setColor(color);
            bubble.getTextpaint().setColor(color);

        }
        for (MyButton  button:  mybuttons) {

            button.getPaint().setColor(color);

        }

        resultbubble.getPaint().setColor(lighter(color,0.5f));
        resultbubble.getStrokepaint().setColor(color);
        resultbubble.getTextpaint().setColor(color);

        myPaint.getPaint().setColor(lighter(color,0.7f));

        icon.getPaint().setColor(lighter(color,0.5f));
        icon.getStrokepaint().setColor(color);
    }
    private void setBubbles() {
        deletebubbles();

        for (int i=0;i<myQuestion.size();i++){

            int  location=empty_locationindex();

            if (isOperation(myQuestion.get(i))){
                MyBubble  bubble=new MyBubble( mylocations.get(location).getXcenter(), mylocations.get(location).getYcenter(),color,myQuestion.get(i),"operation",location);

                myBubbles.add(bubble);


            }else {
                MyBubble  bubble=new MyBubble( mylocations.get(location).getXcenter(), mylocations.get(location).getYcenter(),color,myQuestion.get(i),"number",location );
                myBubbles.add(bubble);
            }
        }

        resultbubble.setText(String.valueOf(getQestionResult()));
        resultbubble.setSizeOK(0);
        icon.setSizeOK(0);
    }
    private boolean isOperation(String s) {

        return   s.equals("+") ||  s.equals("-") ||s.equals("x") ||s.equals("÷") ||s.equals("^") ||s.equals("!") || s.equals("√")  ;
    }
    private  int empty_locationindex() {


        int  place=r.nextInt(mylocations.size());


        while(mylocations.get(place).getUsed()!=-1){

            place=r.nextInt(mylocations.size());

        }

        mylocations.get(place).setUsedOK(place);

        return   place;
    }
    private void deletebubbles() {

        for (int  i=myBubbles.size()-1;i>=0;i--){
            myBubbles.remove(i);
        }
    }
    private void setnewQuestion() {



        for (int i=myQuestion.size()-1;i>=0;i--){
            myQuestion.remove(i);
        }

        setColorundobutton();
        info.setAskedquestion(1);
        switch (MyData.getStringData("mode", "normal", getContext())) {

            case "normal":

                //MyData.setIntData("numberrange0",5,getContext());
                //MyData.setIntData("numberofbuble0",2,getContext());
                operations.add("+");
                operations.add("-");
                operations.add("x");
                operations.add("÷");



                int numberofbubble=5;
                int numberrange=5;
             /*
                Boolean isbubblenumberup=MyData.getBooleanData("isnumberbubble0",true,getContext());
                int numberrange=MyData.getInttData("numberrange0",5,getContext());
                int numberofbubble=MyData.getInttData("numberofbuble0",2,getContext());

                if (info.getunSuccesRate()>=0  &&   info.getunSuccesRate()<2){


                    if (numberofbubble<8  &&  isbubblenumberup){
                        MyData.setIntData("numberofbuble0",numberofbubble+1,getContext());
                        MyData.setBooleanData("isnumberbubble0",false,getContext());
                    }else {
                        MyData.setIntData("numberrange0",numberrange+1,getContext());
                        MyData.setBooleanData("isnumberbubble0",true,getContext());
                    }


                }else   if (info.getunSuccesRate()>=7){

                    if (numberofbubble>3){
                        MyData.setIntData("numberofbuble0",numberofbubble-1,getContext());
                        MyData.setBooleanData("isnumberbubble0",false,getContext());
                    }else {

                        if (numberrange>2){
                            MyData.setIntData("numberrange0",numberrange-1,getContext());

                        }
                        MyData.setBooleanData("isnumberbubble0",true,getContext());

                    }
                }

            */
                if (numberofbubble%2==0){
                    numberofbubble++;
                }



                String   lastoperation="null";
                for (int i=0;i<numberofbubble;i++){

                    if (i%2==0){

                        if (isStringEqual(lastoperation,"÷")){

                            int number=r.nextInt(numberrange)+1;




                            while (!(getQestionResult()%number==0)){
                                number=r.nextInt(numberrange)+1;
                            }
                            myQuestion.add(String.valueOf(number));


                        }else {
                            myQuestion.add(String.valueOf(r.nextInt(numberrange)));
                        }
                    }else {

                        lastoperation=operations.get(r.nextInt(operations.size()));
                        myQuestion.add(lastoperation);
                    }
                }


                Log.d("myQuestion", "myQuestion: "  +myQuestion);
                Log.d("e", "result: "  +getQestionResult());

                break;
            default:
                break;
        }
    }
    private  int  getQestionResult(){

        int  result=0;
        String  oparetion="null";

        for (int i=0; i<myQuestion.size()  ;i++){

            switch (myQuestion.get(i)){

                case "+":
                    oparetion="+";
                    break;
                case  "-":
                    oparetion="-";
                    break;
                case "x":
                    oparetion="x";
                    break;
                case  "÷":
                    oparetion="÷";
                    break;
                case  "^":
                    oparetion="^";
                    break;
                case  "!":
                    result=factorial(result);
                    oparetion="!";
                    break;


                default:

                    switch (oparetion){

                        case "+":
                            result=result+Integer.parseInt(myQuestion.get(i));
                            break;
                        case  "-":
                            result=result-Integer.parseInt(myQuestion.get(i));
                            break;
                        case "x":
                            result=result*Integer.parseInt(myQuestion.get(i));
                            break;
                        case  "÷":
                            result= result/Integer.parseInt(myQuestion.get(i));
                            break;
                        case  "^":
                            result= (int) Math.pow(result,Integer.parseInt(myQuestion.get(i)));
                            break;
                        case  "null":
                            result=Integer.parseInt(myQuestion.get(i));
                            break;
                        default:
                            break;
                    }
                    break;
            }
        }


        return  result;
    }
    private boolean isStringEqual(String firststring, String s) {

        return   firststring.equals(s);

    }
    private void SetLocations() {


        for (int  i=mylocations.size()-1;i>=0;i--){
            mylocations.remove(i);
        }

        float distance=Contant.SCREEN_WIDHT/4;

        float triangleHeight = (float) (Math.sqrt(3) * distance / 2);
        switch (myQuestion.size()){


            case  2 :
                MyLocation  loca0_2=new MyLocation(Contant.SCREEN_WIDHT/2-distance/2,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca1_2=new MyLocation(Contant.SCREEN_WIDHT/2+distance/2,Contant.SCREEN_HEIGHT/2);

                mylocations.add(loca0_2);
                mylocations.add(loca1_2);
                break;
            case  3:

                MyLocation  loca0_3=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca1_3=new MyLocation(Contant.SCREEN_WIDHT/2+distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca2_3=new MyLocation(Contant.SCREEN_WIDHT/2-distance,Contant.SCREEN_HEIGHT/2);
                mylocations.add(loca0_3);
                mylocations.add(loca1_3);
                mylocations.add(loca2_3);
                break;
            case  4:
                MyLocation  loca0_4=new MyLocation(Contant.SCREEN_WIDHT/2-distance/2,Contant.SCREEN_HEIGHT/2-distance/2);
                MyLocation  loca1_4=new MyLocation(Contant.SCREEN_WIDHT/2+distance/2,Contant.SCREEN_HEIGHT/2-distance/2);
                MyLocation  loca2_4=new MyLocation(Contant.SCREEN_WIDHT/2-distance/2,Contant.SCREEN_HEIGHT/2+distance/2);
                MyLocation  loca3_4=new MyLocation(Contant.SCREEN_WIDHT/2+distance/2,Contant.SCREEN_HEIGHT/2+distance/2);

                mylocations.add(loca0_4);
                mylocations.add(loca1_4);
                mylocations.add(loca2_4);
                mylocations.add(loca3_4);
                break;

            case  5:

                MyLocation  loca0_5=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca1_5=new MyLocation(Contant.SCREEN_WIDHT/2+distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca2_5=new MyLocation(Contant.SCREEN_WIDHT/2-distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca3_5=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2+distance);
                MyLocation  loca4_5=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2-distance);
                mylocations.add(loca0_5);
                mylocations.add(loca1_5);
                mylocations.add(loca2_5);
                mylocations.add(loca3_5);
                mylocations.add(loca4_5);
                break;
            case  6:





                MyLocation  loca0_6=new MyLocation(Contant.SCREEN_WIDHT/2+distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca1_6=new MyLocation(Contant.SCREEN_WIDHT/2-distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca2_6=new MyLocation(Contant.SCREEN_WIDHT/2-distance/2,Contant.SCREEN_HEIGHT/2-triangleHeight);
                MyLocation  loca3_6=new MyLocation(Contant.SCREEN_WIDHT/2+distance/2,Contant.SCREEN_HEIGHT/2-triangleHeight);
                ///
                MyLocation  loca4_6=new MyLocation(Contant.SCREEN_WIDHT/2-distance/2,Contant.SCREEN_HEIGHT/2+triangleHeight);
                MyLocation  loca5_6=new MyLocation(Contant.SCREEN_WIDHT/2+distance/2,Contant.SCREEN_HEIGHT/2+triangleHeight);

                mylocations.add(loca0_6);
                mylocations.add(loca1_6);
                mylocations.add(loca2_6);
                mylocations.add(loca3_6);
                mylocations.add(loca4_6);
                mylocations.add(loca5_6);
                break;
            case  7:

                MyLocation  loca0_7=new MyLocation(Contant.SCREEN_WIDHT/2+distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca1_7=new MyLocation(Contant.SCREEN_WIDHT/2-distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca2_7=new MyLocation(Contant.SCREEN_WIDHT/2-distance/2,Contant.SCREEN_HEIGHT/2-triangleHeight);
                MyLocation  loca3_7=new MyLocation(Contant.SCREEN_WIDHT/2+distance/2,Contant.SCREEN_HEIGHT/2-triangleHeight);
                ///
                MyLocation  loca4_7=new MyLocation(Contant.SCREEN_WIDHT/2-distance/2,Contant.SCREEN_HEIGHT/2+triangleHeight);
                MyLocation  loca5_7=new MyLocation(Contant.SCREEN_WIDHT/2+distance/2,Contant.SCREEN_HEIGHT/2+triangleHeight);

                //
                MyLocation  loca6_7=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2);
                mylocations.add(loca0_7);
                mylocations.add(loca1_7);
                mylocations.add(loca2_7);
                mylocations.add(loca3_7);
                mylocations.add(loca4_7);
                mylocations.add(loca5_7);
                mylocations.add(loca6_7);


                break;
            case  8:

                triangleHeight= (float) (distance/Math.sqrt(2));

                MyLocation  loca0_8=new MyLocation(Contant.SCREEN_WIDHT/2+distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca1_8=new MyLocation(Contant.SCREEN_WIDHT/2-distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca2_8=new MyLocation(Contant.SCREEN_WIDHT/2-triangleHeight,Contant.SCREEN_HEIGHT/2-triangleHeight);
                MyLocation  loca3_8=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2-distance);
                MyLocation  loca4_8=new MyLocation(Contant.SCREEN_WIDHT/2+triangleHeight,Contant.SCREEN_HEIGHT/2-triangleHeight);
                MyLocation  loca5_8=new MyLocation(Contant.SCREEN_WIDHT/2+triangleHeight,Contant.SCREEN_HEIGHT/2+triangleHeight);
                MyLocation  loca6_8=new MyLocation(Contant.SCREEN_WIDHT/2-triangleHeight,Contant.SCREEN_HEIGHT/2+triangleHeight);
                //////////

                MyLocation  loca7_8=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2+distance);

                //
                mylocations.add(loca0_8);
                mylocations.add(loca1_8);
                mylocations.add(loca2_8);
                mylocations.add(loca3_8);
                mylocations.add(loca4_8);
                mylocations.add(loca5_8);
                mylocations.add(loca6_8);
                mylocations.add(loca7_8);


                break;
            case  9:

                triangleHeight= (float) (distance/Math.sqrt(2));

                MyLocation  loca0_9=new MyLocation(Contant.SCREEN_WIDHT/2+distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca1_9=new MyLocation(Contant.SCREEN_WIDHT/2-distance,Contant.SCREEN_HEIGHT/2);
                MyLocation  loca2_9=new MyLocation(Contant.SCREEN_WIDHT/2-triangleHeight,Contant.SCREEN_HEIGHT/2-triangleHeight);
                MyLocation  loca3_9=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2-distance);
                MyLocation  loca4_9=new MyLocation(Contant.SCREEN_WIDHT/2+triangleHeight,Contant.SCREEN_HEIGHT/2-triangleHeight);
                MyLocation  loca5_9=new MyLocation(Contant.SCREEN_WIDHT/2+triangleHeight,Contant.SCREEN_HEIGHT/2+triangleHeight);
                MyLocation  loca6_9=new MyLocation(Contant.SCREEN_WIDHT/2-triangleHeight,Contant.SCREEN_HEIGHT/2+triangleHeight);
                //////////

                MyLocation  loca7_9=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2+distance);
                MyLocation  loca8_9=new MyLocation(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2);

                //
                mylocations.add(loca0_9);
                mylocations.add(loca1_9);
                mylocations.add(loca2_9);
                mylocations.add(loca3_9);
                mylocations.add(loca4_9);
                mylocations.add(loca5_9);
                mylocations.add(loca6_9);
                mylocations.add(loca7_9);
                mylocations.add(loca8_9);


                break;



        }

    }
    private int factorial(int x){
        int total=1;
        if(x==0){
            total= 1;
        }
        else{
            for (int i=1; i<=x; i++){
                total*=i;
            }

        }

        return total;
    }
    private int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }
    private boolean isclickedbubblle(float x, float y, MyBubble bubble) {

        return  Math.abs( x-bubble.getXcenter())<bubble.getSize()  &&    Math.abs( y-bubble.getYcenter())<bubble.getSize();
    }
    private void clickonBubble(MyBubble bubble,int index) {
        bubble.resetbubble();
        if(isAddbubbletodownState(bubble)){
            mylocations.get(myBubbles.get(index).getLocationindex()).setUsedOK(-1);
            myBubbles.remove(index);




        }
        setColorundobutton();
    }
    private Boolean isAddbubbletodownState(MyBubble bubble) {

        if (!bubble.getId().equals(info.getSelected())){
            float distance=Contant.SCREEN_WIDHT/4;

            switch (downstatebubbles.size()){
                case 0:



                    MyBubble  down0=new MyBubble(Contant.SCREEN_WIDHT/2-distance,Contant.SCREEN_HEIGHT*37/40,bubble.getPaint().getColor(),bubble.getText(),bubble.getId(),-1);

                    down0.getPaint().setColor(lighter(color,0.5f));
                    down0.getStrokepaint().setColor(color);
                    down0.getTextpaint().setColor(color);
                    downstatebubbles.add(down0);
                    useranswer.add(bubble.getText());
                    info.setSelected(bubble.getId());

                    return  true;
                case 1:


                    MyBubble  down1=new MyBubble(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT*37/40,bubble.getColor(),bubble.getText(),bubble.getId(),-1);

                    down1.getPaint().setColor(lighter(color,0.5f));
                    down1.getStrokepaint().setColor(color);
                    down1.getTextpaint().setColor(color);
                    downstatebubbles.add(down1);
                    useranswer.add(bubble.getText());
                    info.setSelected(bubble.getId());
                    if (bubble.getText().equals("!")){

                        downstatebubbles.get(0).resetbubble();
                        downstatebubbles.get(0).setXOK(Contant.SCREEN_WIDHT/3);
                        downstatebubbles.get(1).setXOK(Contant.SCREEN_WIDHT*2/3);
                    }

                    return  true;

                case 2:

                    if (!downstatebubbles.get(1).getText().equals("!")){
                        MyBubble  down2=new MyBubble(Contant.SCREEN_WIDHT/2+distance,Contant.SCREEN_HEIGHT*37/40,bubble.getColor(),bubble.getText(),bubble.getId(),-1);

                        down2.getPaint().setColor(lighter(color,0.5f));
                        down2.getStrokepaint().setColor(color);
                        down2.getTextpaint().setColor(color);
                        downstatebubbles.add(down2);
                        useranswer.add(bubble.getText());
                        info.setSelected(bubble.getId());
                        return  true;
                    }
                    return  false;

                default:
                    return  false;
            }
        }
        return  false;

    }
    private void clickonequalbutton() {



        if (isCanCalculate()){



            int location=empty_locationindex();
            MyBubble  bubble=new MyBubble(mylocations.get(location).getXcenter(),mylocations.get(location).getYcenter(),color,Integer.toString(getAnswerResult()),"number",location);
            bubble.getPaint().setColor(lighter(color,0.5f));
            bubble.getStrokepaint().setColor(color);
            bubble.getTextpaint().setColor(color);

            if (myBubbles.size()==0){
                bubble.setXOKandYOK(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2);


                if (getAnswerResult()==getQestionResult()){




                    info.setSelected("operation");
                    info.setisSolved(true);

                    icon.setStart(System.currentTimeMillis());
                    deleteAll();



                }else{
                    myBubbles.add(bubble);
                }


            }else{
                myBubbles.add(bubble);
            }
            deleteDownState();
           /// setColorundobutton();
            setColorundobutton();
            info.setSelected("operation");
        }


    }
    private void deleteDownState() {
        for (int i=downstatebubbles.size()-1;i>=0;i--){

            downstatebubbles.remove(i);
        }
    }
    private boolean isCanCalculate() {

        switch (downstatebubbles.size()){
            case  1:
                return false;
            case  2:
                String  text1=downstatebubbles.get(0).getText();
                String  text2=downstatebubbles.get(1).getText();
                if (text2.equals("!")    &&   Integer.parseInt(text1)>=0){

                    return  true;
                }else  if (text2.equals("!")    &&   Integer.parseInt(text1)<0){


                    process.setMission(true);

                    //myPanel.reborn();
                    //myPanel.setId("nofitfactoriel");
                    //myPanel.setMaxHeightOK(Contant.SCREEN_HEIGHT/16);
                    //deleteText();
                    //MyShape shape=new MyShape(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2+Contant.SİZE,Contant.SİZE,Contant.COLOR,getContext().getString(R.string.ok_text),"confirmerror");
                    //myShapes.add(shape);
                    //MyMultilinetext   text=new MyMultilinetext(getContext().getString(R.string.nofitfactoriel_text),Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2-Contant.SCREEN_HEIGHT/20,Contant.SİZE/2, (int) (Contant.SCREEN_WIDHT/2));
                    //myMultilinetexts.add(text);

                    return  false;
                }else {
                    return  false;
                }


            case 3:
                int  number0=Integer.parseInt(downstatebubbles.get(0).getText());
                String  text0=downstatebubbles.get(1).getText();
                int   number1=Integer.parseInt(downstatebubbles.get(2).getText());


                if (text0.equals("÷")){

                    if (number1==0){


                        process.setMission(true);
                         myPanel.reborn();

                        myPanel.setId("error");
                        myPanel.setMaxHeightOK(Contant.SCREEN_HEIGHT/16);
                        myPanel.setMaxWitdhOK(Contant.SCREEN_WIDHT/4);
                        myPanel.getPaint().setColor(color);
                        myPanel.getPaint().setAlpha(150);
                        //deleteText();
                        //MyShape shape=new MyShape(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2+Contant.SİZE,Contant.SİZE,Contant.COLOR,getContext().getString(R.string.ok_text),"confirmerror");
                        //myShapes.add(shape);
                        MulLineText   text=new MulLineText(getContext().getString(R.string.zerodivision_error_expa),Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2-Contant.SCREEN_HEIGHT/20,Contant.SCREEN_WIDHT/30, (int) (Contant.SCREEN_WIDHT/2));
                        //lineTexts.add(text);

                        MulLineText   touch=new MulLineText(getContext().getString(R.string.touch_to_disabled_text),Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2+Contant.SCREEN_WIDHT/15,Contant.SCREEN_WIDHT/30, (int) (Contant.SCREEN_WIDHT/2));
                        //lineTexts.add(touch);
                        process.setMission(true);
                        return  false;
                    }else{

                        if (number0%number1==0){

                            return  true;

                        }else{

                            process.setMission(true);
                            myPanel.reborn();
                            myPanel.setId("error");
                            myPanel.setMaxHeightOK(Contant.SCREEN_HEIGHT/16);
                            myPanel.setMaxWitdhOK(Contant.SCREEN_WIDHT/4);
                            myPanel.getPaint().setColor(color);
                            myPanel.getPaint().setAlpha(150);
                            //deleteText();
                            //MyShape shape=new MyShape(Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2+Contant.SİZE,Contant.SİZE,Contant.COLOR,getContext().getString(R.string.ok_text),"confirmerror");
                            //myShapes.add(shape);
                             MulLineText   text=new MulLineText(getContext().getString(R.string.zerodivision_error_expa),Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2-Contant.SCREEN_HEIGHT/20,Contant.SCREEN_WIDHT/30, (int) (Contant.SCREEN_WIDHT/2));
                           // lineTexts.add(text);

                            process.setMission(true);

                            MulLineText   touch=new MulLineText(getContext().getString(R.string.touch_to_disabled_text),Contant.SCREEN_WIDHT/2,Contant.SCREEN_HEIGHT/2+Contant.SCREEN_WIDHT/15,Contant.SCREEN_WIDHT/30, (int) (Contant.SCREEN_WIDHT/2));
                            //lineTexts.add(touch);

                            return   false;
                        }



                    }


                }


                return  true;



        }



        return false;
    }
    private int getAnswerResult() {
        int  result=0;
        String  oparetion="null";

        if (useranswer.get(useranswer.size()-1).equals("!")){

            for (int i=useranswer.size()-2; i<=useranswer.size()- 1 ;i++){

                switch (useranswer.get(i)){

                    case "+":
                        oparetion="+";
                        break;
                    case  "-":
                        oparetion="-";
                        break;
                    case "x":
                        oparetion="x";
                        break;
                    case  "÷":
                        oparetion="÷";
                        break;
                    case  "^":
                        oparetion="^";
                        break;
                    case  "!":
                        result=factorial(result);
                        oparetion="!";
                        break;


                    default:

                        switch (oparetion){

                            case "+":
                                result=result+Integer.parseInt(useranswer.get(i));
                                break;
                            case  "-":
                                result=result-Integer.parseInt(useranswer.get(i));
                                break;
                            case "x":
                                result=result*Integer.parseInt(useranswer.get(i));
                                break;
                            case  "÷":
                                result= result/Integer.parseInt(useranswer.get(i));
                                break;
                            case  "^":
                                result= (int) Math.pow(result,Integer.parseInt(useranswer.get(i)));
                                break;
                            case  "null":
                                result=Integer.parseInt(useranswer.get(i));
                                break;
                            default:
                                break;
                        }
                        break;
                }
            }

        }else{
            for (int i=useranswer.size()-3; i<=useranswer.size()- 1 ;i++){


                Log.d("dd", "hesaplamalar: "+useranswer.get(i));
                switch (useranswer.get(i)){

                    case "+":
                        oparetion="+";
                        break;
                    case  "-":
                        oparetion="-";
                        break;
                    case "x":
                        oparetion="x";
                        break;
                    case  "÷":
                        oparetion="÷";
                        break;
                    case  "^":
                        oparetion="^";
                        break;
                    case  "!":
                        result=factorial(result);
                        oparetion="!";
                        break;


                    default:

                        switch (oparetion){

                            case "+":
                                result=result+Integer.parseInt(useranswer.get(i));
                                break;
                            case  "-":
                                result=result-Integer.parseInt(useranswer.get(i));
                                break;
                            case "x":
                                result=result*Integer.parseInt(useranswer.get(i));
                                break;
                            case  "÷":
                                result= result/Integer.parseInt(useranswer.get(i));
                                break;
                            case  "^":
                                result= (int) Math.pow(result,Integer.parseInt(useranswer.get(i)));
                                break;
                            case  "null":
                                result=Integer.parseInt(useranswer.get(i));
                                break;
                            default:
                                break;
                        }
                        break;
                }
            }
        }

        return  result;
    }
}
