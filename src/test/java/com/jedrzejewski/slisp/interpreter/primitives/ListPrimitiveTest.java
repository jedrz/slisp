package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ListPrimitiveTest {

    @Test
    public void testCreatingList() {
        Assert.assertEquals(
                Arrays.asList(new Num("1"), new Bool(true), new Bool(false)),
                TestUtils.evalString(
                        "(list (+ 0 1) (if 0 true false) (= 0 1))"
                )
        );
    }
}
