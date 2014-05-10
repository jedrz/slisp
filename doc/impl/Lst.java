public class Lst ... implements LispObject {

    @Override
    public LispObject eval(Scope scope) throws InterpreterException {
        LispObject first = get(0);
        List<LispObject> rest = subList(1, size());
        LispObject firstEvaled =  first.eval(scope);
        if (!(firstEvaled instanceof Callable)) {
            throw new ArgShouldBeCallableException();
        }
        Callable callable = (Callable) firstEvaled;
        callable.validate(new ArgsValidator(rest), scope);
        return callable.call(rest, scope);
    }
}
