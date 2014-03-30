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
                Token.createStringDelimiterToken(),
                Token.createSymbolToken("str"),
                Token.createStringDelimiterToken(),
                Token.createCloseBracketToken(),
                Token.createQuoteToken(),
                Token.createOpenParenToken(),
                Token.createCloseParenToken());
        Assert.assertEquals(expected, tokens);
    }

    @Test
    public void testQuote() {
        Assert.assertEquals(
                Token.createQuoteToken(),
                getFirstToken("'")
        );
    }

    @Test
    public void testQuasiQuote() {
        Assert.assertEquals(
                Token.createQuasiquoteToken(),
                getFirstToken("`")
        );
    }

    @Test
    public void testUnquote() {
        Assert.assertEquals(
                Token.createUnquoteToken(),
                getFirstToken("~")
        );
    }

    @Test
    public void testUnquoteSplicing() {
        Assert.assertEquals(
                Token.createUnquoteSplicingToken(),
                getFirstToken("~@")
        );
    }

    private Token getFirstToken(String in) {
        Lexer lexer = new Lexer(in);
        return lexer.getNextToken();
    }
}
