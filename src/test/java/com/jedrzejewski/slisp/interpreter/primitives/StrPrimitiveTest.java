package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Str;
import org.junit.Assert;
import org.junit.Test;

public class StrPrimitiveTest {

    @Test
    public void testMultipleTypes() throws Exception {
        Assert.assertEquals(
                new Str("(1.0 \"s\")a()str"),
                TestUtils.evalString("(str '(1 \"s\") 'a '() \"str\")")
        );
    }
}
