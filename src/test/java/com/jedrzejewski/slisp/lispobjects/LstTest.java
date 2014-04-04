package com.jedrzejewski.slisp.lispobjects;

import org.junit.Assert;
import org.junit.Test;

public class LstTest {

    @Test
    public void testToString() {
        Lst lst = new Lst();
        lst.add(new Num(1));
        lst.add(new Sym("a"));
        lst.add(new Lst());
        Assert.assertEquals(
                "(1.0 a ())",
                lst.toString()
        );
    }
}
