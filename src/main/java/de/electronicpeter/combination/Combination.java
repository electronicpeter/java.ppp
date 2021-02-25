package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Combination {
    public Cycles createCombinations(int numberOfElements) {
        Square square = new Square(numberOfElements);
        return addRemainingCycles(square);
    }

    private Cycles addRemainingCycles(Square square) {
        Cycle firstCycle = new Cycle();
        for (int y = 0; y < square.getDimension(); y++) {
            Group group = new Group();
            for (int x = 0; x < square.getDimension(); x++) {
                group.add(square.get(x, y));
            }
            if (!group.isEmpty() && group.size() < 2) {
                firstCycle.stream().findAny().get().add(group);
            } else {
                firstCycle.add(group);
            }
        }

        Cycles cycles = new Cycles();
        cycles.add(firstCycle);

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
