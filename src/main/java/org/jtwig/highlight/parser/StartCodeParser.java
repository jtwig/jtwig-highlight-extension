package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;
import org.parboiled.annotations.Label;

public class StartCodeParser extends BasicParser {
    public StartCodeParser(ParserContext context) {
        super(StartCodeParser.class, context);
    }

    @Label("Start code")
    public Rule parse () {
        return FirstOf(
                Sequence(
                        Sequence(getParserContext().configuration().getStartCode(), "-"),
                        push(getParserContext().formatter().startCode(match()))
                ),
                Sequence(
                        getParserContext().configuration().getStartCode(),
                        push(getParserContext().formatter().startCode(match()))
                )
        );
    }
}
