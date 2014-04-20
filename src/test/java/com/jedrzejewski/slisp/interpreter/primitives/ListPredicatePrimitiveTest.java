package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Bool;
import org.junit.Assert;
import org.junit.Test;

public class ListPredicatePrimitiveTest {

    @Test
    public void testArgIsList() throws Exception {
        Assert.assertEquals(
                new Bool(true),
                TestUtils.evalString("(list? '())")
        );
    }

    @Test
    public void testArgisNotList() throws Exception {
        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(or (list? 'a) (list? 1))")
        );
    }
}
