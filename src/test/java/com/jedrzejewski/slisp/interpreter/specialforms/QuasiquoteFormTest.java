package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class QuasiquoteFormTest {

    @Ignore
    @Test
    public void testUnquoteFunction() {
        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString("`~(+ 1 1)")
        );
    }

    @Ignore
    @Test
    public void testUnquoteInList() {
        Assert.assertEquals(
                Arrays.asList(new Num(1), new Num(2)),
                TestUtils.evalString("`(1 ~(+ 1 1))")
        );
    }

    @Ignore
    @Test
    public void testQuasiquote() {
        Assert.assertEquals(
                Arrays.asList(new Num(1), new Num(2), new Num(3), new Num(4)),
                TestUtils.evalString("`(1 ~(+ 1 1) ~@(list 3 4))")
        );
    }
}
