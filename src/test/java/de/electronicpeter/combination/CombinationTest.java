package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

@Slf4j
public class CombinationTest {
    @Test
    public void checkAll() {
        for (int i = 4; i <= 20; i++) {
            check(i);
        }
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

    @Test
    public void check25() {
        check(25);
    }

    @Test
    public void check36() {
        check(36);
    }

    @Test
    public void check49() {
        check(49);
    }

    @Test
    public void checkSquares() {
        for (int i = 3; i < 20; i++) {
            checkSquare(i);
        }
    }

    private void check(int size) {
        Cycles combinations = new Combination().createCombinations(size);
        Memory memory = new Memory(size).set(combinations);
        if (memory.everyThingOneOrMore()) {
            if (memory.everyThingOne()) {
                log.info("check {} PERFECT", size);
            } else {
                log.info("check {} OK", size);
                log.info("{}", combinations.toString());
                log.info("{}", memory.toString());
            }
        } else {
            log.info("check {} NOT OK", size);
            log.info("{}", combinations.toString());
            log.info("{}", memory.toString());
        }
        Assertions.assertTrue(memory.everyThingOneOrMore());
    }

    private void checkSquare(int dimension) {
        int size = dimension * dimension;
        Cycles combinations = new Combination().createCombinations(size);
        Memory memory = new Memory(size).set(combinations);
        if (memory.everyThingOne()) {
            log.info("check {} {} OK", dimension, size);
        } else {
            log.info("check {} {} NOT OK", dimension, size);
        }
    }

}
