package org.jtwig.highlight.config;

import org.apache.commons.lang3.builder.Builder;
import org.jtwig.highlight.format.Formatter;
import org.jtwig.highlight.parser.factory.ParserRunnerFactory;
import org.jtwig.highlight.parser.operators.Operator;
import org.jtwig.util.builder.ListBuilder;

public class HighlightConfigurationBuilder implements Builder<HighlightConfiguration> {
    public static HighlightConfigurationBuilder highlightConfiguration () {
        return new HighlightConfigurationBuilder(new DefaultHighlightConfiguration());
    }

    private Formatter formatter;
    private SyntaxConfiguration syntaxConfiguration;
    private ListBuilder<HighlightConfigurationBuilder, Operator> unaryOperators;
    private ListBuilder<HighlightConfigurationBuilder, Operator> binaryOperators;
    private ParserRunnerFactory factory;

    public HighlightConfigurationBuilder() {
        unaryOperators = new ListBuilder<HighlightConfigurationBuilder, Operator>(this);
        binaryOperators = new ListBuilder<HighlightConfigurationBuilder, Operator>(this);
    }

    public HighlightConfigurationBuilder (HighlightConfiguration prototype) {
        this.formatter = prototype.getFormatter();
        this.syntaxConfiguration = prototype.getSyntaxConfiguration();
        this.factory = prototype.getFactory();
        this.unaryOperators = new ListBuilder<HighlightConfigurationBuilder, Operator>(this, prototype.getUnaryOperators());
        this.binaryOperators = new ListBuilder<HighlightConfigurationBuilder, Operator>(this, prototype.getBinaryOperators());
    }

    public HighlightConfigurationBuilder withFormatter(Formatter formatter) {
        this.formatter = formatter;
        return this;
    }

    public HighlightConfigurationBuilder withSyntaxConfiguration(SyntaxConfiguration syntaxConfiguration) {
        this.syntaxConfiguration = syntaxConfiguration;
        return this;
    }

    public HighlightConfigurationBuilder withFactory(ParserRunnerFactory factory) {
        this.factory = factory;
        return this;
    }

    public ListBuilder<HighlightConfigurationBuilder, Operator> unaryOperators() {
        return unaryOperators;
    }

    public ListBuilder<HighlightConfigurationBuilder, Operator> binaryOperators() {
        return binaryOperators;
    }

    @Override
    public HighlightConfiguration build() {
        return new HighlightConfiguration(
                formatter, syntaxConfiguration,
                unaryOperators.build(),
                binaryOperators.build(),
                factory
        );
    }
}
