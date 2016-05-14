package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;

public class IdentifierParser extends BasicParser {

    public IdentifierParser(ParserContext context) {
        super(IdentifierParser.class, context);
    }

    @Override
    protected Rule parse() {
        return Sequence(
                FirstOf(
                        CharRange('A', 'Z'),
                        CharRange('a', 'z'),
                        String('_')
                ),
                ZeroOrMore(
                        FirstOf(
                                CharRange('A', 'Z'),
                                CharRange('a', 'z'),
                                String('_'),
                                CharRange('0', '9')
                        )
                )
        );
    }

}
