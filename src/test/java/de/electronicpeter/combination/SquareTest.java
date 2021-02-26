package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SquareTest {
    @Test
    public void a() {
        log.info(new Square(17, Square.FillAlgorithm.SQUARE).toString());
        log.info(new Square(17, Square.FillAlgorithm.ROW).toString());
        log.info(new Square(17, Square.FillAlgorithm.SPACED).toString());
    }
}
