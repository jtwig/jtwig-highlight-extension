package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;

public class EndOutputParser extends BasicParser {
    public EndOutputParser(ParserContext context) {
        super(EndOutputParser.class, context);
    }

    public Rule parse () {
        return FirstOf(
                Sequence(
                        Sequence("-", getParserContext().configuration().getEndOutput()),
                        push(getParserContext().formatter().endOutput(match()))
                ),
                Sequence(
                        getParserContext().configuration().getEndOutput(),
                        push(getParserContext().formatter().endOutput(match()))
                )
        );
    }
}
