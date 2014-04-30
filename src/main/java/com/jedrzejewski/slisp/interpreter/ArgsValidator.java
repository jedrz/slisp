package com.jedrzejewski.slisp.interpreter;

import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import java.util.List;
import java.util.function.Predicate;

public class ArgsValidator {

    protected List<LispObject> args;

    public ArgsValidator(List<LispObject> args) {
        this.args = args;
    }

    public Thrower eachShould(ArgPredicate p) {
        return new Thrower(args.stream().allMatch(p));
    }

    public Thrower shouldAt(int index, ArgPredicate p) {
        return new Thrower(p.test(args.get(index)));
    }

    public Thrower shouldSize(SizePredicate p) {
        return new Thrower(p.test(args.size()));
    }

    public static class Thrower {

        protected boolean predicateResult;

        public Thrower(boolean predicateResult) {
            this.predicateResult = predicateResult;
        }

        public void ifNotThenThrow(Class<? extends InterpreterException> classObj)
                throws InterpreterException {
            // FIXME: empty catch blocks.
            if (!predicateResult) {
                try {
                    throw classObj.newInstance();
                } catch (IllegalAccessException e) {
                } catch (InstantiationException e) {
                }
            }
        }

        public void ifNotThenThrow(InterpreterException e)
                throws InterpreterException {
            if (!predicateResult) {
                throw e;
            }
        }
    }

    public interface ArgPredicate extends Predicate<LispObject> {
        boolean test(LispObject lispObject);
    }

    public interface SizePredicate extends Predicate<Integer> {
        boolean test(Integer size);
    }
}
