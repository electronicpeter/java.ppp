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

    private Cycles addRemaining(Cycle cycle, int numberOfElements) {

        Memory memory = new Memory(numberOfElements);
        memory.set(cycle);
        Cycles cycles = new Cycles();
        cycles.add(cycle);
        boolean end = false;
        while (!end) {
            List<Integer> elements = new ArrayList<>(numberOfElements);
            for (int i = 0; i < numberOfElements; i++) {
                elements.add(i);
            }
            Cycle nextCycle = new Cycle();
            for (Group groupOfFirstCycle : cycle) {
                Group group = new Group();
                if (!elements.isEmpty()) {
                    if (group.isEmpty()) {
                        group.add(elements.get(0));
                        elements.remove(0);
                    }
                    for (Integer element : elements) {
                        if (!memory.check(element, group)) {
                            group.add(element);
                            elements.remove(element);
                            memory.set(element, group);
                            break;
                        }
                    }
                }
                if (group.size() > 1) {
                    nextCycle.add(group);
                } else {
                    end = true;
                }
            }
            if (!nextCycle.isEmpty()) {
                if (!nextCycle.isComplete(numberOfElements)) {
                    log.error("cycle no ok {}", nextCycle);
                } else {
                    log.info("cycle ok {}", nextCycle);
                }
                cycles.add(nextCycle);
            } else {
                end = true;
            }
        }
        log.info(memory.toString());
        return cycles;
    }


}
