package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Bool;
import org.junit.Assert;
import org.junit.Test;

public class BuiltinsTest {

    @Test
    public void testEmpty() {
        Assert.assertEquals(
                new Bool(true),
                TestUtils.evalString("(empty? (list))")
        );

        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(empty? (list 1))")
        );
    }

    @Test
    public void testZero() {
        Assert.assertEquals(
                new Bool(true),
                TestUtils.evalString("(zero? 0)")
        );
        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(zero? 1)")
        );
    }
}
