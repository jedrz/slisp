package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Bool;
import org.junit.Assert;
import org.junit.Test;

public class EqualsPrimitveTest {

    @Test
    public void testTrue() throws Exception {
        Assert.assertEquals(
                new Bool(true),
                TestUtils.evalString("(equals '(1 2)"
                                     + "(list 1 2)"
                                     + "(map (fn [n] (+ n 1)) '(0 1)))")
        );
    }

    @Test
    public void testFalse() throws Exception {
        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(equals 1 2)")
        );
    }
}
