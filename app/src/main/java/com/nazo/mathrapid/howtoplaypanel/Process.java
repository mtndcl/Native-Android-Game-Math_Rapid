package com.nazo.mathrapid.howtoplaypanel;

/**
 * Created by mockingbird on 6/20/2018.
 */

public class Process {


    private Boolean init;
    private Boolean mission;



    Process() {


        this.init = true;
        this.mission = false;

    }


    public Boolean getInit() {
        return init;
    }

    public void setInit(Boolean init) {
        this.init = init;
    }

    public Boolean getMission() {
        return mission;
    }

    public void setMission(Boolean mission) {
        this.mission = mission;
    }

}
