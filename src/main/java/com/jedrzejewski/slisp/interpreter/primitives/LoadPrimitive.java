package com.jedrzejewski.slisp.interpreter.primitives;

import com.jedrzejewski.slisp.interpreter.Scope;
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
        validate(args);

        // TODO: check type.
        Str arg = (Str) args.get(0);
        String path = arg.getString();
        try {
            FileReader fileReader = new FileReader(path);
            Lexer lexer = new Lexer(fileReader);
            Parser parser = new Parser(lexer);
            parser.parse().eval(scope);
            return new Bool(true);
        } catch (Exception e) {
            return new Bool(false);
        }
    }

    public void validate(List<LispObject> args) throws InterpreterException {
        if (args.size() != 1) {
            throw WrongNumberOfArgsException.exactly(1).is(args.size());
        }
    }
}
