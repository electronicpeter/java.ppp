package de.electronicpeter.perfect.permutation;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Group extends ArrayList<Integer> {
    private int printLength;
    public Group(int dimension) {
        printLength = ("" + (dimension * dimension - 1)).length() + 1;
    }

    public void add(Group other) {
        this.addAll(other);
    }

    public int numberOfNonNullElements() {
        return this.stream().filter(el -> el != null).collect(Collectors.toList()).size();
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.stream().map(Group::toStringOrNull).map(el -> String.format("%" + printLength + "s", el)).collect(Collectors.joining(", ")));
        sb.append("]");
        return sb.toString();
    }

    public static String toStringOrNull(Integer i){
        if (i != null) {
            return "" + i;
        }
        return " ";
    }
}
