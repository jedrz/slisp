package com.jedrzejewski.slisp.interpreter.exceptions;

public class WrongNumberOfArgsException extends ExactFunctionException {

    public static WrongNumberOfArgsBuilder atLeast(int should) {
        return WrongNumberOfArgsBuilder.atLeast(should);
    }

    public static WrongNumberOfArgsBuilder exactly(int should) {
        return WrongNumberOfArgsBuilder.exactly(should);
    }

    public static class WrongNumberOfArgsBuilder {
        protected Integer atLeast = null;
        protected Integer exactly = null;
        protected Integer is = null;

        // FIXME: code duplication

        public static WrongNumberOfArgsBuilder atLeast(int should) {
            WrongNumberOfArgsBuilder builder = new WrongNumberOfArgsBuilder();
            builder.atLeast = should;
            return builder;
        }

        public static WrongNumberOfArgsBuilder exactly(int should) {
            WrongNumberOfArgsBuilder builder = new WrongNumberOfArgsBuilder();
            builder.exactly = should;
            return builder;
        }

        public WrongNumberOfArgsException is(int is) {
            this.is = is;

            String beginning = null;
            if (atLeast != null) {
                beginning = "should take at least " + atLeast;
            } else if (exactly != null) {
                beginning = "should take exactly " + exactly;
            }

            return new WrongNumberOfArgsException(beginning + " but is " + is);
        }
    }

    protected WrongNumberOfArgsException(String explanation) {
        super(buildMessage(WrongNumberOfArgsException.class) + ": " + explanation);
    }
}
