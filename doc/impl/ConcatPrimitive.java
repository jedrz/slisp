public class ConcatPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Lst concatResult = new Lst();
        for (LispObject lst : args) {
            concatResult.addAll((Lst) lst);
        }
        return concatResult;
    }

    ...
}
