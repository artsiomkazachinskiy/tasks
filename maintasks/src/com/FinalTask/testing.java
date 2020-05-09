package com.FinalTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class testing {
    public static void main(String[] args) {
        customer customer;
        List<customer> customers = new ArrayList<customer>();
        System.out.println("Enter customer? Choice YES or NO...");
        Scanner in = new Scanner(System.in);
        String answer1 = "YES";
        String answer2 = "NO";
        String answ = in.next();
        while (true) {
            if (answ.equals(answer1)) {
                customer = new customer();
                System.out.println("Enter Id...");
                int l = in.nextInt();
                customer.setId(l);
                System.out.println("Enter lastname...");
                String ln = in.next();
                customer.setLastName(ln);
                System.out.println("Enter firstname...");
                String fn = in.next();
                customer.setFirstName(fn);
                System.out.println("Enter secondname...");
                String sn = in.next();
                customer.setSecondName(sn);
                System.out.println("Enter address...");
                String addr = in.next();
                customer.setAddress(addr);
                System.out.println("Enter number of credit card...");
                int a = in.nextInt();
                customer.setNcard(a);
                System.out.println("Enter number of bank account...");
                int g = in.nextInt();
                customer.setNbank(g);
                customers.add(customer);
                System.out.println("Enter customer? Choise YES or NO...");
                answ = in.next();
            } else if (answ.equals(answer2)) {
                System.out.println("Exit...");
                break;
            }
        }
        Collections.sort(customers, com.FinalTask.customer::compareTo);

        for(customer i:customers){
            System.out.println(i.toString());
        }

        System.out.println("Enter first number of card...");
        int card1 = in.nextInt();
        System.out.println("Enter second number of card...");
        int card2 = in.nextInt();
        for(customer i:customers) {
            if (i.getNcard() >= card1 && i.getNcard() <= card2) {
                System.out.println(i.toString());
            }
        }
    }
}

