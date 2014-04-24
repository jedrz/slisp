package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class DefnFormTest {

    @Test
    public void testRecursiveFn() throws Exception {
        Interpreter interpreter = new Interpreter();
        TestUtils.evalString(
                "(defn factorial [n]\n" +
                        "(if (= n 1)\n" +
                        "1\n" +
                        "(* n (factorial (- n 1)))))",
                interpreter
        );
        Assert.assertEquals(
                new Num(120),
                TestUtils.evalString("(factorial 5)", interpreter)
        );
    }

    @Test
    public void testNonRecursiveFn() throws Exception {
        Interpreter interpreter = new Interpreter();
        TestUtils.evalString(
                "(defn sum [a b]\n" +
                        "(+ a b))",
                interpreter
        );
        Assert.assertEquals(
                new Num(3),
                TestUtils.evalString("(sum 1 2)", interpreter)
        );
    }

    @Test
    public void testLexicalBinding() throws Exception {
        Interpreter interpreter = new Interpreter();
        TestUtils.evalString(
                "(defn make-adder [init]\n" +
                "(fn [n]\n" +
                "(+ n init)))\n",
                interpreter
        );
        TestUtils.evalString(
                "(set! one-adder (make-adder 1))",
                interpreter
        );
        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString(
                        "(one-adder 1)",
                        interpreter
                )
        );
    }

    // TODO: test defn with docstring
}
