package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class LessEqualsPrimitveTest {

    private LessEqualsPrimitive lessEqualsPrimitive = new LessEqualsPrimitive();

    @Test
    public void testArgsLesserEquals() {
        Assert.assertEquals(
                new Bool(true),
                lessEqualsPrimitive.call(
                        Arrays.asList(new Num(1), new Num(1), new Num(2))
                )
        );
    }
}
