package org.lionbo.practice.codewars;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WhichAreIn {

    public static String[] inArray(String[] array1, String[] array2) {

        if (array1 == null || array1.length == 0 || array2 == null || array2.length == 0) {
            return new String[0];
        }

        List<String> list1 = Arrays.asList(array1);
        Collections.sort(list1);
        List<String> list = new LinkedList<>();

        for (String str : list1) {
            for (String s : array2) {
                if (s.contains(str)) {
                    list.add(str);
                    break;
                }
            }
        }
        return list.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String a[] = new String[] { "arp", "live", "strong" };
        String b[] = new String[] { "lively", "alive", "harp", "sharp", "armstrong" };
        String r[] = new String[] { "arp", "live", "strong" };
        for (String s : WhichAreIn.inArray(a, b)) {
            System.out.println(s);
        }
    }
}
