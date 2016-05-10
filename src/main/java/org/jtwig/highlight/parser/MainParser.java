package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;

public class MainParser extends BasicParser {
    public MainParser(ParserContext context) {
        super(MainParser.class, context);
    }

    @Override
    protected Rule parse() {
        TagParser tagParser = getParserContext().parsers().get(TagParser.class);
        ContentParser contentParser = getParserContext().parsers().get(ContentParser.class);

        return FirstOf(
                EOI,
                Sequence(
                        FirstOf(
                                tagParser.parse(),
                                contentParser.parse()
                        ),
                        ZeroOrMore(
                                FirstOf(
                                        tagParser.parse(),
                                        contentParser.parse()
                                ),
                                push(mergeSince(1))
                        )
                )
        );
    }
}
