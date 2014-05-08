package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ExactFunctionException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Lst extends LinkedList<LispObject> implements LispObject {

    @Override
    public String toString() {
        return "("
                + stream()
                .map(obj -> obj.toString())
                .collect(Collectors.joining(" "))
                + ")";
    }

    @Override
    public LispObject eval(Scope scope) throws InterpreterException {
        LispObject first = get(0);
        List<LispObject> rest = subList(1, size());
        Callable callable = (Callable) first.eval(scope);
        try {
            callable.validate(new ArgsValidator(rest), scope);
            return callable.call(rest, scope);
        } catch (ExactFunctionException e) {
            // Inject function name in which exception was raised.
            e.setFuncName(first.toString());
            throw e;
        }
    }
}
