package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class ModuloPrimitiveTest {

    @Test
    public void testCall() throws Exception {
        ModuloPrimitive mp = new ModuloPrimitive();
        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString("(% 11 3)")
        );
    }
}
