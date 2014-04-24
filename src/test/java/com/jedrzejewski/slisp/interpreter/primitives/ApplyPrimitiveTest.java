package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class ApplyPrimitiveTest {

    @Test
    public void testApply() throws Exception {
        Assert.assertEquals(
                new Num(10),
                TestUtils.evalString("(apply + 1 2 '(3 4))")
        );
    }

    @Test
    public void testApplyingListOfLists() throws Exception {
        Assert.assertEquals(
                new Num(1),
                TestUtils.evalString("(apply first '((1 2)))")
        );
    }
}
