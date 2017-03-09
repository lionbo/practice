package org.lionbo.practice.codewars;

public class LCS {
    public static String lcs(String x, String y) {
        if (y == null || y.equals(" ") || x == null || x.equals(" ")) {
            return "";
        }
        char[] charsx = x.toCharArray();
        char[] charsy = y.toCharArray();
        char[] result = new char[charsy.length];
        int index = 0;
        for (int i = 0, j = 0; i < charsx.length && j < charsy.length;) {
            int lastindex = 0;
            if (charsy[j] == charsx[i]) {
                result[index++] = charsy[j];
                lastindex = i;
                j++;
            }
            i++;
            if (i == charsx.length - 1 && j < charsy.length - 1) {
                i = lastindex;
                j++;
            }
        }
        if (index == 0) {
            return "";
        }
        return new String(result).trim();
    }

    public static void main(String[] args) {
        System.out.println(LCS.lcs("a", "b"));
        System.out.println(LCS.lcs("abcdef", "abc"));
        System.out.println(LCS.lcs("abcdef", "acf"));
        System.out.println(LCS.lcs("132535365", "123456789"));
    }
}
