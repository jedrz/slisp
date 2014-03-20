package com.jedrzejewski.slisp.lexer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LexerTest {

    @Test
    public void testNextToken() {
        Lexer lexer = new Lexer("(fn (zero? 0) (+ 2 3))");
        List<Token> tokens = new LinkedList<Token>();
        Token token;
        while ((token = lexer.getNextToken()) != null) {
            tokens.add(token);
        }
        List<Token> expected = Arrays.asList(
                new OpenBracket(),
                new Symbol("fn"),
                new OpenBracket(),
                new Symbol("zero?"),
                new Number(0),
                new CloseBracket(),
                new OpenBracket(),
                new Symbol("+"),
                new Number(2),
                new Number(3),
                new CloseBracket(),
                new CloseBracket());
        org.junit.Assert.assertEquals(tokens, expected);
    }
}
