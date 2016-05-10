package org.jtwig.highlight.parser.base;

import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.BaseParser;
import org.parboiled.Rule;

public abstract class BasicParser extends BaseParser<String> {
    final ParserContext parserContext;

    public BasicParser (Class<? extends BasicParser> type, ParserContext context) {
        this.parserContext = context;
        this.parserContext.parsers().register(type, this);
    }

    protected abstract Rule parse();

    public ParserContext getParserContext() {
        return parserContext;
    }

    public String mergeSince (int position) {
        StringBuilder builder = new StringBuilder();
        for (int i = position; i >= 0; i--) {
            builder.append(pop(i));
        }
        return builder.toString();
    }
}

