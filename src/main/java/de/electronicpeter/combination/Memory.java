package de.electronicpeter.combination;

public class Memory {
    private int size;
    private Integer[][] mem;
    public Memory(int size) {
        this.size = size;

        mem = new Integer[size][size];
        for (int i = 0; i<size; i++) {
            mem[i] = new Integer[size];
            for (int i2 = 0; i2<size; i2++) {
                mem[i][i2] = 0;
            }
        }
    }

    public boolean check(int first, int second) {
        return mem[first][second] > 0;
    }

    public void set(Group group) {
        for (Integer i1 : group) {
            for (Integer i2 : group) {
                mem[i1][i2]+=1;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("   ");
        for (int col = 0; col<size; col++) {
            sb.append(String.format("%3d", col));
        }
        sb.append("\n");
        for (int row = 0; row<size; row++) {
            sb.append(String.format("%3d", row));
            for (int col = 0; col<size; col++) {
                sb.append(row < col ? mem[row][col] > 0 ? String.format("%3d", mem[row][col]) : "   " : " ..");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
