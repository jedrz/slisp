package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Nil;
import com.jedrzejewski.slisp.lispobjects.Str;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ReadPrimitive extends Primitive {

    protected BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in));

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        try {
            return new Str(bufferedReader.readLine());
        } catch (IOException e) {
            return new Nil();
        }
    }

    @Override
    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.shouldSize(size -> size == 0)
                 .ifNotThenThrow(
                         WrongNumberOfArgsException.exactly(0)
                                                   .is(validator.getArgsSize()));
    }
}
