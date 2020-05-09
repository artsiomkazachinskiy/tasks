package com.secondmaintask;

import java.util.Scanner;

public class secondmaintask {
    public static void main(String[] args) {
        int n;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter array size...");
        n = in.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            System.out.println("Enter number...");
            array[i] = in.nextInt();
        }
        System.out.println("");
        for(int i = (n - 1); i >=0; i--){
            System.out.print(array[i] + ", ");
        }
    }
}
