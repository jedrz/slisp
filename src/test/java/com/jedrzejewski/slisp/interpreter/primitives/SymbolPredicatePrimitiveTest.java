package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Bool;
import org.junit.Assert;
import org.junit.Test;

public class SymbolPredicatePrimitiveTest {

    @Test
    public void testArgIsSymbol() throws Exception {
        Assert.assertEquals(
                new Bool(true),
                TestUtils.evalString("(symbol? 'a)")
        );
    }

    @Test
    public void testArgIsNotSymbol() throws Exception {
        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(or (symbol? defn) (symbol? '()))")
        );
    }
}
