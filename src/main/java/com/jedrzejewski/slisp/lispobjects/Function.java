package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import java.util.List;

public class Function extends Callable {

    /** Argument names */
    private List<Sym> argNames;
    /** The body of the function. */
    private LispObject body;
    /** The scope at which function was defined. */
    private Scope scope;

    public Function(List<Sym> argNames, LispObject body, Scope scope) {
        this.argNames = argNames;
        this.body = body;
        this.scope = scope;
    }

    public Function(List<Sym> argNames, List<LispObject> bodyList, Scope scope) {
        this(argNames, new Sym("") /* FIXME: okropne */, scope);
        Lst wrappedBody = new Lst();
        wrappedBody.add(new Sym("do"));
        wrappedBody.addAll(bodyList);
        body = wrappedBody;
    }

    public List<Sym> getArgNames() {
        return argNames;
    }

    public LispObject getBody() {
        return body;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return "fn";
    }

    /**
     * Evaluates the function body with scope consisting of evaluated
     * arguments bound to the function arguments.
     * @param args unevaluated args
     * @param scope current scope
     * @return the result of function call
     * @throws InterpreterException
     */
    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Scope wrapperScope = buildScopeWithArgsAndEval(args, scope);
        return getBody().eval(wrapperScope);
    }

    @Override
    public void validate(ArgsValidator validator) throws InterpreterException {
        // Arg names list contains & symbol.
        if (getArgNames().contains(new Sym("&"))) {
            validator.shouldSize(size -> size >= getArgNames().size() - 2)
                     .ifNotThenThrow(
                             WrongNumberOfArgsException.atLeast(getArgNames().size() - 2)
                                                       .is(validator.getArgsSize())
                     );
        } else {
            // No & symbol.
            validator.shouldSize(size -> size == getArgNames().size())
                     .ifNotThenThrow(
                             WrongNumberOfArgsException.exactly(getArgNames().size())
                                                       .is(validator.getArgsSize())
                     );
        }
    }

    public Scope buildScopeWithArgsAndEval(List<LispObject> args, Scope scope)
            throws InterpreterException {
        return buildScopeWithArgs(args, scope, true);
    }

    public Scope buildScopeWithArgsDontEval(List<LispObject> args, Scope scope)
            throws InterpreterException {
        return buildScopeWithArgs(args, scope, false);
    }

    /**
     * Binds arguments to scope extended by the function scope.
     * Arguments are evaluated using passed scope or
     * no if <code>eval</code> is <code>false</code>.
     * To the arg name after ampersand the rest of arguments are passed
     * as list.
     * @param args list of arguments
     * @param scope the scope to use when evaluating arguments
     * @param eval <code>true</code> if args should be evaluated
     * @return scope with binded args
     * @throws InterpreterException
     */
    protected Scope buildScopeWithArgs(List<LispObject> args, Scope scope, boolean eval)
        throws InterpreterException {
        Scope wrapperScope = new Scope(getScope());
        for (int i = 0; i < getArgNames().size(); ++i) {
            Sym argName = getArgNames().get(i);
            if (argName.getName().equals("&")) {
                argName = getArgNames().get(i + 1);
                Lst restArgs = new Lst();
                for (LispObject o : args.subList(i, args.size())) {
                    LispObject result;
                    if (eval) {
                        result = o.eval(scope);
                    } else {
                        result = o;
                    }
                    restArgs.add(result);
                }
                wrapperScope.put(argName, restArgs);
                break;
            }
            LispObject arg = args.get(i);
            LispObject value = eval ? arg.eval(scope) : arg;
            wrapperScope.put(argName, value);
        }
        return wrapperScope;
    }
}
