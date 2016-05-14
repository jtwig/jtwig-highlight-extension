package org.jtwig.highlight.config;

import org.jtwig.highlight.format.HtmlFormatter;
import org.jtwig.highlight.parser.factory.BasicParserRunnerFactory;
import org.jtwig.highlight.parser.operators.Operator;

import static java.util.Arrays.asList;

public class DefaultHighlightConfiguration extends HighlightConfiguration {
    public DefaultHighlightConfiguration() {
        super(new HtmlFormatter(), new DefaultSyntaxConfiguration(), asList(
                new Operator(false, "-"),
                new Operator(true, "not")
        ), asList(
                new Operator(false, ">="),
                new Operator(false, ">"),
                new Operator(false, "<="),
                new Operator(false, "<"),
                new Operator(true, "and"),
                new Operator(true, "or"),
                new Operator(false, "=="),
                new Operator(false, "-"),
                new Operator(false, "**"),
                new Operator(false, "*"),
                new Operator(false, "//"),
                new Operator(false, "/"),
                new Operator(false, "+"),
                new Operator(false, "~"),
                new Operator(false, ".."),
                new Operator(false, "."),
                new Operator(false, "|")
        ), new BasicParserRunnerFactory());
    }
}
