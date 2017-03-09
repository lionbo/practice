package org.lionbo.practice.codewars;

public class TenMinWalk {

    public static boolean isValid(char[] walk) {
        if (walk == null || walk.length != 10)
            return false;
        int[] count = new int[4];

        for (char c : walk) {
            if (c == 'n')
                count[0] = count[0] + 1;
            if (c == 's')
                count[1] = count[1] + 1;
            if (c == 'w')
                count[2] = count[2] + 1;
            if (c == 'e')
                count[3] = count[3] + 1;
        }
        if (count[0] != count[1])
            return false;
        if (count[2] != count[3])
            return false;

        return true;
    }
}
