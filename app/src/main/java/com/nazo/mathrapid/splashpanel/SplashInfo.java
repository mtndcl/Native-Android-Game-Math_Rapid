package com.nazo.mathrapid.splashpanel;

/**
 * Created by mockingbird on 7/3/2018.
 */

public class SplashInfo {





    private  int count;

    private  int alpha;

    SplashInfo(){



        this.count=0;
        this.alpha=0;

    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = this.count+ count;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha =   this.alpha+alpha;
    }
}
