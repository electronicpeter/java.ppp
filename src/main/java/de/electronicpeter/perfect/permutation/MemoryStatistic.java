package de.electronicpeter.perfect.permutation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemoryStatistic {

    private Status status = Status.FAULTY;
    private Integer numberOfElementsWithMoreThanOneMatch = 0;
    private Integer maxNumberOfMatchings = Integer.MIN_VALUE;

    public static enum Status {
        PERFECT,
        OK,
        FAULTY
    }
}
