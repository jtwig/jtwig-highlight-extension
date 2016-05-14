package org.jtwig.highlight.parser;

import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.Rule;
import org.parboiled.annotations.Label;

public class TagParser extends BasicParser {
    public TagParser(ParserContext parserContext) {
        super(TagParser.class, parserContext);
    }

    @Override
    @Label("Tag")
    protected Rule parse() {
        return FirstOf(
                IncludeExpression(),
                CommentExpression(),
                OutputExpression(),
                TagWithExpression(),
                TagWithoutExpression(),
                ForLoopExpression(),
                ForLoopKeyExpression(),
                setExpression()
        );
    }

    @Label("Comment")
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

    @Label("Output")
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

    @Label("Tag without Expression")
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

    @Label("Tag with Expression")
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

    @Label("Set")
    Rule setExpression () {
        StartCodeParser startCodeParser = getParserContext().parsers().get(StartCodeParser.class);
        EndCodeParser endCodeParser = getParserContext().parsers().get(EndCodeParser.class);
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        ExpressionParser expressionParser = getParserContext().parsers().get(ExpressionParser.class);

        return Sequence(
                startCodeParser.parse(),
                spacingParser.parse(),
                String("set"), push(getParserContext().formatter().tagKeyword("set")),
                spacingParser.parse(),
                expressionParser.Identifier(),
                spacingParser.parse(),
                String("="), push(getParserContext().formatter().operator("=")),
                spacingParser.parse(),
                expressionParser.parse(),
                spacingParser.parse(),
                endCodeParser.parse(),

                push(mergeSince(10))
        );
    }

    @Label("Include")
    Rule IncludeExpression () {
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
                expressionParser.parse(),
                spacingParser.parse(),
                IgnoreMissing(),
                spacingParser.parse(),
                With(),
                spacingParser.parse(),
                Only(),
                spacingParser.parse(),
                endCodeParser.parse(),

                push(mergeSince(12))
        );
    }

    Rule Only() {
        TagKeywordParser tagKeywordParser = getParserContext().parsers().get(TagKeywordParser.class);
        return FirstOf(
                tagKeywordParser.parse(),
                push("")
        );
    }

    Rule With() {
        TagKeywordParser tagKeywordParser = getParserContext().parsers().get(TagKeywordParser.class);
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        ExpressionParser expressionParser = getParserContext().parsers().get(ExpressionParser.class);
        return FirstOf(
                Sequence(
                        tagKeywordParser.parse(),
                        spacingParser.parse(),
                        expressionParser.parse(),

                        push(mergeSince(2))
                ),
                push("")
        );
    }

    Rule IgnoreMissing() {
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);

        return FirstOf(
                Sequence(
                        String("ignore"), push(getParserContext().formatter().tagKeyword(match())),
                        spacingParser.mandatory(),
                        String("missing"), push(getParserContext().formatter().tagKeyword(match())),

                        push(mergeSince(2))
                ),
                push("")
        );
    }

    @Label("For loop list")
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

    @Label("For loop map")
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
