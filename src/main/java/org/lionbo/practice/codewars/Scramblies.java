package org.lionbo.practice.codewars;

import java.util.Arrays;

public class Scramblies {
    public static boolean scramble(String str1, String str2) {

        if (str1 == null || str1.length() == 0) {
            return false;
        }
        if (str2 == null || str2.length() == 0) {
            return true;
        }

        char[] schars1 = str1.toCharArray();
        char[] schars2 = str2.toCharArray();

        Arrays.sort(schars1);
        Arrays.sort(schars2);
        int match = 0;
        for (int i = 0, j = 0; i < schars1.length && j < schars2.length;) {
            if (schars2[j] != schars1[i]) {
                i++;
            } else {
                match++;
                i++;
                j++;
            }
        }
        if (match == schars2.length)
            return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Scramblies.scramble("rkqodlw", "world"));
        System.out.println(Scramblies.scramble("katas", "steak"));
        System.out.println(Scramblies.scramble("javscripts", "javascript"));
    }
}
