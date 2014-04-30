package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeNumException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Num;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.DoubleStream;

public abstract class MathOperationPrimitive extends Primitive {

    public DoubleStream convertToDoubleStream(List<LispObject> args)
            throws InterpreterException {
        validate(args);

        List<Num> nums = new LinkedList<>();
        for (LispObject o : args) {
            nums.add((Num) o);
        }
        return nums.stream().mapToDouble(n -> n.getValue());
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        ArgsValidator validator = new ArgsValidator(args);

        validator.shouldSize(size -> size >= 1)
                 .ifNotThenThrow(WrongNumberOfArgsException.atLeast(1)
                                                           .is(args.size()));

        validator.eachShould(arg -> arg instanceof Num)
                 .ifNotThenThrow(ArgShouldBeNumException.class);
    }
}
