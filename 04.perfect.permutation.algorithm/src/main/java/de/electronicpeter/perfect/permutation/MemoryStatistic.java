package de.electronicpeter.perfect.permutation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemoryStatistic {

    private Status status = Status.FAULTY;
    private Integer numberOfElementsWithMoreThanOneMatch = 0;
    private Integer maxNumberOfMatchings = 0;
    private Square.FillAlgorithm fillAlgorithm = null;
    private int numberOfElements = 0;
    private int numberOfCycles = Integer.MIN_VALUE;
    private int maxNumberOfGroups = Integer.MIN_VALUE;
    private int minNumberOfGroups = Integer.MAX_VALUE;
    private int maxElementsInLargestGroup = Integer.MIN_VALUE;
    private int minElementsInSmallestGroup = Integer.MAX_VALUE;

    public static enum Status {
        PERFECT,
        OK,
        FAULTY
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("                       status " + status + "\n");
        sb.append(" elements with more one match " + numberOfElementsWithMoreThanOneMatch + "\n");
        sb.append("        max number of matches " + maxNumberOfMatchings + "\n");
        sb.append("               fill algorithm " + fillAlgorithm + "\n");
        sb.append("           number of elements " + numberOfElements + "\n");
        sb.append("             number of cycles " + numberOfCycles + "\n");
        sb.append("         max number of groups " + maxNumberOfGroups + "\n");
        sb.append("         min number of groups " + minNumberOfGroups + "\n");
        sb.append("max elements in largest group " + maxElementsInLargestGroup + "\n");
        sb.append("min element in smallest group " + minElementsInSmallestGroup + "\n");
        return sb.toString();
    }
}
