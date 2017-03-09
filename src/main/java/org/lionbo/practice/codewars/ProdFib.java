package org.lionbo.practice.codewars;

import java.util.HashMap;
import java.util.Map;

public class ProdFib {
    static Double phi = (1 + Math.sqrt(5)) / 2;
    static Map<Long, Long> cache = new HashMap<>();

    public static long[] productFib(long prod) {

        long guess = (long) Math.sqrt(prod);
        long guessN = findBounds(guess);
        return extract(prod, guessN);
    }

    public static long[] extract(long prod, long guessN) {
        long[] result = new long[3];
        boolean lastflag = false;
        while (true) {
            long FN = F(guessN);
            long FPlus = F(guessN + 1);
            if (prod == FN * FPlus) {
                result[0] = FN;
                result[1] = FPlus;
                result[2] = 1;
                return result;
            }

            if (prod / FN > FPlus) {
                guessN = guessN + 1;
                lastflag = true;
            }

            if (prod / FN < FPlus) {
                guessN = guessN - 1;
                if (lastflag) {
                    result[0] = FN;
                    result[1] = FPlus;
                    result[2] = 0;
                    return result;
                }
                lastflag = false;
            }

        }
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
            return 1;
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

    public static long[] productFib2(long prod) {
        long a = 0L;
        long b = 1L;
        while (a * b < prod) {
            long tmp = a;
            a = b;
            b = tmp + b;
        }
        return new long[] { a, b, a * b == prod ? 1 : 0 };
    }

    public static void main(String[] args) {
        for (long l : ProdFib.productFib2(ProdFib.F(45) * ProdFib.F(46)))
            System.out.println(l);
        //        System.out.println(ProdFib.F(45) * ProdFib.F(46));
        //        1836311903
    }
}
