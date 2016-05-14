package org.jtwig.highlight.parser.factory;

import org.jtwig.highlight.parser.MainParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.parserunners.ParseRunner;

public class BasicParserRunnerFactory implements ParserRunnerFactory {
    @Override
    public ParseRunner<String> create(ParserContext parserContext) {
        return new BasicParseRunner<String>(
                parserContext
                        .parsers()
                        .get(MainParser.class)
                        .parse()
        );
    }
}
