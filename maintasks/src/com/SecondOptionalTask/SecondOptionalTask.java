package com.SecondOptionalTask;

import java.util.Scanner;

public class SecondOptionalTask {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int FirstEl = 0;
        int SecondEL = 0;

        System.out.println("Enter matrix size...");
        int size = in.nextInt();
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++){
            System.out.println("");
            for(int j = 0; j < size; j++){
                matrix[i][j] = ((int) (Math.random()*(20 + 1)) - 10);
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println("");
        for(int i = 0; i < size; i++){
            int sum = 0;
            for(int j = 0; j < size; j++){
                if(matrix[i][j] > 0 ){
                    FirstEl = j;
                    break;
                }

            }
            for(int j = 0; j < size; j++){
                if(matrix[i][j] > 0 && FirstEl < j){
                    SecondEL = j;
                    break;
                }

            }
            if(FirstEl > SecondEL){
                SecondEL = FirstEl;
            }
            //System.out.println("1: " + FirstEl + " 2: " + SecondEL);

            for(int j = FirstEl; j <= SecondEL; j++){
                sum += matrix[i][j];
            }
            System.out.println("Amount: " + sum);

        }
    }
}
