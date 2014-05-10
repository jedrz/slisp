package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class IfFormTest {

    @Test
    public void testTrue() throws Exception {
        Assert.assertEquals(
                new Num(1),
                TestUtils.evalString("(if (= 1 1) 1 2)")
        );
    }

    @Test
    public void testFalse() throws Exception {
        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString("(if (= 1 2) 1 2)")
        );
    }

    @Test
    public void testMultipleFormElse() throws Exception {
        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString("(if false 0 1 2)")
        );
    }
}
