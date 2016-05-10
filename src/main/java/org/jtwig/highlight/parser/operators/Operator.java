package org.jtwig.highlight.parser.operators;

public class Operator {
    private final boolean identifierPattern;
    private final String symbol;

    public Operator(boolean identifierPattern, String symbol) {
        this.identifierPattern = identifierPattern;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isIdentifierPattern() {
        return identifierPattern;
    }
}
