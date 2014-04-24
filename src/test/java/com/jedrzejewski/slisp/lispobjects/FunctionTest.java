package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.interpreter.Scope;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

public class FunctionTest {

    @Test
    public void testAmpersandSymbolInArgNames() throws Exception {
        Interpreter interpreter = new Interpreter();
        TestUtils.evalString(
                "(set! f (fn [a & r]"
                + "(if (empty? r)"
                + "a"
                + "(+ a (first r)))))",
                interpreter);
        Assert.assertEquals(
                new Num(3),
                TestUtils.evalString("(f 1 2 3)", interpreter)
        );
        Assert.assertEquals(
                new Num(1),
                TestUtils.evalString("(f 1)", interpreter)
        );
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals(
                "fn",
                new Function(new LinkedList<>(), new Nil(), new Scope()).toString()
        );
    }
}
