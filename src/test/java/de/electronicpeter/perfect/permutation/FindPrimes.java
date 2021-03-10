package de.electronicpeter.perfect.permutation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FindPrimes {

    // @Test
    public void findPrimes() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        List<Integer> primes = new ArrayList<>();
        for (int i = 3; i < 10000; i += 2) {
            int halfI = i / 2 + 1;
            int found = -1;
            for (int p = 2; p < halfI; p++) {
                if (i % p == 0) {
                    found = p;
                    break;
                }
            }
            if (found == -1) {
                primes.add(i);
                if (primes.size() == 10) {
                    sb.append(primes.stream().map(Object::toString).collect(Collectors.joining(", ")));
                    sb.append(",\n");
                    primes.clear();
                }
            }
        }
        sb.append(primes.stream().map(Object::toString).collect(Collectors.joining(", ")));
        sb.append("\n");
        log.info(sb.toString());
    }
}
