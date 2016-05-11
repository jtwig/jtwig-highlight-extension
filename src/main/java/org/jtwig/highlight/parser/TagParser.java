package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;

public class TagParser extends BasicParser {
    public TagParser(ParserContext parserContext) {
        super(TagParser.class, parserContext);
    }

    @Override
    protected Rule parse() {
        return FirstOf(
                CommentExpression(),
                OutputExpression(),
                TagWithExpression(),
                TagWithoutExpression(),
                ForLoopExpression(),
                ForLoopKeyExpression()
        );
    }

    Rule CommentExpression() {
        StartCommentParser startCodeParser = getParserContext().parsers().get(StartCommentParser.class);
        EndCommentParser endCodeParser = getParserContext().parsers().get(EndCommentParser.class);

        return Sequence(
                startCodeParser.parse(),
                ZeroOrMore(
                        Sequence(
                                TestNot(endCodeParser.parse()),
                                ANY
                        )
                ),
                push(match()),
                endCodeParser.parse(),

                push(mergeSince(2))
        );
    }

    Rule OutputExpression() {
        StartOutputParser startCodeParser = getParserContext().parsers().get(StartOutputParser.class);
        EndOutputParser endCodeParser = getParserContext().parsers().get(EndOutputParser.class);
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        ExpressionParser expressionParser = getParserContext().parsers().get(ExpressionParser.class);

        return Sequence(
                startCodeParser.parse(),
                spacingParser.parse(),
                expressionParser.parse(),
                spacingParser.parse(),
                endCodeParser.parse(),

                push(mergeSince(4))
        );
    }

    Rule TagWithoutExpression() {
        StartCodeParser startCodeParser = getParserContext().parsers().get(StartCodeParser.class);
        EndCodeParser endCodeParser = getParserContext().parsers().get(EndCodeParser.class);
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        TagKeywordParser tagKeywordParser = getParserContext().parsers().get(TagKeywordParser.class);

        return Sequence(
                startCodeParser.parse(),
                spacingParser.parse(),
                tagKeywordParser.parse(),
                spacingParser.parse(),
                endCodeParser.parse(),

                push(mergeSince(4))
        );
    }

    Rule TagWithExpression() {
        StartCodeParser startCodeParser = getParserContext().parsers().get(StartCodeParser.class);
        EndCodeParser endCodeParser = getParserContext().parsers().get(EndCodeParser.class);
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        TagKeywordParser tagKeywordParser = getParserContext().parsers().get(TagKeywordParser.class);
        ExpressionParser expressionParser = getParserContext().parsers().get(ExpressionParser.class);

        return Sequence(
                startCodeParser.parse(),
                spacingParser.parse(),
                tagKeywordParser.parse(),
                spacingParser.parse(),
                expressionParser.parse(),
                spacingParser.parse(),
                endCodeParser.parse(),

                push(mergeSince(6))
        );
    }

    Rule ForLoopExpression () {
        StartCodeParser startCodeParser = getParserContext().parsers().get(StartCodeParser.class);
        EndCodeParser endCodeParser = getParserContext().parsers().get(EndCodeParser.class);
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        TagKeywordParser tagKeywordParser = getParserContext().parsers().get(TagKeywordParser.class);
        ExpressionParser expressionParser = getParserContext().parsers().get(ExpressionParser.class);

        return Sequence(
                startCodeParser.parse(),
                spacingParser.parse(),
                tagKeywordParser.parse(),
                spacingParser.mandatory(),
                expressionParser.Identifier(),
                spacingParser.mandatory(),
                String("in"), push(getParserContext().formatter().tagKeyword(match())),
                spacingParser.mandatory(),
                expressionParser.parse(),
                spacingParser.parse(),
                endCodeParser.parse(),

                push(mergeSince(10))
        );
    }
    Rule ForLoopKeyExpression () {
        StartCodeParser startCodeParser = getParserContext().parsers().get(StartCodeParser.class);
        EndCodeParser endCodeParser = getParserContext().parsers().get(EndCodeParser.class);
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        TagKeywordParser tagKeywordParser = getParserContext().parsers().get(TagKeywordParser.class);
        ExpressionParser expressionParser = getParserContext().parsers().get(ExpressionParser.class);

        return Sequence(
                startCodeParser.parse(),
                spacingParser.parse(),
                tagKeywordParser.parse(),
                spacingParser.mandatory(),
                expressionParser.Identifier(),
                spacingParser.parse(),
                String(","), push(match()),
                spacingParser.parse(),
                expressionParser.Identifier(),
                spacingParser.mandatory(),
                String("in"), push(getParserContext().formatter().tagKeyword(match())),
                spacingParser.mandatory(),
                expressionParser.parse(),
                spacingParser.parse(),
                endCodeParser.parse(),

                push(mergeSince(14))
        );
    }
}
