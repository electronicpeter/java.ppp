package de.electronicpeter.combination;


import java.util.ArrayList;

public class Cycle extends ArrayList<Group> {
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (Group g : this) {
            sb.append(g);
            sb.append("\n");
        }
        return sb.toString();
    }

    Boolean isComplete(int size) {
        int [] all = new int[size];
        for (int i = 0; i<size; i++) {
            all[i] = 0;
        }
        for (Group group : this) {
            for (Integer element : group) {
                all[element] +=1;
            }
        }
        for (int i = 0; i<size; i++) {
            if (all[i] < 1) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
