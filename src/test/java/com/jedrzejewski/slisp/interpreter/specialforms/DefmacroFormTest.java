package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.Nil;
import com.jedrzejewski.slisp.lispobjects.Sym;
import org.junit.Assert;
import org.junit.Test;

public class DefmacroFormTest {

    @Test
    public void testSampleMacro() throws Exception {
        Interpreter interpreter = new Interpreter();
        TestUtils.evalString("(defmacro when [cond & body]"
                             + " (list 'if cond"
                             + " (cons 'do body)"
                             + " nil))",
                             interpreter);
        Assert.assertEquals(
                new Sym("symbol"),
                TestUtils.evalString(
                        "(when true (print 'side-effect) 'symbol)",
                        interpreter
                )
        );
        Assert.assertEquals(
                new Nil(),
                TestUtils.evalString("(when false 'symbol)",
                                     interpreter)
        );
    }
}
