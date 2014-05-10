public class IfForm extends SpecialForm {

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

    ...
}
