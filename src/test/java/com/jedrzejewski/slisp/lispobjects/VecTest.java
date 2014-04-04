package com.jedrzejewski.slisp.lispobjects;

import org.junit.Assert;
import org.junit.Test;

public class VecTest {

    @Test
    public void testToString() {
        Vec vec = new Vec();
        vec.add(new Num(1));
        vec.add(new Sym("a"));
        vec.add(new Vec());
        Assert.assertEquals(
                "[1.0 a []]",
                vec.toString()
        );
    }
}
