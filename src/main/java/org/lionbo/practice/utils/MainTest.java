package org.lionbo.practice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainTest {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println(sdf.format(new Date()));
    }
}
