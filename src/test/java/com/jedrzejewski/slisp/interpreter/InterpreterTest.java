package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InterpreterTest {

    @Test
    public void testEval() throws Exception {
        assertEquals(
                new Num(15),
                TestUtils.evalString("(+ 1 (+ 2 0 (+ 0 0)) (+ 3 4 5))")
        );
    }

    @Test
    public void testTrueSymbol() throws Exception {
        assertEquals(
                new Bool(true),
                TestUtils.evalString("(if true true false)")
        );
    }

    @Test
    public void testFalseSymbol() throws Exception {
        assertEquals(
                new Bool(false),
                TestUtils.evalString("(if false true false)")
        );
    }
}
