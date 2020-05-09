package com.fourthmaintask;

import java.util.Scanner;

public class fourthmaintask {
    public static void main(String[] args) {
        int size;
        int sum = 0;
        int umn = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter array size...");
        size = in.nextInt();
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            System.out.println("Enter number...");
            array[i] = in.nextInt();
            sum += array[i];
            umn *= array[i];
        }
        System.out.println("Enter 1 to + ; Enter 2 to * ; ... ");
        int YourAnswer = in.nextInt();
        if(YourAnswer == 1){
            System.out.println(sum);
        }
        if(YourAnswer == 2){
            System.out.println(umn);
        }
        else {
            System.out.println("Exit...");
        }
    }
}
