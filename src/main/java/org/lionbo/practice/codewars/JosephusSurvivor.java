package org.lionbo.practice.codewars;

public class JosephusSurvivor {

    public static int josephusSurvivor(final int n, final int k) {
        int tmp = 1;
        for (int i = 1; i <= n; i++) {
            tmp = (tmp + k - 1) % i + 1;
        }
        return tmp;

    }

    public static void main(String[] args) {
        System.out.println(JosephusSurvivor.josephusSurvivor(7, 3));
    }

}
