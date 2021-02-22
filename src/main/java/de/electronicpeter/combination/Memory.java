package de.electronicpeter.combination;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Memory {
    private int size;
    private Integer[][] mem;

    public Memory(int size) {
        this.size = size;

        mem = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            mem[i] = new Integer[size];
            for (int i2 = 0; i2 < size; i2++) {
                mem[i][i2] = 0;
            }
        }
    }

    public boolean check(int first, Group group) {
        for (Integer element : group) {
            int i1 = Math.min(first, element);
            int i2 = Math.max(first, element);
            if (mem[i1][i2] > 0) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Memory set(Cycle cycle) {
        for (Group group : cycle) {
            set(group);
        }
        return this;
    }

    public Memory set(Cycles cycles) {
        for (Cycle cycle : cycles) {
            set(cycle);
        }
        return this;
    }

    public Memory set(Group group) {
        for (int groupIndex = 0; groupIndex < group.size(); groupIndex++) {
            int i1 = group.get(groupIndex);
            for (int remainderIndex = groupIndex + 1; remainderIndex < group.size(); remainderIndex++) {
                int i2 = group.get(remainderIndex);
                set(i1, i2);
            }
        }
        return this;
    }

    public Memory set(Integer element, Group group) {
        for (Integer i1 : group) {
            set(i1, element);
        }
        return this;
    }

    public Memory set(Integer element1, Integer element2) {
        int i1 = Math.min(element1, element2);
        int i2 = Math.max(element1, element2);
        if (i1 != i2) {
            mem[i1][i2] += 1;
        }
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("   ");
        for (int col = 0; col < size; col++) {
            sb.append(String.format("%3d", col));
        }
        sb.append("\n");
        for (int row = 0; row < size; row++) {
            sb.append(String.format("%3d", row));
            for (int col = 0; col < size; col++) {
                // sb.append(row < col ? mem[row][col] > 0 ? String.format("%3d", mem[row][col]) : "   " : " ..");
                sb.append(mem[row][col] > 0 ? String.format("%3d", mem[row][col]) : "   ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Boolean everyThingOne() {
        for (int row = 0; row < size; row++) {
            for (int col = row + 1; col < size; col++) {
                if (mem[row][col] != 1) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }
}