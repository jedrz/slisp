public abstract class Primitive extends Callable {

    public abstract LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException;

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        List<LispObject> evaluatedArgs = new LinkedList<>();
        for (LispObject o : args) {
            evaluatedArgs.add(o.eval(scope));
        }
        return callWithEvaluatedArgs(evaluatedArgs, scope);
    }

    ...
}
