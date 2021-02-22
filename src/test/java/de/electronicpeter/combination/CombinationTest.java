package de.electronicpeter.combination;

import org.junit.jupiter.api.Test;

public class CombinationTest {
    @Test
    public void a() {
        for (int i = 4; i <= 9; i++) {
            new Combination().createCombinations(i);
        }
        new Combination().createCombinations(9,5);
        new Combination().createCombinations(14);
        new Combination().createCombinations(15);
        new Combination().createCombinations(16);
        new Combination().createCombinations(20);
    }
}
