private LispObject evalLst(LispObject code, Scope scope) {
    ...
    if (fn instanceof Function) {
        Function function = (Function) fn;
        List<LispObject> args = new LinkedList<>();
        for (LispObject object : lst.subList(1, lst.size())) {
            args.add(eval(object, scope));
        }
        Scope wrapperScope = new Scope(function.getScope());
        List<Sym> argNames = function.getArgs();
        for (int i = 0; i < args.size(); ++i) {
            wrapperScope.put(argNames.get(i), args.get(i));
        }
        return eval(function.getBody(), wrapperScope);
    }
}
