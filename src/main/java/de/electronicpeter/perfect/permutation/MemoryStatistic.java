package de.electronicpeter.perfect.permutation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class MemoryStatistic {

    private Status status = Status.FAULTY;
    private Integer numberOfElementsWithMoreThanOneMatch = 0;
    private Integer maxNumberOfMatchings = Integer.MIN_VALUE;

    public static enum Status {
        PERFECT,
        OK,
        FAULTY
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("                                     status " + status + "\n");
        sb.append("number of elements with more than one match " + numberOfElementsWithMoreThanOneMatch + "\n");
        sb.append("                      max number of matches " + maxNumberOfMatchings + "\n");
        return sb.toString();
    }
}
