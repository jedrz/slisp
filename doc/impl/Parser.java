public class Parser {
    public Parser(Lexer lexer);
    public LispObject parse();
    private LispObject parseQuote(Token token);
    private LispObject parseOpenParen(Token token);
    private LispObject parseCloseParen(Token token);
    private LispObject parseOpenBracket(Token token);
    private LispObject parseCloseBracket(Token token);
    private LispObject parseSymbol(Token token);
    private LispObject parseNumber(Token token);
    private LispObject parseString(Token token);
}
