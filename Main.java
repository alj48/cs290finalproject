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
        request0.requestedSections.put(0,new int[]{0,5});
        request0.requestedSections.put(1,new int[]{2,3});
        request0.requestedSections.put(2,new int[]{1,4});
        list.add(request0);

        Request request1 = new Request(1,3);
        request1.requestedSections.put(0,new int[]{1,6});
        request1.requestedSections.put(1,new int[]{0,4});
        request1.requestedSections.put(2,new int[]{2,1});
        list.add(request1);

        Request request2 = new Request(2,2);
        request2.requestedSections.put(0,new int[]{2,8});
        request2.requestedSections.put(1,new int[]{0,3});
        request2.requestedSections.put(2,new int[]{1,6});
        list.add(request2);

        Request request3 = new Request(3,1);
        request3.requestedSections.put(0,new int[]{0,6});
        request3.requestedSections.put(1,new int[]{1,3});
        request3.requestedSections.put(2,new int[]{2,2});
        list.add(request3);
        ourStadium.recieveRequests(list);

        System.out.println("TESTING Stadium.getSectionRequests()");
        int [][] test = ourStadium.getSectionRequests();
        for (int i = 0; i < test.length; i++){
            for (int j = 0; j < test[0].length; j++){
                System.out.print(test[i][j] + " " );
            }
            System.out.println();
        }
        System.out.println("_________________________");
        System.out.println("TESTING Stadium.getCustomerRequests()");
        int [][] test2 = ourStadium.getCustomerRequests();
        for (int i = 0; i < test2.length; i++){
            for (int j = 0; j < test2[0].length; j++){
                System.out.print(test2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("_________________________");
        System.out.println("TESTING Section.is6Feet");
        Section mysection = new Section(10);

        System.out.println("0,0 & 6,6: " + mysection.is6Feet(0,0,6,6));
        System.out.println("1,1 & 2,2: " + mysection.is6Feet(1,1,2,2));


        System.out.println("_________________________");
        System.out.println("TESTING Section.isSafe");
        System.out.println(mysection.isSafe(0,0));

        System.out.println("_________________________");
        System.out.println("TESTING Section.place");

        System.out.println("Placing 3 person group:");
        int [] tmp = new int[2];
        tmp = mysection.nextSpot(3);
        System.out.println(tmp[0] + " " + tmp[1]);
        mysection.place(3);


        for (int i = 0; i < mysection.size; i++){
            for (int j = 0; j < mysection.size; j++){
                System.out.print(mysection.seatsStatus[i][j]);
            }
            System.out.println();
        }
        System.out.println("NEXT SPOT");
        System.out.println("Placing 1 person group");

        tmp = mysection.nextSpot(1);
        System.out.println(tmp[0] + " " + tmp[1]);
        mysection.place(1);


        for (int i = 0; i < mysection.size; i++){
            for (int j = 0; j < mysection.size; j++){
                System.out.print(mysection.seatsStatus[i][j]);
            }
            System.out.println();
        }
        System.out.println("NEXT SPOT");
        System.out.println("Placing 2 person group");

        tmp = mysection.nextSpot(2);
        System.out.println(tmp[0] + " " + tmp[1]);
        mysection.place(2);


        for (int i = 0; i < mysection.size; i++){
            for (int j = 0; j < mysection.size; j++){
                System.out.print(mysection.seatsStatus[i][j]);
            }
            System.out.println();
        }



        System.out.println("NEXT SPOT");
        System.out.println("Placing 4 person group");

        tmp = mysection.nextSpot(3);
        System.out.println(tmp[0] + " " + tmp[1]);
        mysection.place(3);


        for (int i = 0; i < mysection.size; i++){
            for (int j = 0; j < mysection.size; j++){
                System.out.print(mysection.seatsStatus[i][j]);
            }
            System.out.println();
        }




    }
}
