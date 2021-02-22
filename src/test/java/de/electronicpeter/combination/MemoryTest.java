package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Set;

@Slf4j
public class MemoryTest {
    @Test
    public void aa() {
        int size=9;
        Memory memory = new Memory(size);
        Combination combination = new Combination();
        combination.createCombinations(size);
        Set<Cycle> cycles = combination.getCycles();
        for(Cycle cycle : cycles) {
            for (Group group : cycle) {
                memory.set(group);
                log.info(memory.toString());
            }
        }
    }
}
