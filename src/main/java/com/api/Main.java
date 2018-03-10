package com.api;


public class Main {
    
    
    public static void main(String args[]) {
        System.out.println("dupa");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted on sleep.");
        }
    }
    
}
