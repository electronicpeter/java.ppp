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
import java.util.stream.Collectors;

@Slf4j
public class CombinationTest {
    @Test
    public void checkAll() {
        for (Square.FillAlgorithm algorithm : Square.FillAlgorithm.values()) {
            Map<Integer, MemoryStatistic> justOk = new HashMap<>();
            for (int i = 4; i <= 1000; i++) {
                check(i, justOk, algorithm);
            }
            log.info("in a 1000 groups made with {} found {} which are just ok", algorithm, justOk.keySet().size());
            log.info("{}", justOk.keySet().stream().sorted().map(Object::toString).collect(Collectors.joining(", ")));
            int keyMax = -1;
            MemoryStatistic valueMax = null;
            for (Integer key : justOk.keySet()) {
                MemoryStatistic value = justOk.get(key);
                if (keyMax == -1 || valueMax.getNumberOfElementsWithMoreThanOneMatch() < value.getNumberOfElementsWithMoreThanOneMatch()) {
                    valueMax = value;
                    keyMax = key;
                }
            }
            log.info(" -> worst key {} with {} ", keyMax, valueMax.toString());
            Cycles combinations = new Combination().createCombinations(keyMax);
            log.info(" -> {}", combinations.getStatistics().toString());
            Memory memory = new Memory(keyMax).set(combinations);
            log.info(" -> {}", memory.getMemoryStatistic());
        }
    }

    @Test
    public void checkPerfect() {
        Map<Integer, MemoryStatistic> justOk = new HashMap<>();
        for (int i = 4; i <= 1000; i++) {
            check(i, justOk, null);
        }
        log.info("in a 1000 groups found {} which are just ok", justOk.keySet().size());
        log.info("{}", justOk.keySet().stream().sorted().map(Object::toString).collect(Collectors.joining(", ")));
        int keyMax = -1;
        MemoryStatistic valueMax = null;
        for (Integer key : justOk.keySet()) {
            MemoryStatistic value = justOk.get(key);
            if (keyMax == -1 || valueMax.getNumberOfElementsWithMoreThanOneMatch() < value.getNumberOfElementsWithMoreThanOneMatch()) {
                valueMax = value;
                keyMax = key;
            }
        }
        log.info(" -> worst key {} with {} ", keyMax, valueMax.toString());
        Cycles combinations = new Combination().createCombinations(keyMax);
        log.info(" -> {}", combinations.getStatistics().toString());
        Memory memory = new Memory(keyMax).set(combinations);
        log.info(" -> {}", memory.getMemoryStatistic());
        Assertions.assertEquals(1, justOk.keySet().size());
    }

    @Test
    public void check4() {
        check(4);
    }

    @Test
    public void check5() {
        check(5);
    }

    @Test
    public void check7() {
        log.info(new Square(7).toString());
        check(7);
    }

    @Test
    public void check16() {
        check(16);
    }

    @Test
    public void check20() {
        check(21);
    }

    @Test
    public void check36() {
        check(36);
    }

    @Test
    public void check77() {
        check(77);
    }

    private void check(int size) {
        check(size, null);
    }

    private void check(int size, Square.FillAlgorithm fillAlgorithm) {
        check(size, null, fillAlgorithm);
    }

    private void check(int size, Map<Integer, MemoryStatistic> map, Square.FillAlgorithm fillAlgorithm) {
        Cycles combinations = new Combination().createCombinations(size, fillAlgorithm);
        Memory memory = new Memory(size).set(combinations);
        MemoryStatistic memoryStatistic = memory.getMemoryStatistic();
        switch (memoryStatistic.getStatus()) {
            case PERFECT:
                if (map == null) {
                    log.info("check {} is PERFECT", size);
                    log.info("{}", combinations.toString());
                    log.info("{}", combinations.getStatistics().toString());
                    log.info("{}", memory.getMemoryStatistic().toString());
                    log.info("{}", memory.toString());
                }
                return;
            case OK:
                if (map == null) {
                    log.info("check {} just OK", size);
                    log.info("{}", combinations.toString());
                    log.info("{}", combinations.getStatistics().toString());
                    log.info("{}", memory.getMemoryStatistic().toString());
                    log.info("{}", memory.toString());
                } else {
                    map.put(size, memoryStatistic);
                }
                return;
            default:
                log.info("check {} NOT OK", size);
                log.info("{}", combinations.toString());
                log.info("{}", memory.toString());
                throw new RuntimeException("no ok");
        }
    }
}
