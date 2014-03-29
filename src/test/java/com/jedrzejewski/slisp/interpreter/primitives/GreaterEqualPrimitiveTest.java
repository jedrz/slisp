package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class GreaterEqualPrimitiveTest {

    private GreaterEqualPrimitive greaterEqualPrimitive = new GreaterEqualPrimitive();

    @Test
    public void testArgsGreaterEqual() {
        Assert.assertEquals(
                new Bool(true),
                greaterEqualPrimitive.call(
                        Arrays.asList(new Num(3), new Num(3), new Num(1)
                ))
        );
    }
}