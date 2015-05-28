package com.serkanc;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class Stack {

    private LinkedList<Object> list = new LinkedList<Object>();

    public Object pop() {
        if (list.isEmpty()) {
            throw new EmptyStackException();
        }
        return list.removeLast();
    }

    public void push(Object item) {
        list.add(item);
    }
}
