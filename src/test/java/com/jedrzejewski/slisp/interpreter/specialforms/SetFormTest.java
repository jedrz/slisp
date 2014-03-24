package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.interpreter.Interpreter;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import com.jedrzejewski.slisp.parser.lispobjects.Sym;
import org.junit.Assert;
import org.junit.Test;

public class SetFormTest {

    @Test
    public void testSet() throws Exception {
        Interpreter interpreter = new Interpreter();
        Assert.assertEquals(
                new Sym("var"),
                TestUtils.evalString("(set! var (+ 1 2))", interpreter)
        );
        Assert.assertEquals(
                new Num(3),
                TestUtils.evalString("var", interpreter)
        );
    }
}
