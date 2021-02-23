package de.electronicpeter.combination;


import java.util.ArrayList;

public class Cycle extends ArrayList<Group> {
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (Group g : this) {
            sb.append(g);
        }
        return sb.toString();
    }
}
