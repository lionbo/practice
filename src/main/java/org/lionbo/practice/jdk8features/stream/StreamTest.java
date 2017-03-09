package org.lionbo.practice.jdk8features.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest {

    public static void main(String[] args) {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 11, 8);
        System.out.println(items.stream().filter(item -> item > 3).mapToInt(Integer::valueOf).sum());
    }

}
