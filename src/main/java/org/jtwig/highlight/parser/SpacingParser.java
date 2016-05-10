package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;

public class SpacingParser extends BasicParser {
    public SpacingParser(ParserContext context) {
        super(SpacingParser.class, context);
    }

    @Override
    protected Rule parse() {
        return FirstOf(
                Sequence(
                        OneOrMore(
                                AnyOf(
                                        " \n\t"
                                )
                        ),
                        push(getParserContext().formatter().space(match()))
                ),
                push("")
        );
    }
}
