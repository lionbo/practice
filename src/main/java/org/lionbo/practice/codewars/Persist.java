package org.lionbo.practice.codewars;

import java.util.LinkedList;
import java.util.List;

public class Persist {
    public static int persistence(long n) {
        List<Integer> list = new LinkedList<>();
        if (n < 10) {
            return 0;
        }

        while (n != 0) {
            long le = n / 10;
            int re = (int) (n % 10);
            if (le >= 10) {
                list.add(re);
                n = le;
                le = n / 10;
            } else {
                list.add((int) re);
                list.add((int) le);
                n = 0;
            }
        }
        long value = 1;
        for (int i : list) {
            value = value * i;
        }

        return persistence(value) + 1;
    }

    public static void main(String[] args) {
        System.out.println(Persist.persistence(69363));
    }
}
