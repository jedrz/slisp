package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import org.junit.Assert;
import org.junit.Test;

public class DoFormTest {

    @Test
    public void testCall() throws Exception {
        Assert.assertEquals(new Num(3), TestUtils.evalString("(do 1 2 3)"));
    }
}
