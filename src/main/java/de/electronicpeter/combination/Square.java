package de.electronicpeter.combination;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        int total = dimension * dimension;

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
        Integer limit = 19;
        Set<Integer> primes = new HashSet<>();
        if (number > limit) {
            throw new RuntimeException("no made for more than " + limit * limit + " elements");
        }
        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);
        primes.add(9);
        primes.add(11);
        primes.add(13);
        primes.add(17);
        primes.add(limit);
        return (primes.contains(number));
    }
}
