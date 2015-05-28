package com.serkanc;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class DummyTest {

    @Test
    public void testX() throws Exception {
        Dummy mock = Mockito.mock(Dummy.class);
        mock.x();
        assertThat(true, is(true));
    }
}