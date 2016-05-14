package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;
import org.parboiled.annotations.Label;

public class EndCodeParser extends BasicParser {
    public EndCodeParser(ParserContext context) {
        super(EndCodeParser.class, context);
    }

    @Label("End Code")
    public Rule parse () {
        return FirstOf(
                Sequence(
                        Sequence("-", getParserContext().configuration().getEndCode()),
                        push(getParserContext().formatter().endCode(match()))
                ),
                Sequence(
                        getParserContext().configuration().getEndCode(),
                        push(getParserContext().formatter().endCode(match()))
                )
        );
    }
}
