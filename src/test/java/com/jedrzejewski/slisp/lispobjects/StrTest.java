package com.jedrzejewski.slisp.lispobjects;

import org.junit.Assert;
import org.junit.Test;

public class StrTest {

    @Test
    public void testInternalStringofStr() {
        Assert.assertEquals(
                "\n",
                new Str("\n").getString()
        );
    }
}
