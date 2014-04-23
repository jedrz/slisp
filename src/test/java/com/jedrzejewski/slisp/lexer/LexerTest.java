package com.jedrzejewski.slisp.lexer;

import com.jedrzejewski.slisp.lexer.exceptions.DoubleDotException;
import com.jedrzejewski.slisp.lexer.exceptions.LexerException;
import com.jedrzejewski.slisp.lexer.exceptions.MissingEscapeCharacterException;
import com.jedrzejewski.slisp.lexer.exceptions.StringEndingCharacterNotFoundException;
import com.jedrzejewski.slisp.lexer.exceptions.UnknownTokenException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class LexerTest {

    @Test
    public void testNextToken() throws LexerException {
        Lexer lexer = new Lexer("(fn (zero? 0) (+ 2 3)) [sym] '()");
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
                Token.createSymbolToken("sym"),
                Token.createCloseBracketToken(),
                Token.createQuoteToken(),
                Token.createOpenParenToken(),
                Token.createCloseParenToken());
        Assert.assertEquals(expected, tokens);
    }

    @Test
    public void testQuote() throws LexerException {
        Assert.assertEquals(
                Token.createQuoteToken(),
                getFirstToken("'")
        );
    }

    @Test
    public void testValidNumbers() throws Exception {
        Assert.assertEquals(
                Token.createNumberToken("1.1"),
                getFirstToken("1.1")
        );
        Assert.assertEquals(
                Token.createNumberToken(".1"),
                getFirstToken(".1")
        );
        Assert.assertEquals(
                Token.createNumberToken("1."),
                getFirstToken("1.")
        );
        Assert.assertEquals(
                Token.createNumberToken("00."),
                getFirstToken("00.")
        );
    }

    @Test(expected = UnknownTokenException.class)
    public void testNonValidNumbers() throws Exception {
        getFirstToken(".");
    }

    @Test(expected = DoubleDotException.class)
    public void testDoubleDotInNumber() throws Exception {
        getFirstToken("1.0.1");
    }

    @Test
    public void testString() throws Exception {
        Assert.assertEquals(
                Token.createStringToken("escape \" char\t"),
                getFirstToken("\"escape \\\" char\\t\"")
        );
    }

    @Test
    public void testInternalStringofString() throws Exception {
        Assert.assertEquals(
                "a\nb",
                getFirstToken("\"a\nb\"").getString()
        );
        Assert.assertEquals(
                "a\nb",
                getFirstToken("\"a\\nb\"").getString()
        );
        Assert.assertEquals(
                "a\\b",
                getFirstToken("\"a\\\\b\"").getString()
        );
    }

    @Test(expected = StringEndingCharacterNotFoundException.class)
    public void testNotEndedString() throws Exception {
        getFirstToken("\"not ended...");
    }

    @Test(expected = MissingEscapeCharacterException.class)
    public void testMissingEscapeCharacter() throws Exception {
        getFirstToken("\"s\\s\"");
    }

    @Test(expected = UnknownTokenException.class)
    public void unknownToken() throws LexerException {
        getFirstToken("`");
    }

    private Token getFirstToken(String in) throws LexerException {
        Lexer lexer = new Lexer(in);
        return lexer.getNextToken();
    }
}
