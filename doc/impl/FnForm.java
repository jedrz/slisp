public class FnForm extends SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Scope scope)
            throws InterpreterException {
        // Obsługa parametrów.
        List<Sym> argList = new LinkedList<>();
        Vec argVec = (Vec) args.get(0);
        for (LispObject arg : argVec) {
            argList.add((Sym) arg);
        }

        // Ciało funkcji.
        List<LispObject> body = args.subList(1, args.size());
        return new Function(argList, body, scope);
    }

    ...
}
