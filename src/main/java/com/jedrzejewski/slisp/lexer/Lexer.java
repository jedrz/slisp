package com.jedrzejewski.slisp.lexer;

import com.jedrzejewski.slisp.lexer.exceptions.DoubleDotException;
import com.jedrzejewski.slisp.lexer.exceptions.LexerException;
import com.jedrzejewski.slisp.lexer.exceptions.MissingEscapeCharacterException;
import com.jedrzejewski.slisp.lexer.exceptions.StringEndingCharacterNotFoundException;
import com.jedrzejewski.slisp.lexer.exceptions.UnknownTokenException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Lexer {

    private Reader reader;
    private boolean isPreviousCh = false;
    private int previousCh = -1;

    public Lexer(Reader reader) {
        this.reader = reader;
    }

    public Lexer(String in) {
        this(new StringReader(in));
    }

    public Token getNextToken() throws LexerException {
        skipWhitespace();

        int c = getNextCh();

        if (isCommentCh(c)) {
            ignoreComments();
            c = getNextCh();
        }

        switch (c) {
            case '(':
                return Token.createOpenParenToken();
            case ')':
                return Token.createCloseParenToken();
            case '[':
                return Token.createOpenBracketToken();
            case ']':
                return Token.createCloseBracketToken();
            case '\'':
                return Token.createQuoteToken();
        }

        if (isStringCh(c)) {
            return getString(c);
        }

        if (isNumberCh(c)) {
            return getNumber(c);
        }

        if (isSymbolCh(c)) {
            return getSymbol(c);
        }

        if (c != -1) {
            throw new UnknownTokenException();
        }
        return null;
    }

    private Token getSymbol(int c) {
        String symbol = Character.toString((char) c);
        while ((c = getNextCh()) != -1 && isSymbolCh(c)) {
            symbol += Character.toString((char) c);
        }
        ungetCh(c);
        return Token.createSymbolToken(symbol);
    }

    private boolean isSymbolCh(int c) {
        return Character.isLetterOrDigit(c)
                || c == '?' || c == '!' || c == '&'
                || c == '+' || c == '-' || c == '*' || c == '/' || c == '%'
                || c == '=' || c == '>' || c == '<';
    }

    private Token getNumber(int c) throws LexerException {
        boolean wasDot = false;
        String number = Character.toString((char) c);
        while ((c = getNextCh()) != -1) {
            if (c == '.' && wasDot) {
                throw new DoubleDotException();
            }
            if (isNumberCh(c) || (!wasDot && c == '.')) {
                number += Character.toString((char) c);
                if (c == '.') {
                    wasDot = true;
                }
            } else {
                break;
            }
        }
        ungetCh(c);
        if (number.equals(".")) {
            throw new UnknownTokenException();
        }
        return Token.createNumberToken(number);
    }

    private boolean isNumberCh(int c) {
        return Character.isDigit(c) || c == '.';
    }

    private Token getString(int c) throws LexerException {
        String str = "";
        while ((c = getNextCh()) != -1) {
            if (c == '"') { // Closing double quote
                return Token.createStringToken(str);
            }
            if (c == '\\') { // Escape next character.
                int nextC = getNextCh();
                // We have to escape every character manually since printing
                // \n str actually means to print \\n string.
                switch (nextC) {
                    case 't':
                        str += '\t';
                        break;
                    case 'n':
                        str += '\n';
                        break;
                    case 'r':
                        str += '\r';
                        break;
                    case '\\':
                        str += '\\';
                        break;
                    case '"':
                        str += "\"";
                        break;
                    default:
                        throw new MissingEscapeCharacterException();
                }
            } else {
                str += Character.toString((char) c);
            }
        }
        throw new StringEndingCharacterNotFoundException();
    }

    private boolean isStringCh(int c) {
        return c == '"';
    }

    private boolean isCommentCh(int c) {
        return c == ';';
    }

    private void ignoreComments() {
        int c;
        while ((c = getNextCh()) != -1 && c != '\n') {
        }
    }

    private int getNextCh() {
        if (isPreviousCh) {
            isPreviousCh = false;
            return previousCh;
        }
        try {
            int c;
            if ((c = reader.read()) != -1) {
                return c;
            } else {
                reader.close();
            }
        } catch (IOException e) {
        }
        return -1;
    }

    private void ungetCh(int c) {
        previousCh = c;
        isPreviousCh = true;
    }

    private void skipWhitespace() {
        int c;
        while ((c = getNextCh()) != -1 && Character.isWhitespace(c)) {
        }
        ungetCh(c);
    }
}
