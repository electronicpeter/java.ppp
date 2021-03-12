package de.electronicpeter.perfect.permutation;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
@Getter
public class Cycles extends ArrayList<Cycle> {
    private Square square;

    public Cycles(Square square) {
        this.square = square;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cylces: " + this.size() + "\n");
        int i = 0;
        for (Cycle c : this) {
            sb.append("\ncycle " + i + " " + c);
            i++;
        }
        return sb.toString();
    }
}
