package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CombinationTest {
    @Test
    public void a() {
        for (int i = 4; i <= 9; i++) {
            log.info("" + new Combination().createCombinations(i));
        }
        log.info("" + new Combination().createCombinations(9,5));
        log.info("" + new Combination().createCombinations(14));
        log.info("" + new Combination().createCombinations(15));
        log.info("" + new Combination().createCombinations(16));
        log.info("" + new Combination().createCombinations(20));
    }
}
