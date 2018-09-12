package com.cultivation.javaBasic.util;

public class ValueHolder<T> {
    public ValueHolder(){}
    public ValueHolder(T value) {
        this.value = value;
    }

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
