package com.jedrzejewski.slisp.lexer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class LexerTest {

    @Test
    public void testNextToken() {
        Lexer lexer = new Lexer("(fn (zero? 0) (+ 2 3)) [\"str\"] '()");
        List<Token> tokens = new LinkedList<>();
        Token token;
        while ((token = lexer.getNextToken()) != null) {
            tokens.add(token);
        }
        List<Token> expected = Arrays.asList(
                Token.createOpenParenToken(),
                Token.createSymbolToken("fn"),
                Token.createOpenParenToken(),
                Token.createSymbolToken("zero?"),
                Token.createNumberToken("0"),
                Token.createCloseParenToken(),
                Token.createOpenParenToken(),
                Token.createSymbolToken("+"),
                Token.createNumberToken("2"),
                Token.createNumberToken("3"),
                Token.createCloseParenToken(),
                Token.createCloseParenToken(),
                Token.createOpenBracketToken(),
                Token.createStringDelimeterToken(),
                Token.createSymbolToken("str"),
                Token.createStringDelimeterToken(),
                Token.createCloseBracketToken(),
                Token.createQuoteToken(),
                Token.createOpenParenToken(),
                Token.createCloseParenToken());
        Assert.assertEquals(expected, tokens);
    }
}
