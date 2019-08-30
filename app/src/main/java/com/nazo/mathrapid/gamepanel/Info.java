package com.nazo.mathrapid.gamepanel;

/**
 * Created by mockingbird on 6/22/2018.
 */

import android.graphics.Paint;



        import android.content.Context;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Typeface;


/**
 * Created by mockingbird on 3/10/2018.
 */

public class Info {



    private Paint paint;
    private  long  starttime;



    private  Boolean undo;





    private  int askedquestion;
    private  int   skipnumber;

    private   Boolean  gameover;


    private   int back;
    private  Boolean  useskip;


    private   int undonumber;
    private  String  selected;
    private  Boolean  isSolved;
    private  Float remainskiptime;
    private  long atime;
    private   long questiontime;
    private  Boolean pass;

    Info(){







        this.pass=true;
        this.questiontime=System.currentTimeMillis();
        this.back=0;
        this.undonumber=0;


        this.remainskiptime=0f;
        this.undo=false;
        this.askedquestion=0;
        this.gameover=false;
        this.isSolved=false;
        this.skipnumber=0;


        this.useskip=true;

        this.paint=new Paint();

        this.selected="operation";

        paint.setTextAlign(Paint.Align.CENTER);


        paint.setTypeface(paint.setTypeface(Typeface.create("Brush Script MT",Typeface.BOLD)));

        this.starttime=System.currentTimeMillis();
    }


    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public long getQuestiontime() {


        return  (System.currentTimeMillis()-this.questiontime)/1000;
    }

    public void setQuestiontime(long questiontime) {
        this.questiontime = questiontime;
    }

    public int getUndonumber() {
        return undonumber;
    }

    public void setUndonumber(int undonumber) {
        this.undonumber = this.undonumber + undonumber;
    }
    public void setUndonumberOK(int undonumber) {
        this.undonumber = undonumber;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back =this.back + back;
    }
    public void setBackOK(int back) {
        this.back = back;
    }
    public Boolean getUseskip() {
        return useskip;
    }

    public Float getRemainskiptime() {


        float  passedtime=(System.currentTimeMillis()-this.atime)/1000;
        return remainskiptime-passedtime;
    }



    public void  setremainskiptimeOK(float time){

        this.atime=System.currentTimeMillis();
        this.remainskiptime=time;
    }
    public void setUseskip(Boolean useskip) {
        this.useskip = useskip;
    }

    public int getAskedquestion() {
        return askedquestion;
    }

    public void setAskedquestionOK(int askedquestion) {
        this.askedquestion = askedquestion;
    }
    public void setAskedquestion(int askedquestion) {
        this.askedquestion = this.askedquestion+askedquestion;
    }

    public Boolean getGameover() {
        return gameover;
    }

    public void setGameover(Boolean gameover) {
        this.gameover = gameover;
    }

    public Boolean getUndo() {
        return undo;
    }

    public void setUndo(Boolean undo) {
        this.undo = undo;
    }

    public String getSelected() {
        return selected;
    }



    public Boolean getisSolved() {
        return isSolved;
    }

    public void setisSolved(Boolean solved) {
        isSolved = solved;
    }


    public int getskipnumber() {
        return skipnumber;
    }

    public void setskipnumber(int skipnumber) {
        this.skipnumber = this.skipnumber+skipnumber;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }







    public Paint getPaint() {
        return paint;
    }




    private int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    public   float  getpassedTime(){


       return  (System.currentTimeMillis() - this.starttime)/1000;
    }

   // public String getRemaintime() {


       // String remaintime=Long.toString(option*60-( System.currentTimeMillis() - this.starttime)/1000);



     //   textYlocation = ((ycenter) - ((paint.descent() + paint.ascent()) / 2));

        //return String.valueOf(remaintime);
     //   return  "d";
    //}

    public void seStartTime() {

        this.starttime=System.currentTimeMillis();
    }

    public void setskipnumberOK(int skipnumberOK) {
        this.skipnumber = skipnumberOK;
    }







   /* public String getRemainQuestions() {



        String  remainQuestion=Integer.toString(option-solvedQuestion-skipnumber);
        textYlocation = ((ycenter) - ((paint.descent() + paint.ascent()) / 2));
        return remainQuestion;
    }*/
}
