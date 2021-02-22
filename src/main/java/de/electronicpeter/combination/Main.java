package de.electronicpeter.combination;

public class Main {
    public static void main(String[] args) {
        int numberOfElements = Integer.valueOf(args[0]);
        int groupSize = Integer.valueOf(args[1]);

        new Combination().createCombinations(numberOfElements, groupSize);
    }
}
