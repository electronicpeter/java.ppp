package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Combination {
    public Cycles createCombinations(int numberOfElements, int groupSize) {
        List<Integer> elements = createElements(numberOfElements);

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
        return addRemainingCycles(firstCycle, numberOfElements);
    }

    public Cycles createCombinations(int numberOfElements) {
        List<Integer> elements = createElements(numberOfElements);

        int numberOfGroups = Double.valueOf(Math.ceil(Math.sqrt(numberOfElements))).intValue();

        Cycle firstCycle = new Cycle();
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
        return addRemainingCycles(firstCycle, numberOfElements);
    }

    private List<Integer> createElements(int numberOfElements) {
        List<Integer> elements = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            elements.add(i);
        }
        return elements;
    }

    private Cycles addRemainingCycles(Cycle refCycle, int numberOfElements) {
        Cycles cycles = new Cycles();
        cycles.add(refCycle);

        /**
         * [0, 4, 8, 12]
         * [1, 5, 9, 13]
         * [2, 6, 10, 14]
         * [3, 7, 11, 15]
         */

        int xDim = refCycle.get(0).size();
        int yDim = refCycle.size();
        xDim = Math.max(xDim, yDim);
        yDim = xDim;

        for (int shift = 0; shift < xDim; shift++) {
            Cycle cycle = new Cycle();
            for (int x = 0; x < xDim; x++) {
                Group g = new Group();
                for (int y = 0; y < yDim; y++) {
                    int modX = (x + y*shift) % xDim;
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
