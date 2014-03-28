package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class InterpreterTest {

    @Test
    public void testEval() throws Exception {
        assertEquals(
                new Num(15),
                TestUtils.evalString("(+ 1 (+ 2 0 (+ 0 0)) (+ 3 4 5))")
        );
    }
}
