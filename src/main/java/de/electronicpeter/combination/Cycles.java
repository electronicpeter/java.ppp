package de.electronicpeter.combination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
public class Cycles extends ArrayList<Cycle> {
    private final Square.FillAlgorithm fillAlgorithm;

    public CyclesStatistic getStatistics() {
        final CyclesStatistic cyclesStatistic = new CyclesStatistic(fillAlgorithm);
        cyclesStatistic.setNumberOfCycles(this.size());
        this.stream().forEach(cycle -> {
            cyclesStatistic.setMaxNumberOfGroups(Math.max(cyclesStatistic.getMaxNumberOfGroups(), cycle.size()));
            cyclesStatistic.setMinNumberOfGroups(Math.min(cyclesStatistic.getMinNumberOfGroups(), cycle.size()));
            cycle.stream().forEach(group -> {
                cyclesStatistic.setMaxElementsInLargestGroup(Math.max(cyclesStatistic.getMaxElementsInLargestGroup(), group.size()));
                cyclesStatistic.setMinElementInSmallestGroup(Math.min(cyclesStatistic.getMinElementInSmallestGroup(), group.size()));
            });
        });
        return cyclesStatistic;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cylces: " + this.size() + "\n");
        int i = 0;
        for (Cycle c : this) {
            sb.append("\ncycle " + i + " " + c);
            i++;
        }
        return sb.toString();
    }
}
