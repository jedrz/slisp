package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Str;
import org.junit.Assert;
import org.junit.Test;

public class SubsPrimitiveTest {

    @Test
    public void test() throws Exception {
        Assert.assertEquals(
                new Str("string"),
                TestUtils.evalString("(subs \"sub string sub\" 4 10)")
        );

    }
}
