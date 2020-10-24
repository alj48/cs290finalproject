package com.company;

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
    int nextGroup = 1;


    //This function checks whether the distance between 2 seats is at least 6 feet.
    //This function can be modified for improvement by making one foot NOT equal to 1 seat apart
    //Like how it would be in a real stadium
    private boolean is6Feet(int seatAx, int seatAy, int seatBx, int seatBy){
        return (Math.pow(Math.abs(seatAx-seatBx),2)+Math.pow(seatAy-seatBy,2)>=6);
    }


    //This function checks every seat from 6 in every direction.
    //If the distance is less than 6 and the seats are occupied by different groups, the seat is not safe.
    private boolean isSafe(int seatX, int seatY){
        for (int i =  Math.min(seatX-6,0); i < seatX+6 || seatX+6==size; i++){
            for (int j = Math.min(seatY-6,0);j < seatY+6 || seatY+6==size; i++){
                if (!is6Feet(seatX, seatY, i,j) && groupNum[seatX][seatY] != groupNum[i][j] && seatsStatus[i][j]==OCCUPIED) return false;
            }
            return false;
        }
        return true;
    }

    //This function checks to see if there are any usable seats left in a section
    private boolean isFull(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (seatsStatus[i][j]==FREE && isSafe(i,j)) return false;
            }
        }
        return true;
    }
    public boolean canFit(int groupSize){
        return (nextSpot(groupSize)[0]==-1);
    }

    public int[] nextSpot(int groupSize){
        int [] ret = new int[2];
        for (int i = 0; i < size-groupSize; i++){
            for (int j = 0; j < size; j++){
                for (int seat = i; seat <  + groupSize; seat++){
                    if (isSafe(seat,j)){
                        ret[0] = i;
                        ret[1] = 2;
                        return ret;
                    }
                }
            }
        }
        ret[0]=-1;
        ret[2]=-1;
        return ret;
    }



}
