package com.company;

import java.util.HashMap;

public class Request {

    int groupNum;

    int groupSize;


    boolean fulfilled;

    boolean assigned = false;


    //The key is the pref number, the value is int array with the first being the section number, the second being the amount willing to pay
    HashMap<Integer, Integer[]> requestedSections;

    public Request(groupNum,groupSize){
        this.groupNum=groupNum;
        this.groupSize=groupSize;
        requestedSections = new HashMap<>();
    }
}
