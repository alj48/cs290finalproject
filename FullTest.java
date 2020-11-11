package com.company;

import java.util.ArrayList;

public class FullTest {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> test = new ArrayList<>();
        for (int q = 0; q < 20; q++)test.add(q);
        Stadium stadium = new Stadium(20,25);
        ArrayList<Request> requestList = new ArrayList<>();
        int [] stadiumPriceExpectation = new int[20];
        for (int i = 0; i < stadiumPriceExpectation.length; i++){
            stadiumPriceExpectation[i] = (int)(Math.random()*50 + 150);
        }
        double avg = 0.0;
        for (int i = 0; i < stadiumPriceExpectation.length; i++)avg += stadiumPriceExpectation[i];
        System.out.println("Average ticket price: " + avg/20);
        boolean[] used = new boolean[20];
        for (boolean b:used)b=false;
        for (int i = 0; i < 6000; i++){
            int gpSize = (int)(Math.random()*3)+1;
            Request r = new Request(i,gpSize);
            for (int j = 0; j < 20; j++){
                int x = test.get((int)(Math.random()*test.size()));
                int thisExpected = stadiumPriceExpectation[x];
                int willingToPay = (int)(Math.random()* .5*thisExpected + .75*thisExpected + (20.0-j/2));
                r.requestedSections.put(j,new int[]{x,willingToPay*gpSize});
                used[x]=true;
                test.remove(test.indexOf(x));
            }
            for (boolean b:used)b=false;
            for (int q = 0; q < 20; q++)test.add(q);
            requestList.add(r);
        }
        stadium.recieveRequests(requestList);
        stadium.giveSeating();


        System.out.println("Total money made: " + stadium.totalMoney);
        System.out.println("Total people sat: " + stadium.peopleSat);
        System.out.println("Money per ticket: " + (1.0*stadium.totalMoney/stadium.peopleSat));
        System.out.println("___________________________");
        for (Section sect:stadium.allSections){
            System.out.println("_________________________________________________________________________________");
            System.out.println();
            System.out.println();
            sect.printSection();
        }

        System.out.println("NOW WITH GREEDY");
        Stadium stadium2 = new Stadium(20,25);
        int money = 0;
        int people = 0;
        for (Request r:requestList){
            for (int i = 0; i < 20; i++){
                int requestedSect = r.requestedSections.get(i)[0];
                if (stadium2.allSections[requestedSect].canFit(r.groupSize)){
                    stadium2.allSections[requestedSect].place(r.groupSize,r.groupNum);
                    money += (stadiumPriceExpectation[requestedSect]*r.groupSize);
                    people += r.groupSize;
                    break;
                }
            }
        }
        System.out.println("Total money: " + money);
        System.out.println("People sat: " + people);
        System.out.println("Money paid per ticket: " + (1.0*money/people));

        System.out.println("DIFFERENCE: " + (stadium.totalMoney-money));
        System.out.println("DIFFERENCE PER TICKET: " + ((1.0*stadium.totalMoney/stadium.peopleSat)-(1.0*money/people)));

    }
}
