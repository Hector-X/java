package com.cultivation.javaBasic.util;

public class MyInteger implements Comparable<MyInteger> {
    private int number;

    public MyInteger(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(MyInteger myInteger) {
        if (this.number == myInteger.number) return 0;
        return this.number > myInteger.number ? 1 : -1;
    }
}
