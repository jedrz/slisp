package com.jedrzejewski.slisp.lispobjects;

import com.jedrzejewski.slisp.interpreter.Scope;
import java.util.List;

public class Function extends Callable {

    private List<Sym> argNames;
    private LispObject body;
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

    @Override
    public LispObject call(List<LispObject> args, Scope scope) {
        Scope wrapperScope = new Scope(getScope());
        for (int i = 0; i < getArgNames().size(); ++i) {
            Sym argName = getArgNames().get(i);
            if (argName.getName().equals("&")) {
                // TODO: there is no symbol after &?
                argName = getArgNames().get(i + 1);
                Lst restArgs = new Lst();
                for (LispObject o : args.subList(i, args.size())) {
                    restArgs.add(o.eval(scope));
                }
                wrapperScope.put(argName, restArgs);
                break;
            }
            LispObject arg = args.get(i);
            wrapperScope.put(argName, arg.eval(scope));
        }
        return getBody().eval(wrapperScope);
    }
}
