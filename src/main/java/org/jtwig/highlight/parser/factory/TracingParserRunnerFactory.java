package org.jtwig.highlight.parser.factory;

import org.jtwig.highlight.parser.MainParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.parserunners.ParseRunner;
import org.parboiled.parserunners.TracingParseRunner;

public class TracingParserRunnerFactory implements ParserRunnerFactory {
    @Override
    public ParseRunner<String> create(ParserContext parserContext) {
        return new TracingParseRunner<String>(
                parserContext
                        .parsers()
                        .get(MainParser.class)
                        .parse()
        );
    }
}
