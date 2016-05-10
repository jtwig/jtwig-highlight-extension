package org.jtwig.highlight.parser.context;

import org.jtwig.highlight.config.SyntaxConfiguration;
import org.jtwig.highlight.format.Formatter;
import org.jtwig.highlight.parser.operators.Operator;

import java.util.List;

public class ParserContext {
    private final Parsers parsers;
    private final SyntaxConfiguration syntaxConfiguration;
    private final Formatter formatter;
    private final List<Operator> unaryOperators;
    private final List<Operator> binaryOperators;

    public ParserContext(Parsers parsers, SyntaxConfiguration syntaxConfiguration, Formatter formatter, List<Operator> unaryOperators, List<Operator> binaryOperators) {
        this.parsers = parsers;
        this.syntaxConfiguration = syntaxConfiguration;
        this.formatter = formatter;
        this.unaryOperators = unaryOperators;
        this.binaryOperators = binaryOperators;
    }

    public Parsers parsers() {
        return parsers;
    }

    public SyntaxConfiguration configuration() {
        return syntaxConfiguration;
    }

    public Formatter formatter() {
        return formatter;
    }

    public List<Operator> unaryOperators() {
        return unaryOperators;
    }

    public List<Operator> binaryOperators() {
        return binaryOperators;
    }
}
