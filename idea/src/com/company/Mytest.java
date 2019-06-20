package com.company;

public class Mytest {

    private int a;
    private String str;

    public Mytest() {
        a = 10;
        str = "asdasdasdasd";
    }

    public Mytest(int a) {
        this.a = a;
    }

    public void test() {
        System.out.println(a + str);
    }
}
