package com.company;

import java.util.Random;

class RandomUtils {
    public static boolean generateRandomBoolean(double trueProbability) {
        return Math.random() < trueProbability;
    }


    public static int generateRandomInteger(int from, int to) {
        Random rand = new Random();
        return rand.nextInt(to - from + 1) + from;
    }
}