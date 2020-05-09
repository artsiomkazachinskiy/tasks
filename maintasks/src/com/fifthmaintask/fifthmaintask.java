package com.fifthmaintask;

import java.util.Scanner;

public class fifthmaintask {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int YourNumber;
        System.out.println("Enter your number...");
        YourNumber = in.nextInt();
        String[] array = new String[12];
        array[0] = "January";
        array[1] = "February";
        array[2] = "March";
        array[3] = "April";
        array[4] = "May";
        array[5] = "June";
        array[6] = "July";
        array[7] = "August";
        array[8] = "September";
        array[9] = "October";
        array[10] = "November";
        array[11] = "December";

        for(int i = 0; i < 12; i ++){
            if(YourNumber == (i+1)){
                System.out.println(array[i]);
            }
        }
    }
}
