package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;
import org.parboiled.annotations.Label;

public class EndCommentParser extends BasicParser {
    public EndCommentParser(ParserContext context) {
        super(EndCommentParser.class, context);
    }

    @Label("End Comment")
    public Rule parse () {
        return FirstOf(
                Sequence(
                        Sequence("-", getParserContext().configuration().getEndComment()),
                        push(getParserContext().formatter().endComment(match()))
                ),
                Sequence(
                        getParserContext().configuration().getEndComment(),
                        push(getParserContext().formatter().endComment(match()))
                )
        );
    }
}
