package org.lionbo.practice.javabasic;

public class StaticMethodTest {

    //    private static StaticMethodTest instance = new StaticMethodTest();

    private StaticMethodTest() {
        System.out.println("class init");
    }

    public static void getSomething() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        StaticMethodTest.getSomething();
    }
}
