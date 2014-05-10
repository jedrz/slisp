package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import java.util.List;

public abstract class Callable implements LispObject {

    /**
     * Calls a callable object.
     * @param args unevaluated args
     * @param scope current scope
     * @return result of callable call
     * @throws InterpreterException
     */
    public abstract LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException;

    /**
     * Validates given arguments.
     * This method is called before <code>call</code>.
     * @param validator a wrapper class around arguments to help validate them
     * @throws InterpreterException
     */
    public abstract void validate(ArgsValidator validator)
            throws InterpreterException;

    // To be able to check evaluated args.
    public void validate(ArgsValidator validator, Scope scope)
            throws InterpreterException {
        validate(validator);
    }

    @Override
    public LispObject eval(Scope scope) {
        return this;
    }
}
