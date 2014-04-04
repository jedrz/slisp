package com.jedrzejewski.slisp.lispobjects;

import org.junit.Assert;
import org.junit.Test;

public class BoolTest {

    @Test
    public void testToString() {
        Assert.assertEquals(
                "false",
                new Bool(false).toString()
        );

        Assert.assertEquals(
                "true",
                new Bool(true).toString()
        );
    }
}
