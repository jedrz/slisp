package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class LessPrimitiveTest {

    private LessPrimitive lessPrimitive = new LessPrimitive();

    @Test
    public void testArgsLesser() throws Exception {
        Assert.assertEquals(
                new Bool(true),
                lessPrimitive.callWithEvaluatedArgs(
                        Arrays.asList(new Num(1), new Num(2), new Num(3)),
                        null
                )
        );
    }

    @Test
    public void testArgsNotLesser() throws Exception {
        Assert.assertEquals(
                new Bool(false),
                lessPrimitive.callWithEvaluatedArgs(
                        Arrays.asList(new Num(2), new Num(1)),
                        null
                )
        );
    }
}
