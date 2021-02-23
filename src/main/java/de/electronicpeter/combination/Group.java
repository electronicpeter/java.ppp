package de.electronicpeter.combination;

import java.util.ArrayList;

public class Group extends ArrayList<Integer> {
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Integer element : this) {
            sb.append(String.format("%3d", element));
        }
        sb.append("]\n");
        return sb.toString();
    }
}
