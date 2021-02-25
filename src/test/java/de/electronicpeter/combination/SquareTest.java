package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SquareTest {
    @Test
    public void a() {
        log.info(new Square(7).toString());
        log.info(new Square(17).toString());
    }
}
