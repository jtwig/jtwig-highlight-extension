package org.jtwig.highlight.parser;

import org.jtwig.highlight.config.DefaultHighlightConfiguration;
import org.jtwig.highlight.parser.context.ParserContext;
import org.jtwig.highlight.parser.context.ParserContextFactory;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.support.ParsingResult;

public class HighlightParser {
    private static final ParserContextFactory PARSER_CONTEXT_FACTORY = new ParserContextFactory();

    public static HighlightParser defaultParser () {
        return new HighlightParser(PARSER_CONTEXT_FACTORY.create(new DefaultHighlightConfiguration()));
    }

    private final ParserContext parserContext;

    public HighlightParser(ParserContext parserContext) {
        this.parserContext = parserContext;
    }

    public String parse (String content) {
        BasicParseRunner<String> runner = new BasicParseRunner<String>(
                parserContext
                        .parsers()
                        .get(MainParser.class)
                        .parse()
        );

        ParsingResult<String> result = runner.run(content);

        return result.resultValue;
    }
}
