package de.electronicpeter.combination;

import java.util.ArrayList;

public class Cycles extends ArrayList<Cycle> {
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (Cycle c : this) {
            sb.append(c);
        }
        return sb.toString();
    }

}
