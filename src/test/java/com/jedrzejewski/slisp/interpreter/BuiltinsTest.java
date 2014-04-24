package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.TestUtils;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.Nil;
import com.jedrzejewski.slisp.lispobjects.Num;
import com.jedrzejewski.slisp.lispobjects.Str;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class BuiltinsTest {

    @Test
    public void testEmpty() throws Exception {
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
    public void testZero() throws Exception {
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
    public void testCons() throws Exception {
        Assert.assertEquals(
                Arrays.asList(new Num(1), new Num(2), new Num(3)),
                TestUtils.evalString("(cons 1 '(2 3))")
        );
    }

    @Test
    public void testConj() throws Exception {
        Assert.assertEquals(
                Arrays.asList(new Num(1), new Num(2), new Num(3)),
                TestUtils.evalString("(conj '(1 2) 3)")
        );
    }

    @Test
    public void testMap() throws Exception {
        Assert.assertEquals(
                TestUtils.evalString("'(1 4 9 16)"),
                TestUtils.evalString("(map (fn [n] (* n n)) '(1 2 3 4))")
        );
    }

    @Test
    public void testFilter() throws Exception {
        Assert.assertEquals(
                TestUtils.evalString("'(2 4 6 8)"),
                TestUtils.evalString("(do"
                                     + "(defn even? [n]"
                                     + "(= (% n 2) 0))"
                                     + "(filter even? '(1 2 3 4 5 6 7 8)))")
        );
    }

    @Test
    public void testReduce() throws Exception {
        Assert.assertEquals(
                new Num(15),
                TestUtils.evalString("(reduce (fn [acc n]\n"
                                     + "(+ acc (* n n)))\n"
                                     + "'(1 2 3)\n"
                                     + "1)")
        );
    }

    @Test
    public void testNot() throws Exception {
        Assert.assertEquals(
                new Bool(true),
                TestUtils.evalString("(not nil)")
        );
        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(not 'symbol)")
        );
    }

    @Test
    public void testAnd() throws Exception {
        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(and true false)")
        );

        Assert.assertEquals(
                new Num(2),
                TestUtils.evalString("(and 1 2)")
        );

        Assert.assertEquals(
                new Num(1),
                TestUtils.evalString("(and 1)")
        );
    }

    @Test
    public void testOr() throws Exception {
        Assert.assertEquals(
                new Num(1),
                TestUtils.evalString("(or nil 1 false)")
        );

        Assert.assertEquals(
                new Num(1),
                TestUtils.evalString("(or 1)")
        );

        Assert.assertEquals(
                new Bool(false),
                TestUtils.evalString("(or false nil false)")
        );
    }

    @Test
    public void testWhen() throws Exception {
        Assert.assertEquals(
                new Num(1),
                TestUtils.evalString("(when (+ 1 2) 'side-effect 1)")
        );
        Assert.assertEquals(
                new Nil(),
                TestUtils.evalString("(when (= 1 0) 'side-effect 1)")
        );
    }

    @Test
    public void testCharAt() throws Exception {
        Assert.assertEquals(
                new Str("a"),
                TestUtils.evalString("(char-at \"cba\" 2)")
        );
    }
}
