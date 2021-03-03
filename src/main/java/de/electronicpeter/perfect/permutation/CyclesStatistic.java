package de.electronicpeter.perfect.permutation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CyclesStatistic {
    private final Square.FillAlgorithm fillAlgorithm;
    private final int numberOfElements = 0;
    private int numberOfCycles = Integer.MIN_VALUE;
    private int maxNumberOfGroups = Integer.MIN_VALUE;
    private int minNumberOfGroups = Integer.MAX_VALUE;
    private int maxElementsInLargestGroup = Integer.MIN_VALUE;
    private int minElementInSmallestGroup = Integer.MAX_VALUE;
}
