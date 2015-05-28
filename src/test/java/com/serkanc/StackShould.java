package com.serkanc;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class StackShould {

    private Stack stack;

    @Before
    public void setUp() throws Exception {
        stack = new Stack();
    }

    @Test(expected = EmptyStackException.class) public void
    throw_exception_when_its_empty() throws Exception
    {
        stack.pop();
    }

    @Test public void
    pop_last_pushed_item() throws Exception
    {
        Object item = new Object();

        stack.push(item);

        assertThat(stack.pop(), is(item));
    }

    @Test public void
    pop_pushed_items_in_the_reverse_order_that_they_were_pushed() throws Exception
    {
        Object item = new Object();
        Object item2 = new Object();
        Object item3 = new Object();

        stack.push(item);
        stack.push(item2);
        stack.push(item3);

        assertThat(stack.pop(), is(item3));
        assertThat(stack.pop(), is(item2));
        assertThat(stack.pop(), is(item));
    }

    @Test public void
    pop_last_pushed_item_regardless_of_the_items_in_stack() throws Exception
    {
        Object item = new Object();
        Object item2 = new Object();
        Object item3 = new Object();

        stack.push(item);

        stack.push(item2);
        assertThat(stack.pop(), is(item2));

        stack.push(item2);
        stack.push(item3);
        assertThat(stack.pop(), is(item3));
        assertThat(stack.pop(), is(item2));

        assertThat(stack.pop(), is(item));

        try {
            stack.pop();
            fail("should have thrown exception here");
        }
        catch (EmptyStackException e) {
            System.err.println("no elements on stack");
        }
    }

    @Test public void
    allow_pushing_same_instance_many_times() throws Exception
    {
        Object item = new Object();

        stack.push(item);
        stack.push(item);

        assertThat(stack.pop(), is(item));
        assertThat(stack.pop(), is(item));
    }
}
