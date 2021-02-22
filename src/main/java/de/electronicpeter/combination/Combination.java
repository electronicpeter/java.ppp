package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class Combination {
    Set<Iteration> iterations = new HashSet<>();

    public void createCombinations(int numberOfElements, int groupSize) {
        List<String> elements = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            elements.add("" + (i + 1));
        }

        int numberOfGroups = Double.valueOf(Math.ceil(Double.valueOf(numberOfElements) / Double.valueOf(groupSize))).intValue();
        log.info("X numberOfElements {}, groups {}, groupsize {}", numberOfElements, numberOfGroups, groupSize);

        Iteration firstIteration = new Iteration();
        int e = 0;
        for (int g = 0; g < numberOfGroups; g++) {
            Group group = new Group();
            group.addAll(elements.subList(e, Math.min(e + groupSize, numberOfElements)));
            firstIteration.add(group);
            e += groupSize;
        }
        if (e < numberOfElements) {
            firstIteration.stream().findAny().get().addAll(elements.subList(e, numberOfElements));
        }
        log.info(firstIteration.toString());
    }

    public void createCombinations(int numberOfElements) {
        List<String> elements = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            elements.add("" + (i + 1));
        }

        int numberOfGroups = Double.valueOf(Math.ceil(Math.sqrt(numberOfElements))).intValue();
        int groupSize = numberOfGroups;

        Iteration firstIteration = new Iteration();
        int e = 0;
        for (int g = 0; g < numberOfGroups; g++) {
            firstIteration.add(new Group());
        }
        log.info("numberOfElements {}, numberOfGroups {}, groupsize {}, remainder {}, e {}", numberOfElements, numberOfGroups, groupSize, numberOfElements - e, e);
        while (!elements.isEmpty()) {
            firstIteration.stream().forEach(group -> {
                if (!elements.isEmpty()) {
                    group.add(elements.get(0));
                    elements.remove(0);
                }
            });
        }
        if (firstIteration.get(firstIteration.size()-1).size() == 1) {
            firstIteration.get(0).addAll(firstIteration.get(firstIteration.size()-1));
            firstIteration.remove(firstIteration.size()-1);
        }
        log.info(firstIteration.toString());
    }

}
