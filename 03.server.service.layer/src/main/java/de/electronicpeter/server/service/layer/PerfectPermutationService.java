package de.electronicpeter.server.service.layer;

import de.electronicpeter.perfect.permutation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PerfectPermutationService {
    public PerfectPermutationResult getPerfectPermutation(int numberOfElements, boolean filterNulls, Square.FillAlgorithm fillAlgorithm) {
        Permutation permutation = new Permutation();
        log.info("filterNulls: {}", filterNulls);
        Cycles cycles = permutation.findPerfectPermutation(numberOfElements, fillAlgorithm);
        if (filterNulls) {
            Cycles newCycles = new Cycles(cycles.getSquare());
            for (Cycle cycle : cycles) {
                Cycle newCycle = new Cycle();
                for (Group group : cycle) {
                    Group newGroup = new Group(cycles.getSquare().getDimension());
                    newGroup.addAll(group.stream().filter(element -> element != null).collect(Collectors.toList()));
                    if (!newGroup.isEmpty()) {
                        newCycle.add(newGroup);
                    }
                }
                if (! newCycle.isEmpty()) {
                    newCycles.add(newCycle);
                }
            }
            cycles = newCycles;
        }
        return new PerfectPermutationResult(cycles, new Memory(cycles).getMemoryStatistic());
    }
}
