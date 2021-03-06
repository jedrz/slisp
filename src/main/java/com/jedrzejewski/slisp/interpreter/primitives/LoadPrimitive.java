package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.ArgsValidator;
import com.jedrzejewski.slisp.interpreter.Scope;
import com.jedrzejewski.slisp.interpreter.exceptions.ArgShouldBeStringException;
import com.jedrzejewski.slisp.interpreter.exceptions.InterpreterException;
import com.jedrzejewski.slisp.interpreter.exceptions.WrongNumberOfArgsException;
import com.jedrzejewski.slisp.lexer.Lexer;
import com.jedrzejewski.slisp.lispobjects.Bool;
import com.jedrzejewski.slisp.lispobjects.LispObject;
import com.jedrzejewski.slisp.lispobjects.Str;
import com.jedrzejewski.slisp.parser.Parser;
import java.io.FileReader;
import java.util.List;

public class LoadPrimitive extends Primitive {

    @Override
    public LispObject callWithEvaluatedArgs(List<LispObject> args, Scope scope)
            throws InterpreterException {
        Str arg = (Str) args.get(0);
        String path = arg.getString();
        try {
            FileReader fileReader = new FileReader(path);
            Lexer lexer = new Lexer(fileReader);
            Parser parser = new Parser(lexer);
            LispObject code;
            while ((code = parser.parse()) != null) {
                code.eval(scope);
            }
            return new Bool(true);
        } catch (Exception e) {
            return new Bool(false);
        }
    }

    @Override
    public void validate(ArgsValidator validator) throws InterpreterException {
        validator.shouldSize(size -> size == 1)
                 .ifNotThenThrow(
                         WrongNumberOfArgsException.exactly(1)
                                                   .is(validator.getArgsSize()));

        validator.eachShould(arg -> arg instanceof Str)
                 .ifNotThenThrow(ArgShouldBeStringException.class);
    }
}
