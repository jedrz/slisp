package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class LetFormTest {

    @Test
    public void testLet() {
        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString(
                        "(let [a 1\n" +
                        "b (+ a 1)]\n" +
                        "a\n" +
                        "b)\n"
                )
        );
    }

    // TODO: check if symbols are not bound to values after let form.
}
