package com.example.springforumapp.util;

public class IdRandomGenerator {

    public static int getRandomIntegerId() {
        return (int) ((Math.random() * (2147483647 - 1147483647)) + 1147483647);
    }
}
