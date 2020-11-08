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
        return (Math.pow(Math.abs(seatAx-seatBx),2)+Math.pow(seatAy-seatBy,2)>=36);
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
        return (nextSpot(groupSize)[0]==-1);
    }

    public void place(int groupSize){
        int [] blah = nextSpot(groupSize);
        if (blah[0]==-1) {
            System.out.println("CANNOT PLACE HERE");
            return;
        }
        for (int i = 0; i < groupSize; i++){
            seatsStatus[blah[0]][blah[1]-i] = OCCUPIED;
            groupNum[blah[0]][blah[1]-i] = nextGroup;
        }
        nextGroup++;
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
//        int [] ret = new int[2];
//
//        boolean works = false;
//        for (int i = 0; i < size; i++){
//            for (int j = 0; j < size-gpSize; j++){
//                works = true;
//                for (int k = 0; k < gpSize; k++){
//                    if (!isSafe(i,j+k)){
//                        works = false;
//                        break;
//                    }
//                    else{
//                        if (k==gpSize-1 && works){
//                            ret[0]=i;
//                            ret[1]=j+k+1;
//                        }
//                    }
//                }
//            }
//        }
        return ret;
    }

    public void printSection(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(seatsStatus[i][j]);
            }
            System.out.println();
        }
    }
}
