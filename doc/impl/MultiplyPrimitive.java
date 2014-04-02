public class MultiplyPrimitive extends MathOperationPrimitive {

    @Override
    public LispObject call(List<LispObject> args) {
        double result = convertToDoubleStream(args)
                    .reduce((a, b) -> (a * b))
                    .getAsDouble();
        return new Num(result);
    }
}
