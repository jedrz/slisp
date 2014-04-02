private LispObject evalLst(LispObject code, Scope scope) {
    if (fn instanceof SpecialForm) {
        SpecialForm specialForm = (SpecialForm) fn;
        List<LispObject> args = lst.subList(1, lst.size());
        Evaluator evaluator = this.new Evaluator(scope);
        return specialForm.call(args, evaluator);
    }
}
