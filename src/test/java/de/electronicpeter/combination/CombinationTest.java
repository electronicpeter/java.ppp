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
        Map<Integer, MemoryStatistic> justOk = new HashMap<>();
        for (int i = 4; i <= 1000; i++) {
            check(i, justOk);
        }
        log.info("in a 1000 groups found {} which are just ok", justOk.keySet().size());
        justOk.keySet().stream().sorted().forEach(key -> log.info("key {} {}", key, justOk.get(key)));
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

    @Test
    public void check4() {
        check(4);
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
    public void check17() {
        check(17);
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
    public void check77() {
        check(77);
    }

    private void check(int size) {
        check(size, null);
    }

    private void check(int size, Map<Integer, MemoryStatistic> map) {
        Cycles combinations = new Combination().createCombinations(size);
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
