public interface SpecialForm extends LispObject {
    LispObject call(List<LispObject> args, Interpreter.Evaluator evaluator);
}
