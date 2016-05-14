package org.jtwig.highlight.parser;

import org.jtwig.highlight.config.DefaultHighlightConfiguration;
import org.jtwig.highlight.parser.context.ParserContext;
import org.jtwig.highlight.parser.context.ParserContextFactory;
import org.jtwig.highlight.parser.factory.BasicParserRunnerFactory;
import org.jtwig.highlight.parser.factory.ParserRunnerFactory;
import org.jtwig.highlight.parser.factory.TracingParserRunnerFactory;
import org.parboiled.parserunners.ParseRunner;
import org.parboiled.support.ParsingResult;

public class HighlightParser {
    private static final ParserContextFactory PARSER_CONTEXT_FACTORY = new ParserContextFactory();

    public static HighlightParser defaultParser () {
        return new HighlightParser(PARSER_CONTEXT_FACTORY.create(new DefaultHighlightConfiguration()), new BasicParserRunnerFactory());
    }

    public static HighlightParser tracingParser () {
        return new HighlightParser(PARSER_CONTEXT_FACTORY.create(new DefaultHighlightConfiguration()), new TracingParserRunnerFactory());
    }

    private final ParserContext parserContext;
    private final ParserRunnerFactory factory;

    public HighlightParser(ParserContext parserContext, ParserRunnerFactory factory) {
        this.parserContext = parserContext;
        this.factory = factory;
    }

    public String parse (String content) {
        ParseRunner<String> runner = factory.create(parserContext);
        ParsingResult<String> result = runner.run(content);
        return result.resultValue;
    }
}
