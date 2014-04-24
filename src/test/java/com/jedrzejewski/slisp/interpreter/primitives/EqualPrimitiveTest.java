package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class EqualPrimitiveTest {

    private EqualPrimitive equalPrimitive = new EqualPrimitive();

    @Test
    public void testArgsEqual() throws Exception {
        Assert.assertEquals(
                new Bool(true),
                equalPrimitive.callWithEvaluatedArgs(
                        Arrays.asList(new Num(1), new Num(1)), null)
        );
    }

    @Test
    public void testArgsNotEqual() throws Exception {
        Assert.assertEquals(
                new Bool(false),
                equalPrimitive.callWithEvaluatedArgs(
                        Arrays.asList(new Num(1), new Num(2)), null)
        );
    }
}
