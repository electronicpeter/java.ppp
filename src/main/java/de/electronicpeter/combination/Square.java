package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Square {
    private Integer[][] array;
    private Integer dimension;

    public Square(Integer numberOfElements) {
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            elements.add(i);
        }

        dimension = Double.valueOf(Math.ceil(Math.sqrt(numberOfElements))).intValue();
        while (!isPrime(dimension)) {
            dimension++;
        }

        array = new Integer[dimension][dimension];
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (!elements.isEmpty()) {
                    array[x][y] = elements.get(0);
                    elements.remove(0);
                }
            }
        }
    }

    public Integer getDimension() {
        return dimension;
    }

    public Optional<Integer> get(int x, int y) {
        if (array[x][y] == null) {
            return Optional.empty();
        }
        return Optional.of(array[x][y]);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                sb.append(array[x][y] != null ? String.format("%3d", array[x][y]) : "  .");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    private boolean isPrime(int number) {
        int primes[] = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
        if (number > primes[primes.length-1]) {
            throw new RuntimeException("no made for more than " + primes[primes.length-1]*primes[primes.length-1] + " elements");
        }
        for (int i = 0; i<primes.length; i++) {
            if (number == primes[i]) {
                return true;
            }
        }
        return false;
    }
}
