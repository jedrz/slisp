package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ConcatPrimitiveTest {

    @Test
    public void concatNoLists() {
        Assert.assertEquals(
                new Lst(),
                TestUtils.evalString("(concat)")
        );
    }

    @Test
    public void concatOneList() {
        Assert.assertEquals(
                Arrays.asList(new Num(1)),
                TestUtils.evalString("(concat '(1))")
        );
    }

    @Test
    public void concatMultipleLists() {
        Assert.assertEquals(
                Arrays.asList(new Num(1), new Num(2), new Num(3)),
                TestUtils.evalString("(concat '(1) (list 2 3))")
        );
    }
}
