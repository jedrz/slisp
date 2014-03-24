package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.parser.lispobjects.LispObject;
import com.jedrzejewski.slisp.parser.lispobjects.Num;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

public class DividePrimitiveTest {

    @Test
    public void testCall() throws Exception {
        DividePrimitive pm = new DividePrimitive();

        Assert.assertEquals(
                new Num(5),
                pm.call(Arrays.asList(new Num(5)))
        );

        List<LispObject> numbers = Stream
                .of(10, 2, -1)
                .map(Num::new)
                .collect(Collectors.toList());
        Assert.assertEquals(new Num(-5), pm.call(numbers));
    }
}
