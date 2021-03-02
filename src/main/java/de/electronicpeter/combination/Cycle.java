package de.electronicpeter.combination;


import java.util.ArrayList;

public class Cycle extends ArrayList<Group> {
    @Override
    public boolean add(Group group) {
        if (!group.isEmpty()) {
            if (group.size() == 1) {
                throw new CycleException("Groups with one Element are not allowed");
            }
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

    public static class CycleException extends RuntimeException {
        public CycleException(String message) {
            super(message);
        }
    }
}
