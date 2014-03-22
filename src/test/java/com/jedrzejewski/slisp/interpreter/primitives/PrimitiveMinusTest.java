package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.parser.LispObject;
import com.jedrzejewski.slisp.parser.Number;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PrimitiveMinusTest {

    @Test
    public void testCall() throws Exception {
        PrimitiveMinus pm = new PrimitiveMinus();

        org.junit.Assert.assertEquals(
                new Number(-5),
                pm.call(Arrays.asList(new Number(5)))
        );

        List<LispObject> numbers = Stream
                .of(10, 3, -1)
                .map(Number::new)
                .collect(Collectors.toList());
        org.junit.Assert.assertEquals(new Number(8), pm.call(numbers));
    }
}
