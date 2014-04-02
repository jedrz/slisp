public class IfForm implements SpecialForm {

    @Override
    public LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator) {
        Bool condition = new Bool(evaluator.eval(args.get(0)));
        if (condition.isTrue()) {
            return evaluator.eval(args.get(1));
        } else {
            Lst doForm = new Lst();
            doForm.add(new Sym("do"));
            doForm.addAll(args.subList(2, args.size()));
            return evaluator.eval(doForm);
        }
    }
}
