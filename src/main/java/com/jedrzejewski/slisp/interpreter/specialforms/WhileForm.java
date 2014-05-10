package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;

public class WhileForm extends SpecialForm {

    /**
     * Repeatably evalutes the body (args from the second) until the condition
     * evalutes to true (the first argument).
     * @param args unevaluated args
     * @param scope current scope
     * @throws InterpreterException
     */
    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        LispObject condition = args.get(0);
        List<LispObject> body = args.subList(1, args.size());
        while (new Bool(condition.eval(scope)).isTrue()) {
            for (LispObject expression : body) {
                expression.eval(scope);
            }
        }
        return new Bool(false); // FIXME: change return value?
    }

    @Override
    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.shouldSize(size -> size >= 1)
                 .ifNotThenThrow(
                         WrongNumberOfArgsException.atLeast(1)
                                                   .is(validator.getArgsSize()));
    }
}
