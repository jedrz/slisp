public class Token {

    public enum Type {
        OPEN_PAREN,
        CLOSE_PAREN,
        OPEN_BRACKET,
        CLOSE_BRACKET,
        QUOTE,
        SYMBOL,
        NUMBER,
        STRING,
    }

    public Token(Type type, String string);
    public Type getType();
    public String getString();
}
