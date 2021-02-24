package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CombinationTest {
    @Test
    public void checkAll() {
        Map<Integer, Integer> justOk = new HashMap<>();
        for (int i = 4; i <= 1000; i++) {
            checkLight(i, justOk);
        }
        log.info("found {} which are just ok", justOk.keySet().size());
        int keyMax = -1;
        int valueMax = -1;
        for (Integer key : justOk.keySet()) {
            Integer value = justOk.get(key);
            if (value > valueMax) {
                valueMax = value;
                keyMax = key;
            }
        }
        log.info("worst key {} with {} doubles", keyMax, valueMax);
        Cycles combinations = new Combination().createCombinations(keyMax);
        Memory memory = new Memory(keyMax).set(combinations);
        log.info("memory is {}", memory.toString());


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
    public void check1000() {
        check(1000);
    }

    @Test
    public void check49() {
        check(49);
    }
    @Test
    public void check147() {
        check(147);
    }

    @Test
    public void check50() {
        log.info(new Square(54).toString());
        check(54);
    }

    @Test
    public void checkSquares() {
        for (int i = 3; i < 20; i++) {
            checkSquare(i);
        }
    }

    private void checkLight(int size, Map<Integer, Integer> map) {
        Cycles combinations = new Combination().createCombinations(size);
        Memory memory = new Memory(size).set(combinations);
        if (memory.everyThingOneOrMore()) {
            if (memory.everyThingOne()) {
            } else {
                log.info("check {} just OK", size);
                map.put(size, memory.countDoulbes());
            }
        } else {
            log.info("check {} NOT OK", size);
            log.info("{}", combinations.toString());
            log.info("{}", memory.toString());
            throw new RuntimeException("no ok");
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
