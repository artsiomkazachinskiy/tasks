package com.third;

public class thirdmaintsk {
    public static void main(String[] args) {
        int size = ((int) (Math.random()*25));
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = ((int) (Math.random()*10));
        }
        for(int i = 0; i< size; i++){
            int choice =  ((int) (Math.random() * 2));

            if(choice == 0){
                System.out.println(array[i]);
            }
            if(choice == 1){
                System.out.print(array[i] + " ");
            }
        }
    }
}
