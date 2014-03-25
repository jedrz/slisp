package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class FnFormTest {

    @Test
    public void testBindAndCall() throws Exception {
        Interpreter interpreter = new Interpreter();
        TestUtils.evalString("(set! inc (fn [n] (+ n 1)))", interpreter);
        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString("(inc 1)", interpreter)
        );
    }

    // TODO: add lexical binding test
}
