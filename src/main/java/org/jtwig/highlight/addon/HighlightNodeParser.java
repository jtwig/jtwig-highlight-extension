package org.jtwig.highlight.addon;

import org.jtwig.parser.parboiled.ParserContext;
import org.jtwig.parser.parboiled.base.*;
import org.jtwig.parser.parboiled.node.AddonParser;
import org.parboiled.Rule;

import static org.parboiled.Parboiled.createParser;

public class HighlightNodeParser extends AddonParser {
    public static final String HIGHLIGHT = "highlight";
    public static final String ENDHIGHLIGHT = "endhighlight";

    public HighlightNodeParser(ParserContext context) {
        super(HighlightNodeParser.class, context);
        createParser(HighlightNodeContentParser.class, context);
    }

    @Override
    public Rule NodeRule() {
        PositionTrackerParser positionTrackerParser = parserContext().parser(PositionTrackerParser.class);
        LimitsParser limitsParser = parserContext().parser(LimitsParser.class);
        SpacingParser spacingParser = parserContext().parser(SpacingParser.class);
        LexicParser lexicParser = parserContext().parser(LexicParser.class);
        HighlightNodeParser.HighlightNodeContentParser highlightContent = parserContext().parser(HighlightNodeParser.HighlightNodeContentParser.class);
        return Sequence(
                positionTrackerParser.PushPosition(),

                // start
                Sequence(
                        limitsParser.startCode(),
                        spacingParser.Spacing(),
                        lexicParser.Keyword(HIGHLIGHT),
                        spacingParser.Spacing(),
                        Mandatory(limitsParser.endCode(), "Missing code island end")
                ),

                // content
                highlightContent.Content(endHighlight(limitsParser, spacingParser, lexicParser)),

                endHighlight(limitsParser, spacingParser, lexicParser),

                push(new HighlightNode(positionTrackerParser.pop(1), highlightContent.pop()))
        );
    }

    Rule endHighlight(LimitsParser limitsParser, SpacingParser spacingParser, LexicParser lexicParser) {
        return Sequence(
                limitsParser.startCode(),
                spacingParser.Spacing(),
                lexicParser.Keyword(ENDHIGHLIGHT),
                spacingParser.Spacing(),
                limitsParser.endCode()
        );
    }

    public static class HighlightNodeContentParser extends BasicParser<String> {
        public HighlightNodeContentParser(ParserContext context) {
            super(HighlightNodeContentParser.class, context);
        }

        public Rule Content(Rule end) {
            return Sequence(
                    ZeroOrMore(
                            TestNot(end),
                            ANY
                    ),
                    push(match())
            );
        }
    }
}
