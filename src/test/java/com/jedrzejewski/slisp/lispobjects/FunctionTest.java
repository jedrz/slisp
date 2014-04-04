package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

public class FunctionTest {

    @Test
    public void testToString() {
        Assert.assertEquals(
                "fn",
                new Function(new LinkedList<>(), new Nil(), new Scope()).toString()
        );
    }
}
