package de.electronicpeter.server.service.layer;

import de.electronicpeter.perfect.permutation.Cycles;
import de.electronicpeter.perfect.permutation.Memory;
import de.electronicpeter.perfect.permutation.Permutation;
import de.electronicpeter.perfect.permutation.Square;
import org.springframework.stereotype.Service;

@Service
public class PerfectPermutationService {
    public PerfectPermutationResult getPerfectPermutation(int numberOfElements, Square.FillAlgorithm fillAlgorithm) {
        Permutation permutation = new Permutation();
        Cycles perfectPermutation = permutation.findPerfectPermutation(numberOfElements, fillAlgorithm);
        return new PerfectPermutationResult(perfectPermutation, new Memory(perfectPermutation).getMemoryStatistic());
    }
}
