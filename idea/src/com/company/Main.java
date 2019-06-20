package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("asasdd");
        System.out.println("asdasd");
        Mytest mt = new Mytest(123);
        mt.test();
        Map map = new TreeMap();
        map.keySet();
        boolean is = map.containsKey(1);
        System.out.println(is);
        Stack stack = new Stack<String>();
        for(int i = 0; i < 10; i ++) {
            stack.push("nmsl" + i);
        }
        while(!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
