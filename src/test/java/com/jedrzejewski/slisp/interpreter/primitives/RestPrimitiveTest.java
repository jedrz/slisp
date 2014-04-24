package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class RestPrimitiveTest {

    @Test
    public void testNonEmptyList() throws Exception {
        Assert.assertEquals(
                Arrays.asList(new Num(2)),
                TestUtils.evalString("(rest (list 1 2))")
        );
    }

    @Test
    public void testOneElementList() throws Exception {
        Assert.assertEquals(
                new Lst(),
                TestUtils.evalString("(rest (list 1))")
        );
    }

    @Test
    public void testEmptyList() throws Exception {
        Assert.assertEquals(
                new Lst(),
                TestUtils.evalString("(rest (list))")
        );
    }
}