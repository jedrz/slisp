public class FnForm implements SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator) {
        // Obsługa parametrów.
        List<Sym> argList = new LinkedList<>();
        if (args.get(0) instanceof Vec) {
            Vec argVec = (Vec) args.get(0);
            for (LispObject arg : argVec) {
                if (arg instanceof Sym) {
                    argList.add((Sym) arg);
                } else {
                    // Nazwy argumentów powinny być symbolami.
                }
            }
        } else {
            // Pierwszym argumentem powinien być wektor.
        }

        List<LispObject> body = args.subList(1, args.size());
        // Ciało funkcji.
        return new Function(argList, body, evaluator.getScope());
    }
}
