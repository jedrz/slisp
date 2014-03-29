package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class LessPrimitiveTest {

    private LessPrimitive lessPrimitive = new LessPrimitive();

    @Test
    public void testArgsLesser() {
        Assert.assertEquals(
                new Bool(true),
                lessPrimitive.call(
                        Arrays.asList(new Num(1), new Num(2), new Num(3))
                )
        );
    }

    @Test
    public void testArgsNotLesser() {
        Assert.assertEquals(
                new Bool(false),
                lessPrimitive.call(
                        Arrays.asList(new Num(2), new Num(1))
                )
        );
    }
}
