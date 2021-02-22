package de.electronicpeter.combination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupTest {
    @Test
    public void compare() {
        Group first = new Group();
        first.add("1");
        first.add("2");

        Group second = new Group();
        second.add("2");
        second.add("1");

        Group third = new Group();
        third.add("3");
        third.add("1");

        Assertions.assertEquals(first, second);
        Assertions.assertNotEquals(first, third);
    }
}
