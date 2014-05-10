public class Function implements LispObject {

    private List<Sym> args;
    private LispObject body;
    private Scope scope;

    public Function(List<Sym> args, LispObject body, Scope scope);

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Scope wrapperScope = buildScopeWithArgsAndEval(args, scope);
        return getBody().eval(wrapperScope);
    }

    protected Scope buildScopeWithArgs(List<LispObject> args, Scope scope, boolean eval)
        throws InterpreterException {
        Scope wrapperScope = new Scope(getScope());
        for (int i = 0; i < getArgNames().size(); ++i) {
            Sym argName = getArgNames().get(i);
            // Obsługa &
            LispObject arg = args.get(i);
            wrapperScope.put(argName, arg.eval(scope));
        }
        return wrapperScope;
    }
}
