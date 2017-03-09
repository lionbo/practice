package org.lionbo.practice.codewars;

public class Zeros {

    public static int zeros(int n) {

        long result = 1;
        int count = 0;

        for (int i = 1; i <= n; i++) {
            result = result * i;

            while (result % 10 == 0) {
                result = result / 10;
                count++;
            }
            result = result % 10000;
        }

        return count;
    }

    public static int zeros2(int n) {
        int res = 0;
        for (int i = 5; i <= n; i *= 5) {
            res += n / i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Zeros.zeros2(1000));
    }

}
