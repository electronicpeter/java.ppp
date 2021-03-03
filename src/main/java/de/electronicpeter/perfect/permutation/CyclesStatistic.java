package de.electronicpeter.perfect.permutation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
public class CyclesStatistic {
    private final Square.FillAlgorithm fillAlgorithm;
    private final int numberOfElements;
    private int numberOfCycles = Integer.MIN_VALUE;
    private int maxNumberOfGroups = Integer.MIN_VALUE;
    private int minNumberOfGroups = Integer.MAX_VALUE;
    private int maxElementsInLargestGroup = Integer.MIN_VALUE;
    private int minElementInSmallestGroup = Integer.MAX_VALUE;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("               fill algorithm " + fillAlgorithm + "\n");
        sb.append("           number of elements " + numberOfElements + "\n");
        sb.append("             number of cycles " + numberOfCycles + "\n");
        sb.append("         max number of groups " + maxNumberOfGroups + "\n");
        sb.append("         min number of groups " + minNumberOfGroups + "\n");
        sb.append("max elements in largest group " + maxElementsInLargestGroup + "\n");
        sb.append("min element in smallest group " + minElementInSmallestGroup + "\n");
        return sb.toString();
    }
}
