package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SquareTest {
    @Test
    public void a() {
        for (int i = 4; i<100; i++) {
            log.info(new Square(i).toString());
        }
    }
}
