package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class Combination {
    Set<Cycle> cycles = new HashSet<>();

    public void createCombinations(int numberOfElements, int groupSize) {
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
        cycles.add(firstCycle);
        log.info(firstCycle.toString());
    }

    public void createCombinations(int numberOfElements) {
        List<Integer> elements = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            elements.add(i);
        }

        int numberOfGroups = Double.valueOf(Math.ceil(Math.sqrt(numberOfElements))).intValue();
        int groupSize = numberOfGroups;

        Cycle firstCycle = new Cycle();
        int e = 0;
        for (int g = 0; g < numberOfGroups; g++) {
            firstCycle.add(new Group());
        }
        log.info("numberOfElements {}, numberOfGroups {}, groupsize {}, remainder {}, e {}", numberOfElements, numberOfGroups, groupSize, numberOfElements - e, e);
        while (!elements.isEmpty()) {
            firstCycle.stream().forEach(group -> {
                if (!elements.isEmpty()) {
                    group.add(elements.get(0));
                    elements.remove(0);
                }
            });
        }
        if (firstCycle.get(firstCycle.size()-1).size() == 1) {
            firstCycle.get(0).addAll(firstCycle.get(firstCycle.size()-1));
            firstCycle.remove(firstCycle.size()-1);
        }
        cycles.add(firstCycle);
        log.info(firstCycle.toString());
    }

    public Set<Cycle> getCycles() {
        return cycles;
    }

}
