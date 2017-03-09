package org.lionbo.practice.codewars;

public class Magnets {

    public static double doubles(int maxk, int maxn) {
        double result = 0;
        for (int i = 1; i <= maxk; i++) {
            for (int j = 1; j <= maxn; j++) {
                result += 1 / (i * Math.pow(j + 1, 2 * i));
            }
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(Magnets.doubles(10, 100));
    }
}
