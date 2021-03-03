package de.electronicpeter.perfect.permutation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Combination {
    static int forRow[] = {10, 13, 17, 26, 37, 50, 65, 82};
    static int forSquare2[] = {21, 31, 43, 57, 73, 91};

    public Cycles createCombinations(int numberOfElements) {
        if (numberOfElements <= 100) {
            if (Arrays.stream(forRow).anyMatch(el -> el==numberOfElements)) {
                    return createCombinations(numberOfElements, Square.FillAlgorithm.ROW);
            }
            if (Arrays.stream(forSquare2).anyMatch(el -> el==numberOfElements)) {
                return createCombinations(numberOfElements, Square.FillAlgorithm.SQUARE2);
            }
            return createCombinations(numberOfElements, Square.FillAlgorithm.SQUARE);
        }
        while (true) {
            Cycles cycles = createCombinations(numberOfElements, Square.FillAlgorithm.SPACED);
            Memory memory = new Memory(numberOfElements).set(cycles);
            if (memory.everyThingOne()) {
                return cycles;
            }
            log.info("repeat search for perfect cylces for {}", numberOfElements);
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
        Cycles cycles = new Cycles(square.getFillAlgorithm());
        {
            Cycle firstCycle = new Cycle();
            List<Group> singletonGroups = new ArrayList<>();
            for (int y = 0; y < square.getDimension(); y++) {
                Group group = new Group();
                for (int x = 0; x < square.getDimension(); x++) {
                    group.add(square.get(x, y));
                }
                if (group.size() == 1) {
                    singletonGroups.add(group);
                } else {
                    firstCycle.add(group);
                }
            }
            handleSingletonGroups(square, cycles, firstCycle, singletonGroups);
            cycles.add(firstCycle);
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
            List<Group> singletonGroups = new ArrayList<>();
            for (int x = 0; x < square.getDimension(); x++) {
                Group group = new Group();
                for (int y = 0; y < square.getDimension(); y++) {
                    int modX = (x + y * shift) % square.getDimension();
                    group.add(square.get(modX, y));
                }
                if (group.size() == 1) {
                    singletonGroups.add(group);
                } else {
                    cycle.add(group);
                }
            }
            handleSingletonGroups(square, cycles, cycle, singletonGroups);
            cycles.add(cycle);
        }

        return cycles;
    }

    private void handleSingletonGroups(Square square, Cycles cycles, Cycle cycle, List<Group> singletonGroups) {
        if (!singletonGroups.isEmpty()) {
            if (singletonGroups.size() > 1) {
                Group newGroup = new Group();
                singletonGroups.stream().forEach(el -> newGroup.addAll(el));
                log.debug("for {} elements found more than one singleton group in for cycle {}. Added new group {}", square.getNumberOfElements(), cycles.size() + 1, newGroup.toString());
                cycle.add(newGroup);
            } else {
                log.debug("add singleton group to firstCycle");
                cycle.stream().findAny().get().add(singletonGroups.get(0));
            }
        }
    }


}
