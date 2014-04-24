package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class GreaterPrimitiveTest {

    private GreaterPrimitive greaterPrimitive = new GreaterPrimitive();

    @Test
    public void testArgsGreater() throws Exception {
        Assert.assertEquals(
                new Bool(true),
                greaterPrimitive.callWithEvaluatedArgs(
                        Arrays.asList(new Num(3), new Num(2), new Num(1)),
                        null
                )
        );
    }

    @Test
    public void testArgsNotGreater() throws Exception {
        Assert.assertEquals(
                new Bool(false),
                greaterPrimitive.callWithEvaluatedArgs(
                        Arrays.asList(new Num(1), new Num(1)),
                        null
                )
        );
    }
}
