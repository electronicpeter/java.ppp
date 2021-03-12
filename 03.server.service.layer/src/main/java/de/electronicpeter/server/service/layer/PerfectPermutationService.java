package de.electronicpeter.server.service.layer;

import de.electronicpeter.perfect.permutation.Cycles;
import de.electronicpeter.perfect.permutation.Memory;
import de.electronicpeter.perfect.permutation.Permutation;
import org.springframework.stereotype.Service;

@Service
public class PerfectPermutationService {
    public PerfectPermutationResult getPerfectPermutation(int numberOfElements) {
        Permutation permutation = new Permutation();
        Cycles perfectPermutation = permutation.findPerfectPermutation(numberOfElements);
        return new PerfectPermutationResult(perfectPermutation, new Memory(perfectPermutation).getMemoryStatistic());
    }
}
