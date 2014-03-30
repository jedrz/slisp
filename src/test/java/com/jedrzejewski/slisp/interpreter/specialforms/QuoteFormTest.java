package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Num;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class QuoteFormTest {

    @Test
    public void testQuote() {
        Assert.assertEquals(
                Arrays.asList(new Num(1), new Sym("a")),
                TestUtils.evalString("'(1 a)")
        );
    }
}
