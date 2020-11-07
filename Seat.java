package com.company;

public class Seat {
//DECLARE VARIABLES

    //Seat status
    final int SEAT_OPEN = 0;
    final int SEAT_TAKEN = 1;
    final int NOT_A_SEAT = 2;

    //Seat location (within its section)
    int x;
    int y;

    //people in party
    int partySize;

    //party number
    int partyNum;
}
