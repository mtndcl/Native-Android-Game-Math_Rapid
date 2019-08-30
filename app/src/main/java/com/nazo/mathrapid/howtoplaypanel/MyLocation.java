package com.nazo.mathrapid.howtoplaypanel;

/**
 * Created by mockingbird on 3/11/2018.
 */



/**
 * Created by mockingbird on 3/11/2018.
 */

public class MyLocation {



    private  float xcenter;
    private  float ycenter;
    private  int used;


    public  MyLocation(float xcenter,float ycenter){
        this.xcenter=xcenter;
        this.ycenter=ycenter;
        this.used=-1;


    }

    public float getXcenter() {
        return xcenter;
    }


    public float getYcenter() {
        return ycenter;
    }

    public int getUsed() {
        return used;
    }

    public void setUsedOK(int used) {
        this.used = used;
    }










}
