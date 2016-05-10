package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;

public class StartOutputParser extends BasicParser {
    public StartOutputParser(ParserContext context) {
        super(StartOutputParser.class, context);
    }

    public Rule parse () {
        return FirstOf(
                Sequence(
                        Sequence(getParserContext().configuration().getStartOutput(), "-"),
                        push(getParserContext().formatter().startOutput(match()))
                ),
                Sequence(
                        getParserContext().configuration().getStartOutput(),
                        push(getParserContext().formatter().startOutput(match()))
                )
        );
    }
}
