package org.lionbo.practice.codewars;

public class Line {

    public static String Tickets(int[] peopleInLine) {
        int[] count = new int[3];

        for (int i : peopleInLine) {
            if (i == 25)
                count[0]++;
            if (i == 50)
                count[1]++;
            if (i == 100)
                count[2]++;
        }

        int c1 = 0;
        int c2 = 0;
        int c4 = 0;

        if (count[2] > count[1]) {
            c1 = count[0] - count[1] - count[2];
            c4 = count[2] - count[1];
            if (c1 < 0)
                return "NO";
            if (c1 >= 3 * c4)
                return "YES";
            return "NO";
        } else {
            c1 = count[0] - count[1] - count[2];
            c2 = count[1] - count[2];
            if (c1 < 0)
                return "NO";
            if (c1 >= c2)
                return "YES";
            return "NO";
        }
    }

    public static String Tickets2(int[] peopleInLine) {
        int[] count = new int[3];

        for (int i : peopleInLine) {
            if (i == 25)
                count[0]++;
            if (i == 50) {
                count[0]--;
                count[1]++;
            }
            if (i == 100) {
                if (count[1] > 0) {
                    count[1]--;
                    count[0]--;
                } else {
                    count[0] -= 3;
                }
            }
        }
        if (count[0] < 0 || count[1] < 0)
            return "NO";
        return "YES";

    }

    public static void main(String[] args) {
        System.out.println(Line.Tickets2(new int[] { 25, 25, 25, 50 }));
        System.out.println(Line.Tickets2(new int[] { 25, 50, 100 }));
    }

}
