private LispObject evalLst(LispObject code, Scope scope) {
    ...
    if (fn instanceof Primitive) {
        Primitive primitive = (Primitive) fn;
        List<LispObject> args = new LinkedList<>();
        for (LispObject object : lst.subList(1, lst.size())) {
            args.add(eval(object, scope));
        }
        return primitive.call(args);
    }
}
