package de.electronicpeter.perfect.permutation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Memory {
    private int dimension;
    private Byte[][] mem;
    private Cycles cycles;


    public Memory(Cycles cycles) {
        this.cycles = cycles;
        this.dimension = cycles.getSquare().getNumberOfElements();

        mem = new Byte[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            mem[i] = new Byte[dimension];
            for (int i2 = 0; i2 < dimension; i2++) {
                mem[i][i2] = 0;
            }
        }

        for (Cycle cycle : cycles) {
            set(cycle);
        }
    }

    public String toString() {
        int l = ("" + (dimension - 1)).length() + 1;

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(String.format("%" + l + "s", " "));
        for (int col = 0; col < dimension; col++) {
            sb.append(String.format("%" + l + "s", col));
        }
        sb.append("\n");
        for (int row = 0; row < dimension; row++) {
            sb.append(String.format("%" + l + "s", row));
            for (int col = 0; col < dimension; col++) {
                sb.append(String.format("%" + l + "s", mem[row][col] > 0 ? mem[row][col] == 1 ? "." : mem[row][col] : " "));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Boolean everyThingOne() {
        for (int row = 0; row < dimension; row++) {
            for (int col = row + 1; col < dimension; col++) {
                if (mem[row][col] != 1) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    private Memory set(Cycle cycle) {
        for (Group group : cycle) {
            set(group);
        }
        return this;
    }

    private Memory set(Group group) {
        if (group.size() == 1) {
            throw new MemoryException("group just contains one element");
        }
        for (int groupIndex = 0; groupIndex < group.size(); groupIndex++) {
            if (group.get(groupIndex) != null) {
                int i1 = group.get(groupIndex);
                for (int remainderIndex = groupIndex + 1; remainderIndex < group.size(); remainderIndex++) {
                    if (group.get(remainderIndex) != null) {
                        int i2 = group.get(remainderIndex);
                        set(i1, i2);
                    }
                }
            }
        }
        return this;
    }

    private Memory set(Integer element1, Integer element2) {
        int i1 = Math.min(element1, element2);
        int i2 = Math.max(element1, element2);
        if (i1 != i2) {
            mem[i1][i2] = Integer.valueOf(mem[i1][i2] + 1).byteValue();
        }
        return this;
    }

    private Boolean everyThingOneOrMore() {
        for (int row = 0; row < dimension; row++) {
            for (int col = row + 1; col < dimension; col++) {
                if (mem[row][col] < 1) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    public MemoryStatistic getMemoryStatistic() {
        MemoryStatistic memoryStatistic = new MemoryStatistic();

        if (everyThingOne()) {
            memoryStatistic.setStatus(MemoryStatistic.Status.PERFECT);
        } else if (everyThingOneOrMore()) {
            memoryStatistic.setStatus(MemoryStatistic.Status.OK);
        } else {
            memoryStatistic.setStatus(MemoryStatistic.Status.FAULTY);
        }

        for (int row = 0; row < dimension; row++) {
            for (int col = row + 1; col < dimension; col++) {
                if (mem[row][col] > 1) {
                    memoryStatistic.setNumberOfElementsWithMoreThanOneMatch(memoryStatistic.getNumberOfElementsWithMoreThanOneMatch() + 1);
                    memoryStatistic.setMaxNumberOfMatchings(Math.max(memoryStatistic.getMaxNumberOfMatchings(), mem[row][col]));
                }
            }
        }

        memoryStatistic.setFillAlgorithm(cycles.getSquare().getFillAlgorithm());
        memoryStatistic.setNumberOfElements(cycles.getSquare().getNumberOfElements());
        memoryStatistic.setNumberOfCycles(cycles.size());
        cycles.stream().forEach(cycle -> {
            memoryStatistic.setMaxNumberOfGroups(Math.max(memoryStatistic.getMaxNumberOfGroups(), cycle.size()));
            memoryStatistic.setMinNumberOfGroups(Math.min(memoryStatistic.getMinNumberOfGroups(), cycle.size()));
            cycle.stream().forEach(group -> {
                memoryStatistic.setMaxElementsInLargestGroup(Math.max(memoryStatistic.getMaxElementsInLargestGroup(), group.size()));
                memoryStatistic.setMinElementsInSmallestGroup(Math.min(memoryStatistic.getMinElementsInSmallestGroup(), group.size()));
            });
        });

        return memoryStatistic;
    }

    public static class MemoryException extends RuntimeException {
        public MemoryException(String message) {
            super(message);
        }
    }
}
