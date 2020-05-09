package com.FirstOptionalTask;

import java.util.Scanner;

public class FirstOptionalTask {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter array size...");
        int ArraySize = in.nextInt();
        int max = 0;
        int min = 0;
        int LengthMax = 0;
        int LengthMin = 10;
        int average = 0;
        Integer[] array = new Integer[ArraySize];
        for(int i = 0; i < ArraySize; i++){
            System.out.println("Enter number...");
            array[i] = in.nextInt();
        }

        for(int i = 0; i < ArraySize; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.println("");
        for(int i = 0; i < ArraySize ; i++){
            int length = String.valueOf(array[i]).length();
            if(LengthMax <= length){
                max = array[i];
                LengthMax = length;
            }
            if(LengthMin > length){
                min = array[i];
                LengthMin = length;
            }
        }
        System.out.println("Max = " + max + "; Length = " + LengthMax);
        System.out.println("Min = " + min + "; Length = " + LengthMin);

        for(int i = 0; i < ArraySize ; i++) {
            int length = String.valueOf(array[i]).length();
            average += length;
        }
        average = average/ArraySize;
        System.out.print("Less than average: ");
        for(int i = 0; i < ArraySize; i++){
            int length = String.valueOf(array[i]).length();
            if(length <= average){
                System.out.print(array[i] + ", ");
            }
        }
        System.out.println("");
        System.out.print("More than average: ");
        for(int i = 0; i < ArraySize; i++){
            int length = String.valueOf(array[i]).length();
            if(length > average){
                System.out.print(array[i] + ", ");
            }
        }
    }
}
