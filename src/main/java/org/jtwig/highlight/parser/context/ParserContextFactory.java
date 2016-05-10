package org.jtwig.highlight.parser.context;

import org.jtwig.highlight.config.HighlightConfiguration;
import org.jtwig.highlight.parser.*;
import org.parboiled.Parboiled;

public class ParserContextFactory {
    public ParserContext create (HighlightConfiguration configuration) {
        Parsers parsers = new Parsers();
        ParserContext parserContext = new ParserContext(parsers, configuration.getSyntaxConfiguration(), configuration.getFormatter(), configuration.getUnaryOperators(), configuration.getBinaryOperators());

        Parboiled.createParser(StartCodeParser.class, parserContext);
        Parboiled.createParser(StartOutputParser.class, parserContext);
        Parboiled.createParser(StartCommentParser.class, parserContext);
        Parboiled.createParser(EndCodeParser.class, parserContext);
        Parboiled.createParser(EndOutputParser.class, parserContext);
        Parboiled.createParser(EndCommentParser.class, parserContext);
        Parboiled.createParser(TagKeywordParser.class, parserContext);

        Parboiled.createParser(SpacingParser.class, parserContext);
        Parboiled.createParser(IdentifierParser.class, parserContext);
        Parboiled.createParser(ExpressionParser.class, parserContext);
        Parboiled.createParser(TagParser.class, parserContext);
        Parboiled.createParser(ContentParser.class, parserContext);

        Parboiled.createParser(MainParser.class, parserContext);

        return parserContext;
    }
}
