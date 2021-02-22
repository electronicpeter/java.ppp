package de.electronicpeter.combination;

import java.util.HashSet;

public class Cycles extends HashSet<Cycle> {
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (Cycle c : this) {
            sb.append(c);
            sb.append("\n");
        }
        return sb.toString();
    }

}
