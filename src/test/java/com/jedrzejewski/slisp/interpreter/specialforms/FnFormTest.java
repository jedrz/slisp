package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class FnFormTest {

    @Test
    public void testBindThenCall() throws Exception {
        Interpreter interpreter = new Interpreter();
        TestUtils.evalString("(set! inc (fn [n] (+ n 1)))", interpreter);
        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString("(inc 1)", interpreter)
        );
    }

    @Test
    public void testDefineAndCall() throws Exception {
        Assert.assertEquals(
                new Num(6),
                TestUtils.evalString("((fn [a b] (* a b)) 3 2)")
        );
    }
}
