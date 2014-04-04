package com.jedrzejewski.slisp.lispobjects;

import org.junit.Assert;
import org.junit.Test;

public class SymTest {

    @Test
    public void testToString() {
        Assert.assertEquals(
                "symbol",
                new Sym("symbol").toString()
        );
    }
}
