public class Macro extends Function {

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Scope wrapperScope = buildScopeWithArgsDontEval(args, scope);
        LispObject expanded = getBody().eval(wrapperScope);
        return expanded.eval(scope);
    }
}
