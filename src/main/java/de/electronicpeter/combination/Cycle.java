package de.electronicpeter.combination;


import java.util.ArrayList;

public class Cycle extends ArrayList<Group> {
    @Override
    public boolean add(Group group) {
        if (!group.isEmpty()) {
            return super.add(group);
        }
        return false;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        int i = 0;
        for (Group g : this) {
            sb.append("group " + i + " " + g);
            i++;
        }
        return sb.toString();
    }
}
