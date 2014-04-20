package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class EvalPrimitiveTest {

    @Test
    public void testEval() {
        Assert.assertEquals(
                new Num(17),
                TestUtils.evalString("(eval '(let [a 10] (+ 3 4 a)))")
        );
    }
}
