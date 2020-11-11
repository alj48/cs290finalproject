package com.company;

import java.util.HashMap;

public class Section {
//DECLARE VARIABLES

    //Section number
    int sect;

    //Size of section, going to assume square even though it is not always the case (This can be easily changed)
    int size;

    //All seats, int 0-2. 0 means not taken, 1 means taken, 2 means
    int [][] seatsStatus;
    final int FREE = 0;
    final int OCCUPIED = 1;
    final int NOSEAT = 2;

    //store the group number so we can make sure you can sit next to a fellow party member
    int [][] groupNum;

    //store the average price for this section requested
    double avgPrice;

    //store the next group (so we can keep it counting
    int nextGroup = 0;

    //constructor function, sets everything up
    public Section(int size){
        this.size = size;
        groupNum = new int[size][size];
        seatsStatus = new int[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                groupNum[i][j]=-1;
            }
        }
    }

    //This function checks whether the distance between 2 seats is at least 6 feet.
    //This function can be modified for improvement by making one foot NOT equal to 1 seat apart
    //Like how it would be in a real stadium
    public boolean is6Feet(int seatAx, int seatAy, int seatBx, int seatBy){
        int xdiff = Math.abs(seatAx-seatBx);
        double xscaled = xdiff*(26/12);
        double xsquared = Math.pow(xscaled,2);
        int ydiff = Math.abs(seatAy-seatBy);
        double yscaled = ydiff*(16/12);
        double ysquared = Math.pow(yscaled,2);
        return (xsquared+ysquared>=36);
    }


    //This function checks every seat from 6 in every direction.
    //If the distance is less than 6 and the seats are occupied by different groups, the seat is not safe.
    public  boolean isSafe(int seatX, int seatY){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (!is6Feet(seatX,seatY,i,j) && groupNum[seatX][seatY] != groupNum[i][j] && seatsStatus[i][j] == OCCUPIED && groupNum[i][j]!=-1){
                    return false;
                }
            }
        }
        return true;
    }

    //This function checks to see if there are any usable seats left in a section
    public boolean isFull(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (seatsStatus[i][j]==FREE && isSafe(i,j)) return false;
            }
        }
        return true;
    }

    public boolean canFit(int groupSize){
        return (nextSpot(groupSize)[0]!=-1);
    }

    public void place(int groupSize, int gpNum){
        int [] blah = nextSpot(groupSize);
        if (blah[0]==-1) {
            return;
        }
        for (int i = 0; i < groupSize; i++){
            seatsStatus[blah[0]][blah[1]-i] = OCCUPIED;
            groupNum[blah[0]][blah[1]-i] = gpNum;
        }
    }

    public int[] nextSpot(int gpSize){
        int [] ret = new int[2];
        ret[0]=-1;
        ret[1]=-1;
        int inARow = 0;
        for (int i = 0; i < size; i++){
            inARow = 0;
            for (int j = 0; j < size; j++){
                if (isSafe(i,j) && seatsStatus[i][j]!=1)inARow++;
                else{
                    inARow=0;
                    continue;
                }
                if (inARow==gpSize){
                    ret[0] = i;
                    ret[1] = j;
                }
            }
        }
        return ret;
    }

    public void remove(int gpNum){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (groupNum[i][j]==gpNum){
                    groupNum[i][j]=-1;
                    seatsStatus[i][j]=FREE;
                }
            }
        }
    }

    public void printSection(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(groupNum[i][j] + " | ");
            }
            System.out.println();
        }
    }
}
