package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.TestUtils;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class MacroTest {

    @Test
    public void testBuildingScopeForMacro() throws Exception {
        // All args shouldn't be evaluated.
        Assert.assertEquals(
                Arrays.asList(new Sym("+"), new Num(1)),
                TestUtils.evalString("(do (defmacro a-macro [x] x) (a-macro '(+ 1)))")
        );
    }
}