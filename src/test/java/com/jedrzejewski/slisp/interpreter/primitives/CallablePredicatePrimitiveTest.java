package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Bool;
import org.junit.Assert;
import org.junit.Test;

public class CallablePredicatePrimitiveTest {

    @Test
    public void testArgIsCallable() throws Exception {
        Assert.assertEquals(
                new Bool(true),
                TestUtils.evalString("(callable? first)")
        );

    }

    @Test
    public void testArgIsNotCallable() throws Exception {
        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(callable? true)")
        );
    }
}
