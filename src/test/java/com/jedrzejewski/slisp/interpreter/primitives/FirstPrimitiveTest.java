package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Nil;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class FirstPrimitiveTest {

    @Test
    public void testNonEmptyList() throws Exception {
        Assert.assertEquals(
                new Num(1),
                TestUtils.evalString("(first (list 1 2))")
        );
    }

    @Test
    public void testEmptyList() throws Exception {
        Assert.assertEquals(
                new Nil(),
                TestUtils.evalString("(first (list))")
        );
    }
}
