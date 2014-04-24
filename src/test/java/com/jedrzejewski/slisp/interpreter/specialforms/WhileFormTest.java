package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class WhileFormTest {

    @Test
    public void testWhile() throws Exception {
        Interpreter interpreter = new Interpreter();
        TestUtils.evalString("(set! a 10)", interpreter);
        TestUtils.evalString("(set! b 15)", interpreter);
        TestUtils.evalString(
                "(while (> a 0)\n" +
                "(set! a (- a 1))\n" +
                "(set! b (- b 1)))\n",
                interpreter
        );
        Assert.assertEquals(
                new Num(5),
                TestUtils.evalString("b", interpreter)
        );
    }
}
