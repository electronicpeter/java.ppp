package de.electronicpeter.server.service.layer;

import de.electronicpeter.perfect.permutation.Cycles;
import de.electronicpeter.perfect.permutation.MemoryStatistic;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PerfectPermutationResult {
    private final Cycles cycles;
    private final MemoryStatistic Statistic;
}
