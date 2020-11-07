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
        allSections = new Section[numberOfSections];
        sectionSize = 10;
    }
    public Stadium(int numberOfSections, int sectionSize){
        numSecs = numberOfSections;
        allSections = new Section[numberOfSections];
        this.sectionSize = sectionSize;
    }

    public Stadium(){
        numSecs = 20;
        allSections = new Section[numSecs];
        sectionSize = 10;
    }

    //The following functions reciev es the requests, either as a list or individual
    public void recieveRequests(ArrayList<Request> requests) {
        allRequests = requests;
    }

    public void oneMoreRequets(Request r){
        allRequests.add(r);
    }

    //The following function puts the customer requests into a 2d array so DA algorithm can be implemented with it.
    public int [][] getCustomerRequests(){
        int [][] ret  = new int[allRequests.size()][numSecs];
        for (Request r:allRequests){
            for (int i = 0; i < numSecs; i++){
                ret[r.groupNum][i]=r.requestedSections.get(i)[0];
            }
        }
        return ret;
    }



    public int[][] getSectionRequests(){

        int [][] ret = new int[numSecs][allRequests.size()];

        Set<Integer> tmpList = new HashSet<>();
        ArrayList<Integer> aslist = new ArrayList<>();
        HashMap<Integer,Integer> sortMap = new HashMap<>();
        for (int i = 0; i < numSecs; i++){
            aslist.clear();
            tmpList.clear();
            sortMap.clear();
            for (Request r:allRequests){
                for (int [] p:r.requestedSections.values()){
                    if (p[0]==i){
                        sortMap.put(p[1],r.groupNum);
                        aslist.add(p[1]);
                    }
                }
            }
            //Below I am sorting the different values into an array list, and the values will be
            tmpList = sortMap.keySet();
            Collections.sort(aslist,Collections.reverseOrder());
            int ctr = 0;
            for (int j = 0; j < allRequests.size(); j++){
                ret[i][j]=sortMap.get(aslist.get(ctr));
                ctr++;
            }
        }
        return ret;
    }


    //This function gives the seating, this performs DA algorithm. YET TO FINISH.
    public void giveSeating(){
        //This function uses DA algorithm to place people into their seatings based on preferences.

        //Get capacities - this will be based on, for each individual request, whether or not there is space


        int numGiven = 0;
        while (numGiven < allSections.length){
            for (int i = 0; i < allSections.length; i++){

            }
        }
        //Get preferences in order
        int [][] customerRequests = getCustomerRequests();
        int [][] sectionRequests = getSectionRequests();
        ArrayList<Integer> [] acceptances = new ArrayList[numSecs];
        for (int i = 0; i < numSecs; i++){
            acceptances[i] = new ArrayList<>();
        }

        ArrayList<Integer> [] test = new ArrayList[numSecs];
        int numMatched = 0;
        int round = 0;
        while (numMatched < allRequests.size()){
            for (int i = 0; i < allRequests.size(); i++){
                test[customerRequests[i][round]].add(allRequests.get(i).groupNum);
            }
            for (int section = 0; section < numSecs; section++){
                for (int j = 0; j < allRequests.size(); j++){
                    if (test[section].contains(sectionRequests[section][j]) && allSections[section].canFit(allRequests.get(j).groupSize)){
                        boolean bool = false;
                        for(int k = 0; k < 10; k++) {
                            if(acceptances[k].contains(sectionRequests[section][j])) {
                                int counter = 0;
                                int counter2 = 0;
                                for(int a : acceptances[k]) {
                                    if(a == sectionRequests[section][j]) {
                                        counter2 = counter;
                                    }
                                    counter++;
                                }
                                acceptances[k].remove(counter2);
                                bool = true;
                                break;
                            }
                        }
                        if(bool == false) {
                            acceptances[section].add(sectionRequests[section][j]);
                            numMatched++;
                        }
                    }
                }
            }
            for (int i = 0; i < 10; i++){
                test[i].clear();
            }
            round++;
        }


    }
}
