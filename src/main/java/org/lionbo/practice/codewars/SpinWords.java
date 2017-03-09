package org.lionbo.practice.codewars;

public class SpinWords {

    public String spinWords(String sentence) {
        if (sentence == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] strs = sentence.split(" ");
        for (String str : strs) {
            if (str.length() > 4) {
                sb.append(this.reverse(str));
            } else {
                sb.append(str);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public String reverse(String str) {
        char[] chars = str.toCharArray();
        int clength = chars.length;
        for (int i = 0; i < clength / 2; i++) {
            char tmp = chars[i];
            chars[i] = chars[clength - i - 1];
            chars[clength - i - 1] = tmp;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(new SpinWords().spinWords("emocleW"));
        System.out.println(new SpinWords().spinWords("Hey wollef sroirraw"));
    }
}
