package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;

public class StartCommentParser extends BasicParser {
    public StartCommentParser(ParserContext context) {
        super(StartCommentParser.class, context);
    }

    public Rule parse () {
        return FirstOf(
                Sequence(
                        Sequence(getParserContext().configuration().getStartComment(), "-"),
                        push(getParserContext().formatter().startComment(match()))
                ),
                Sequence(
                        getParserContext().configuration().getStartComment(),
                        push(getParserContext().formatter().startComment(match()))
                )
        );
    }
}
