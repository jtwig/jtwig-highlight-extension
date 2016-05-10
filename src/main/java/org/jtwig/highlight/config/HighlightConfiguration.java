package org.jtwig.highlight.config;

import org.jtwig.highlight.format.Formatter;
import org.jtwig.highlight.parser.operators.Operator;

import java.util.List;

public class HighlightConfiguration {
    private final Formatter formatter;
    private final SyntaxConfiguration syntaxConfiguration;
    private final List<Operator> unaryOperators;
    private final List<Operator> binaryOperators;

    public HighlightConfiguration(Formatter formatter, SyntaxConfiguration syntaxConfiguration, List<Operator> unaryOperators, List<Operator> binaryOperators) {
        this.formatter = formatter;
        this.syntaxConfiguration = syntaxConfiguration;
        this.unaryOperators = unaryOperators;
        this.binaryOperators = binaryOperators;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public SyntaxConfiguration getSyntaxConfiguration() {
        return syntaxConfiguration;
    }

    public List<Operator> getUnaryOperators() {
        return unaryOperators;
    }

    public List<Operator> getBinaryOperators() {
        return binaryOperators;
    }
}
