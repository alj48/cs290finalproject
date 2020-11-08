package com.company;
import java.util.*;
import java.io.*;

public class TestWithHospitals {
    public static void main(String[] args) throws Exception {
        File f = new File("residents.csv");
        Scanner sc = new Scanner(f);
        int pref = 0;
        int ctr = 0;
        ArrayList<Request> requestList = new ArrayList<>();
        while (sc.hasNext()){
            String line = sc.next();
            pref = 0;
            Request r = new Request(ctr,1);
            for (String i:line.split(",")){
                r.requestedSections.put(pref,new int[] {Integer.parseInt(i),(int)Math.random()*20});
                pref++;
            }
            requestList.add(r);

        }
        Stadium stadium = new Stadium(10,25);
        sc = new Scanner(new File("hospitals.csv"));
        ctr = 0;
        while(sc.hasNext()){
            String line = sc.next();

        }


    }
}
