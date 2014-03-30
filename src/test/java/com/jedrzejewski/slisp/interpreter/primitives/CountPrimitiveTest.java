package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class CountPrimitiveTest {

    @Test
    public void testNonEmptyListLength() {
        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString("(count (list 1 2))")
        );
    }

    @Test
    public void testEmptyListLength() {
        Assert.assertEquals(
                new Num(0),
                TestUtils.evalString("(count (list))")
        );
    }

    // TODO: test str length.
}
