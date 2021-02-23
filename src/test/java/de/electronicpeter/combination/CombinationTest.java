package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CombinationTest {
    @Test
    public void checkAll() {
        for (int i = 4; i <= 9; i++) {
            check(i);
        }
        check(14);
        check(15);
        check(16);
        check(20);
    }

    @Test
    public void check4() {
        check(4);
    }

    @Test
    public void check9() {
        check(9);
    }

    @Test
    public void check16() {
        check(16);
    }

    private void check(int size) {
        Cycles combinations = new Combination().createCombinations(size);
        log.info("check {} -> {}", size, combinations.toString());
        Memory memory = new Memory(size).set(combinations);
        log.info("{}", memory.toString());
        Assertions.assertTrue(memory.everyThingOne());
    }

}
