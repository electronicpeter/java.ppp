package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Combination {
    public Cycles createCombinations(int numberOfElements) {
        if (numberOfElements <= 20 && numberOfElements != 13) {
            return createCombinations(numberOfElements, Square.FillAlgorithm.SQUARE);
        }
        while (true) {
            Cycles cycles = createCombinations(numberOfElements, Square.FillAlgorithm.SPACED);
            Memory memory = new Memory(numberOfElements).set(cycles);
            if (memory.everyThingOne()) {
                return cycles;
            }
            log.info("did not find perfect cylces for {}", numberOfElements);
        }
    }

    public Cycles createCombinations(int numberOfElements, Square.FillAlgorithm fillAlgorithm) {
        if (fillAlgorithm == null) {
            return createCombinations(numberOfElements);
        }
        Square square = new Square(numberOfElements, fillAlgorithm);
        return addRemainingCycles(square);
    }

    private Cycles addRemainingCycles(Square square) {
        Cycles cycles = new Cycles();
        Cycle firstCycle = new Cycle();
        cycles.add(firstCycle);
        {
            Group singletonGroup = null;
            for (int y = 0; y < square.getDimension(); y++) {
                Group group = new Group();
                for (int x = 0; x < square.getDimension(); x++) {
                    group.add(square.get(x, y));
                }
                if (!group.isEmpty() && group.size() < 2) {
                    if (firstCycle.isEmpty()) {
                        // this can happen, if square algorithm is spaced
                        singletonGroup = group;
                    } else {
                        firstCycle.stream().findAny().get().add(group);
                    }
                } else {
                    firstCycle.add(group);
                }
            }
            if (singletonGroup != null) {
                firstCycle.stream().findAny().get().add(singletonGroup);
            }
        }

        /**
         * [   0   1   4   9  16]
         * [   2   3   5  10  17]
         * [   6   7   8  11  18]
         * [  12  13  14  15  19]
         * [  20  21  22  23  24]
         */

        for (int shift = 0; shift < square.getDimension(); shift++) {
            Cycle cycle = new Cycle();
            for (int x = 0; x < square.getDimension(); x++) {
                Group g = new Group();
                for (int y = 0; y < square.getDimension(); y++) {
                    int modX = (x + y * shift) % square.getDimension();
                    g.add(square.get(modX, y));
                }
                cycle.add(g);
            }
            cycles.add(cycle);
        }

        return cycles;
    }


}
