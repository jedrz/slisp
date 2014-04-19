package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class BuiltinsTest {

    @Test
    public void testEmpty() {
        Assert.assertEquals(
                new Bool(true),
                TestUtils.evalString("(empty? (list))")
        );

        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(empty? (list 1))")
        );
    }

    @Test
    public void testZero() {
        Assert.assertEquals(
                new Bool(true),
                TestUtils.evalString("(zero? 0)")
        );
        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(zero? 1)")
        );
    }

    @Test
    public void testCons() {
        Assert.assertEquals(
                Arrays.asList(new Num(1), new Num(2), new Num(3)),
                TestUtils.evalString("(cons 1 '(2 3))")
        );
    }

    @Test
    public void testConj() {
        Assert.assertEquals(
                Arrays.asList(new Num(1), new Num(2), new Num(3)),
                TestUtils.evalString("(conj '(1 2) 3)")
        );
    }

    @Test
    public void testMap() {
        Assert.assertEquals(
                TestUtils.evalString("'(1 4 9 16)"),
                TestUtils.evalString("(map (fn [n] (* n n)) '(1 2 3 4))")
        );
    }
}
