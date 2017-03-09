package org.lionbo.practice.codewars;

import java.util.HashMap;
import java.util.Map;

public class ProdFibMinDiff {

    static Double phi = (1 + Math.sqrt(5)) / 2;
    static Map<Long, Long> cache = new HashMap<>();

    public static long[] productFib(long prod) {

        long guess = (long) Math.sqrt(prod);
        long guessN = findBounds(guess);
        return extract(prod, guessN, Long.MAX_VALUE);
    }

    private static long[] extract(long prod, long guessN, long diff) {
        long[] result = new long[3];

        long FN = F(guessN);
        long FPlus = F(guessN + 1);

        if (prod == FN * FPlus) {
            result[0] = FN;
            result[1] = FPlus;
            result[2] = 1;
            return result;
        }

        if (prod > FN * FPlus) {
            return extract(prod, guessN + 1, diff);
        }
        if (prod < FN * FPlus) {
            long currentDiff = FN * FPlus - prod;
            if (currentDiff > diff) {
                return extract(prod, guessN - 1, diff);
            }
            if (currentDiff < diff) {
                return extract(prod, guessN - 1, currentDiff);
            }
            if (currentDiff == diff) {
                result[0] = FN;
                result[1] = FPlus;
                result[2] = 0;
                return result;
            }
        }

        return result;
    }

    public static long findBounds(long guess) {
        long n = 1;
        while (getX(n) < guess) {
            n++;
        }
        return n;
    }

    public static Double getX(long n) {
        Double x = Math.pow(phi, n) / Math.sqrt(5);
        return x;
    }

    public static long F(long n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (cache.get(n) != null) {
            return cache.get(n);
        }
        long result = 0;
        long first = 1;
        long second = 2;
        for (int i = 3; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        cache.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        for (long l : ProdFibMinDiff.productFib(Long.MAX_VALUE))
            System.out.println(l);
    }
}
