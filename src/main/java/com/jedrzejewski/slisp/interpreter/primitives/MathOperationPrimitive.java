package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.exceptions.ArgsShouldBeNumsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.DoubleStream;

public abstract class MathOperationPrimitive extends Primitive {

    public DoubleStream convertToDoubleStream(List<LispObject> args)
            throws ArgsShouldBeNumsException {
        List<Num> nums = new LinkedList<>();
        try {
            for (LispObject o : args) {
                nums.add((Num) o);
            }
        } catch (ClassCastException e) {
            throw new ArgsShouldBeNumsException();
        }
        return nums.stream().mapToDouble(n -> n.getValue());
    }
}
