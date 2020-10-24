package com.company;

import java.util.*;

public class Stadium {
//DECLARE VARIABLES

    //Number of sections
    int numSecs;
    //All sections
    Section[] allSections;
    //All requests
    ArrayList<Request> allRequests = new ArrayList<>();
    //Size of sections
    int sectionSize;


    //below are a few constructor functions, so we can test with smaller sample sizes if needed.
    public Stadium(int numberOfSections){
        numSecs = numberOfSections;
        allSections = new int[numberOfSections];
        sectionSize = 10;
    }
    public Stadium(int numberOfSections, int sectionSize){
        numSecs = numberOfSections;
        allSections = new int[numberOfSections];
        this.sectionSize = sectionSize;
    }

    public Stadium(){
        numSecs = 20;
        allSections = new int[numSecs];
        sectionSize = 10;
    }

    //The following functions recieves the requests, either as a list or individual
    public void recieveRequests(ArrayList<Request> requests) {
        allRequests = requests;
    }

    public void oneMoreRequets(Request r){
        allRequests.add(r);
    }


    //This function gives the seating, this performs DA algorithm. YET TO FINISH.
    public void giveSeating(){

    }
}
