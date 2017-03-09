package org.lionbo.practice.codewars;

import java.util.LinkedList;
import java.util.List;

public class CodeDecode {

    public static String code(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int slength = s.length();
        int n = (int) Math.sqrt(slength);
        if (slength != n * n) {
            n = n + 1;
            StringBuilder sb = new StringBuilder(s);
            for (int i = slength; i < n * n; i++) {
                char c = 11;
                sb.append(c);
            }
            s = sb.toString();
        }
        List<String> origin = new LinkedList<>();
        for (int i = 0; i < n * n; i += n) {
            origin.add(s.substring(i, i + n));
        }
        List<String> target = new LinkedList<>();
        List<String> trans = new LinkedList<>();

        for (int i = 0; i < origin.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (String str : origin) {
                sb.append(str.charAt(i));
            }
            target.add(sb.toString());
        }

        for (String str : target) {

            trans.add(new StringBuilder(str).reverse().toString());
        }
        StringBuilder sb = new StringBuilder();

        for (String str : trans) {
            sb.append(str);
            sb.append("\n");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();

    }

    public static String decode(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] strArr = s.split("\n");
        List<String> target = new LinkedList<>();
        List<String> origin = new LinkedList<>();
        for (int i = 0; i < strArr.length; i++) {
            StringBuilder sb = new StringBuilder(strArr[i]);
            target.add(sb.reverse().toString());
        }
        for (int i = 0; i < target.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < target.size(); j++) {
                sb.append(target.get(j).charAt(i));
            }
            origin.add(sb.toString());
        }
        StringBuilder sb = new StringBuilder();
        for (String str : origin) {
            sb.append(str);
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(CodeDecode.decode(CodeDecode.code("I.was.going.fishing.that.morning.at.ten.o'clock")));
    }
}
