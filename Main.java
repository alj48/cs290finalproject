package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //DUMMY TEST CASE
        int howManySections = 3;
        int sectionSize = 5;
        Stadium ourStadium = new Stadium(howManySections,sectionSize);

        ArrayList<Request> list = new ArrayList<>();

        Request request0 = new Request(0,1);
        request0.requestedSections.put(0,{0,5});
        request0.requestedSections.put(1,{2,3});
        request0.requestedSections.put(2,{1,4});
        list.add(request0);

        Request request1 = new Request(1,3);
        request1.requestedSections.put(0,{1,6});
        request1.requestedSections.put(1,{0,4});
        request1.requestedSections.put(2,{2,1});
        list.add(request1);

        Request request2 = new Request(2,2);
        request2.requestedSections.put(0,{2,8});
        request2.requestedSections.put(1,{0,3});
        request2.requestedSections.put(2,{1,6});
        list.add(request2)

        Request request3 = new Request(3,1);
        request3.requestedSections.put(0,{0,6});
        request3.requestedSections.put(1,{1,3});
        request3.requestedSections.put(2,{2,2});
        list.add(request3);
        ourStadium.recieveRequests(list);

//        for (int i = 0; i < 3; i++){
//            int a = (int)Math.random()*3;
//            int b = (int)Math.random()*10;
//
//            request1.requestedSections.put(i,)
//        }

    }
}
