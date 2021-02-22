package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class Combination {
    public Cycles createCombinations(int numberOfElements, int groupSize) {
        List<Integer> elements = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            elements.add(i);
        }

        int numberOfGroups = Double.valueOf(Math.ceil(Double.valueOf(numberOfElements) / Double.valueOf(groupSize))).intValue();
        log.info("X numberOfElements {}, groups {}, groupsize {}", numberOfElements, numberOfGroups, groupSize);

        Cycle firstCycle = new Cycle();
        int e = 0;
        for (int g = 0; g < numberOfGroups; g++) {
            Group group = new Group();
            group.addAll(elements.subList(e, Math.min(e + groupSize, numberOfElements)));
            firstCycle.add(group);
            e += groupSize;
        }
        if (e < numberOfElements) {
            firstCycle.stream().findAny().get().addAll(elements.subList(e, numberOfElements));
        }
        return addRemaining(firstCycle, numberOfElements);
    }

    public Cycles createCombinations(int numberOfElements) {
        List<Integer> elements = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            elements.add(i);
        }

        int numberOfGroups = Double.valueOf(Math.ceil(Math.sqrt(numberOfElements))).intValue();

        Cycle firstCycle = new Cycle();
        int e = 0;
        for (int g = 0; g < numberOfGroups; g++) {
            firstCycle.add(new Group());
        }
        // log.info("numberOfElements {}, numberOfGroups {}, groupsize {}, remainder {}, e {}", numberOfElements, numberOfGroups, groupSize, numberOfElements - e, e);
        while (!elements.isEmpty()) {
            firstCycle.stream().forEach(group -> {
                if (!elements.isEmpty()) {
                    group.add(elements.get(0));
                    elements.remove(0);
                }
            });
        }
        if (firstCycle.get(firstCycle.size() - 1).size() == 1) {
            firstCycle.get(0).addAll(firstCycle.get(firstCycle.size() - 1));
            firstCycle.remove(firstCycle.size() - 1);
        }
        return addRemaining(firstCycle, numberOfElements);
    }

    private Cycles addRemaining(Cycle refCycle, int numberOfElements) {
        Cycles cycles = new Cycles();
        cycles.add(refCycle);

        /**
         * [0, 3, 6]
         * [1, 4, 7]
         * [2, 5, 8]
         */

        int xDim = refCycle.get(0).size();
        int yDim = refCycle.size();
        xDim = Math.max(xDim, yDim);
        yDim = xDim;

        // vertical
        Cycle cycle = new Cycle();
        for (int x = 0; x < xDim; x++) {
            Group g = new Group();
            for (int y = 0; y < yDim; y++) {
                if (y < refCycle.size() && x < refCycle.get(y).size()) {
                    g.add(refCycle.get(y).get(x));
                }
            }
            cycle.add(g);
        }
        cycles.add(cycle);

        // diagone right
        cycle = new Cycle();
        for (int x = 0; x < xDim; x++) {
            Group g = new Group();
            for (int y = 0; y < yDim; y++) {
                int modX = (x + y) % xDim;
                if (y < refCycle.size() && modX < refCycle.get(y).size()) {
                    g.add(refCycle.get(y).get(modX));
                }
            }
            cycle.add(g);
        }
        cycles.add(cycle);

        // diagone left
        if (xDim > 2) {
            cycle = new Cycle();
            for (int x = 0; x < xDim; x++) {
                Group g = new Group();
                for (int y = 0; y < yDim; y++) {
                    int modX = (xDim + x - y) % xDim;
                    if (y < refCycle.size() && modX < refCycle.get(y).size()) {
                        g.add(refCycle.get(y).get(modX));
                    }
                }
                cycle.add(g);
            }
            cycles.add(cycle);
        }

        return cycles;
    }


}
