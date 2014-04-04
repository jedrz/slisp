package com.jedrzejewski.slisp.lispobjects;

import org.junit.Assert;
import org.junit.Test;

public class NumTest {

    @Test
    public void testToString() {
        Assert.assertEquals(
                "1.0",
                new Num(1).toString()
        );
    }
}
