package com.company;

import java.util.*;

public class Stadium {
//DECLARE VARIABLES

    int peopleSat = 0;

    //Number of sections
    int numSecs;
    //All sections
    Section[] allSections;
    //All requests
    ArrayList<Request> allRequests = new ArrayList<>();
    //Size of sections
    int sectionSize;

    int totalMoney = 0;

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
        for (int i = 0; i < numSecs; i++){
            allSections[i] = new Section(sectionSize);
        }
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
    public boolean allSectionsFull(){
        for (int i = 0; i < numSecs; i++){
            Section section = allSections[i];
            if (!section.isFull())return false;
        }
        return true;
    }

    //This function gives the seating, this performs DA algorithm. YET TO FINISH.
    public void giveSeating(){

        int [][] customerRequests = getCustomerRequests();
        int [][] sectionRequests = getSectionRequests();

        int numPeopleMatched = 0;
        int numMatched = 0;
        int round = 0;

        //Prepare all of the data
        ArrayList<Integer>[]test = new ArrayList[numSecs];
        for (int i = 0; i < numSecs; i++) test[i]=new ArrayList<>();

        boolean [] isMatched = new boolean[allRequests.size()];
        for (int i = 0; i < allRequests.size(); i++)isMatched[i]=false;

        ArrayList<Integer>[] acceptances = new ArrayList[numSecs];
        for (int i = 0; i < numSecs; i++)acceptances[i]=new ArrayList<>();
        while(numMatched<allRequests.size() && !allSectionsFull() && round<20){
            for (int request = 0; request < allRequests.size();request++){
                if(!allRequests.get(request).assigned){
                    test[customerRequests[request][round]].add(allRequests.get(request).groupNum);
                }
            }
            for(int section = 0; section < allSections.length; section++){
                for(int request = 0; request < allRequests.size(); request++){
                    if (test[section].contains(sectionRequests[section][request]) && allSections[section].canFit(allRequests.get(request).groupSize)) {
                        if (!allRequests.get(request).assigned){
                            allSections[section].place(allRequests.get(request).groupSize,allRequests.get(request).groupNum);
                            numMatched++;
                            numPeopleMatched += allRequests.get(request).groupSize;
                            allRequests.get(request).assigned=true;
                            for (int i:allRequests.get(request).requestedSections.keySet()){
                                if (allRequests.get(request).requestedSections.get(i)[0]==section){
                                    totalMoney += allRequests.get(request).requestedSections.get(i)[1];//*allRequests.get(request).groupSize;
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < numSecs; i++){
                test[i].clear();
            }
            round++;
//            System.out.println(round + " " + numMatched + " " + numPeopleMatched);
        }
        peopleSat=numPeopleMatched;


//        System.out.println();
//        int samplenum = 0;
//        for (int i = 0; i < numSecs; i++){
//            allSections[i] = new Section(sectionSize);
//        }
//        System.out.println(allSections[0]);
//        //This function uses DA algorithm to place people into their seatings based on preferences.
//
//        //Get preferences in order
//        int [][] customerRequests = getCustomerRequests();
//        int [][] sectionRequests = getSectionRequests();
//        ArrayList<Integer> [] acceptances = new ArrayList[numSecs];
//
//        for (int i = 0; i < numSecs; i++){
//            acceptances[i] = new ArrayList<>();
//        }
//        ArrayList<Integer> [] test = new ArrayList[numSecs];
//        for (int i = 0; i < numSecs; i++)test[i] = new ArrayList<>();
//        int numMatched = 0;
//        int round = 0;
//        System.out.println(allSectionsFull());
//        while (numMatched < allRequests.size() && !allSectionsFull()){
//            for (int i = 0; i < allRequests.size(); i++){
//                test[customerRequests[i][round]].add(allRequests.get(i).groupNum);
//            }
//
//            for (int section = 0; section < numSecs; section++){
//                for (int request = 0; request < allRequests.size(); request++){
//                    if (test[section].contains(sectionRequests[section][request]) && allSections[section].canFit(allRequests.get(request).groupSize)){
//                        boolean bools = false;
//                        for(int k = 0; k < numSecs; k++) {
//                            if(acceptances[k].contains(sectionRequests[section][request])) {
//                                int counter = 0;
//                                int counter2 = 0;
//                                for(int a : acceptances[k]) {
//                                    if(a == sectionRequests[section][request]) {
//                                        counter2 = counter;
//                                    }
//                                    counter++;
//                                }
//                                //What happens if something needs to be removed
//                                acceptances[k].remove(counter2);
//                                allSections[k].remove(counter2);
//                                for (int key:allRequests.get(counter2).requestedSections.keySet()){
//                                    if (allRequests.get(counter2).requestedSections.get(key)[0]==k){
//                                        totalMoney = totalMoney - allRequests.get(counter2).requestedSections.get(key)[1];
//                                        samplenum-=allRequests.get(counter2).groupSize;
//                                    }
//                                }
//                                bools = true;
//                                break;
//                            }
//                        }
//                        if(bools == false) {
//                            acceptances[section].add(sectionRequests[section][request]);
//                            allSections[section].place(allRequests.get(request).groupSize,allRequests.get(request).groupNum);
//                            samplenum+=allRequests.get(request).groupSize;
//                            for (int i:allRequests.get(request).requestedSections.keySet()){
//                                if(allRequests.get(request).requestedSections.get(i)[0] ==section){
//                                    totalMoney += allRequests.get(request).requestedSections.get(i)[1];
//                                    allRequests.get(request).assigned=true;
//                                }
//                            }
//                            numMatched++;
//                        }
//                    }
//                }
//            }
//            for (int i = 0; i < 10; i++){
//                test[i].clear();
//            }
//            round++;
//            System.out.println(round + " " + numMatched + " " + (allRequests.size()-numMatched) + " " + (numMatched < allRequests.size()) + " " + !allSectionsFull());
//        }
//        System.out.println(numMatched + " " + samplenum);
////        for (int i = 0; i < allSections.length; i++)System.out.println(i + " " + allSections[i].canFit(1));


    }
}
