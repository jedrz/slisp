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
        List<Token> tokens = new LinkedList<>();
        Token token;
        while ((token = lexer.getNextToken()) != null) {
            tokens.add(token);
        }
        List<Token> expected = Arrays.asList(
                Token.createOpenBracketToken(),
                Token.createSymbolToken("fn"),
                Token.createOpenBracketToken(),
                Token.createSymbolToken("zero?"),
                Token.createNumberToken("0"),
                Token.createCloseBracketToken(),
                Token.createOpenBracketToken(),
                Token.createSymbolToken("+"),
                Token.createNumberToken("2"),
                Token.createNumberToken("3"),
                Token.createCloseBracketToken(),
                Token.createCloseBracketToken());
        org.junit.Assert.assertEquals(expected, tokens);
    }
}