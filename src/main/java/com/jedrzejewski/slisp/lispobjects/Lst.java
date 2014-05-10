package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeCallableException;
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
        // Getting function to call
        //
        // How list is evaluated (actually how function call is performed).
        // At first the first argument needs to be evaluated, which should
        // evaluate to a callable object. Usually evaluating it results in
        // find a symbol in given scope. But also an anonymous function
        // can be defined inline.
        //
        // Now the function can be called with the rest of the list
        // as arguments (not evaluated ones). If the callable is a function
        // the before actual evaluation arguments are evaluated.
        LispObject first = get(0);
        List<LispObject> rest = subList(1, size());
        LispObject firstEvaled =  first.eval(scope);
        if (!(firstEvaled instanceof Callable)) {
            throw new ArgShouldBeCallableException();
        }
        Callable callable = (Callable) firstEvaled;
        try {
            callable.validate(new ArgsValidator(rest), scope);
            return callable.call(rest, scope);
        } catch (ExactFunctionException e) {
            // Inject function name in which exception was raised.
            // Before injecting name check if wasn't injected deeper.
            if (e.getFuncName() == null && !first.toString().equals("do")) {
                e.setFuncName(first.toString());
            }
            throw e;
        }
    }
}
