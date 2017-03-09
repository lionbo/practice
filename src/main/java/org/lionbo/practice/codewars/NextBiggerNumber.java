package org.lionbo.practice.codewars;

import java.util.Arrays;

public class NextBiggerNumber {

    public static long nextBiggerNumber(long n) {
        if (n < 12) {
            return -1;
        }
        String str = n + "";
        char[] numberArray = str.toCharArray();
        for (int i = numberArray.length - 2; i >= 0; i--) {
            int length = numberArray.length - i;
            char[] subArray = new char[length];
            System.arraycopy(numberArray, i, subArray, 0, length);
            long part = findBigger(subArray);
            if (part != -1) {
                char[] highArray = new char[i];
                System.arraycopy(numberArray, 0, highArray, 0, i);
                String result = new String(highArray);
                result = result + part + "";
                return Long.valueOf(result);
            }
        }
        return -1;
    }

    public static long findBigger(char[] subArray) {
        char[] copy = new char[subArray.length];
        System.arraycopy(subArray, 0, copy, 0, subArray.length);
        Arrays.sort(copy);
        StringBuilder sbt = new StringBuilder(new String(copy));
        sbt.reverse();
        copy = sbt.toString().toCharArray();
        boolean hasbigger = false;
        char first = subArray[0];
        char nextbiggerthanfirst = 0;
        int nextbiggerthanfirstindex = -1;
        boolean hasfound = false;
        for (int i = copy.length - 1; i >= 0; i--) {
            if (copy[i] != subArray[i]) {
                hasbigger = true;
            }
            if (copy[i] > first && !hasfound) {
                nextbiggerthanfirst = copy[i];
                for (int j = 0; j < subArray.length; j++) {
                    if (nextbiggerthanfirst == subArray[j]) {
                        nextbiggerthanfirstindex = j;
                    }
                }
                hasfound = true;
            }
            if (hasbigger & hasfound) {
                break;
            }
        }
        if (!hasbigger) {
            return -1;
        }
        if (subArray.length == 2) {
            char tmp = subArray[0];
            subArray[0] = subArray[1];
            subArray[1] = tmp;
            return Long.valueOf(new String(subArray));
        }

        char[] nextsub = new char[subArray.length - 1];
        System.arraycopy(subArray, 1, nextsub, 0, subArray.length - 1);
        long part = findBigger(nextsub);
        if (part != -1) {
            StringBuilder sb = new StringBuilder(subArray[0]);
            sb.append(part);
            return Long.valueOf(sb.toString());
        }
        char tmp = subArray[0];
        subArray[0] = nextbiggerthanfirst;
        subArray[nextbiggerthanfirstindex] = tmp;

        nextsub = new char[subArray.length - 1];
        System.arraycopy(subArray, 1, nextsub, 0, subArray.length - 1);
        Arrays.sort(nextsub);
        StringBuilder res = new StringBuilder();
        res.append(subArray[0]);
        res.append(new String(nextsub));
        return Long.valueOf(res.toString());

    }

    public static long nextBiggerNumber2(long n) {
        char[] digits = String.valueOf(n).toCharArray();
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] > digits[i - 1]) {
                int nBiggerIndex = i;
                for (int j = digits.length - 1; j > i; j--) {
                    if (digits[j] > digits[i - 1]) {
                        nBiggerIndex = j;
                        break;
                    }
                }
                char temp = digits[nBiggerIndex];
                digits[nBiggerIndex] = digits[i - 1];
                digits[i - 1] = temp;
                Arrays.sort(digits, i, digits.length);
                return Long.parseLong(new String(digits));
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //        System.out.println(NextBiggerNumber.nextBiggerNumber(12));
        //        System.out.println(NextBiggerNumber.nextBiggerNumber(513));
        //        System.out.println(NextBiggerNumber.nextBiggerNumber(2017));
        //        System.out.println(NextBiggerNumber.nextBiggerNumber(9));
        //        System.out.println(NextBiggerNumber.nextBiggerNumber(111));
        //        System.out.println(NextBiggerNumber.nextBiggerNumber(531));
        //        System.out.println(NextBiggerNumber.nextBiggerNumber(414));
        //        System.out.println(NextBiggerNumber.nextBiggerNumber(144));
        //        System.out.println(NextBiggerNumber.nextBiggerNumber(533022655));
        System.out.println(NextBiggerNumber.nextBiggerNumber(1592214961));
    }

}
