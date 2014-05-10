package com.jedrzejewski.slisp.interpreter.specialforms;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Lst;
import com.jedrzejewski.slisp.lispobjects.Sym;
import java.util.List;

public class IfForm extends SpecialForm {

    /**
     * Evaluates the first argument. If the result is true, then evaluates and
     * returns the second one. Otherwise evalutes the rest ones from the third
     * and returns the result of evaluating the last form.
     * @param args unevaluated args
     * @param scope current scope
     * @throws InterpreterException
     */
    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Bool condition = new Bool(args.get(0).eval(scope));
        if (condition.isTrue()) {
            return args.get(1).eval(scope);
        } else {
            Lst doForm = new Lst();
            doForm.add(new Sym("do"));
            doForm.addAll(args.subList(2, args.size()));
            return doForm.eval(scope);
        }
    }

    @Override
    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.shouldSize(size -> size >= 3)
                 .ifNotThenThrow(
                         WrongNumberOfArgsException.atLeast(3)
                                                   .is(validator.getArgsSize()));
    }
}
