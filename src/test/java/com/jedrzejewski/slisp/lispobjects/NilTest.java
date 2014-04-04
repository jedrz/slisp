package com.jedrzejewski.slisp.lispobjects;

import org.junit.Assert;

public class NilTest {

    public void testToString() {
        Assert.assertEquals(
                "nil",
                new Nil().toString()
        );
    }
}
