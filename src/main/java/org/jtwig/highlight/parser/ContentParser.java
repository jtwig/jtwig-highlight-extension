package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;
import org.parboiled.annotations.Label;

public class ContentParser extends BasicParser {
    public ContentParser(ParserContext context) {
        super(ContentParser.class, context);
    }

    @Override
    @Label("Content")
    protected Rule parse() {
        StartCommentParser startCommentParser = getParserContext().parsers().get(StartCommentParser.class);
        StartOutputParser startOutputParser = getParserContext().parsers().get(StartOutputParser.class);
        StartCodeParser startCodeParser = getParserContext().parsers().get(StartCodeParser.class);
        return FirstOf(
                Sequence(
                        OneOrMore(
                                TestNot(
                                        FirstOf(
                                                startCommentParser.parse(),
                                                startOutputParser.parse(),
                                                startCodeParser.parse()
                                        )
                                ),
                                ANY
                        ),
                        push(getParserContext().formatter().content(match()))
                ),
                Sequence(
                        TestNot(EOI),
                        push("")
                )
        );
    }
}
