package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;
import org.parboiled.annotations.Label;

public class EndOutputParser extends BasicParser {
    public EndOutputParser(ParserContext context) {
        super(EndOutputParser.class, context);
    }

    @Label("End Output")
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
