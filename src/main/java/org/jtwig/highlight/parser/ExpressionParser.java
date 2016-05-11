package org.jtwig.highlight.parser;

import org.jtwig.highlight.format.Formatter;
import org.jtwig.highlight.parser.base.BasicParser;
import org.jtwig.highlight.parser.context.ParserContext;
import org.jtwig.highlight.parser.operators.Operator;
import org.parboiled.Rule;

import java.util.List;

public class ExpressionParser extends BasicParser {
    public ExpressionParser(ParserContext context) {
        super(ExpressionParser.class, context);
    }

    @Override
    protected Rule parse() {
        return FirstOf(
                Ternary(),
                Binary(),
                Unary(),
                Primary()
        );
    }

    Rule Ternary() {
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        return Sequence(
                Primary(),
                spacingParser.parse(),
                String("?"), push(getParserContext().formatter().operator(match())),
                spacingParser.parse(),
                parse(),
                spacingParser.parse(),
                String(":"), push(getParserContext().formatter().operator(match())),
                spacingParser.parse(),
                parse(),
                push(mergeSince(8))
        );
    }

    Rule Binary() {
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        return Sequence(
                Primary(),
                spacingParser.parse(),
                AnyOperator(getParserContext().binaryOperators()),
                spacingParser.parse(),
                parse(),
                push(mergeSince(4))
        );
    }

    Rule Unary() {
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);

        return Sequence(
                AnyOperator(getParserContext().unaryOperators()),
                spacingParser.parse(),
                Primary(),
                push(mergeSince(2))
        );
    }

    Rule AnyOperator(List<Operator> operators) {
        Rule[] rules = new Rule[operators.size()];
        for (int i = 0; i < operators.size(); i++) {
            rules[i] = Operator(operators.get(i));
        }
        return Sequence(
                FirstOf(rules),
                push(getParserContext().formatter().operator(match()))
        );
    }

    Rule Operator(Operator operator) {
        if (operator.isIdentifierPattern()) {
            return Sequence(
                    String(operator.getSymbol()),
                    Test(AnyOf(" \n\t"))
            );
        } else {
            return String(operator.getSymbol());
        }
    }

    Rule Primary() {
        return FirstOf(
                Literals(),
                Identifier(),
                ParentsisExpression()
        );
    }

    Rule Literals() {
        return FirstOf(
                StringExpression(),
                NumberExpression(),
                BooleanExpression()
        );
    }

    Rule BooleanExpression() {
        return Sequence(
                FirstOf(
                        "true",
                        "false"
                ),
                push(getParserContext().formatter().booleanLiteral(match()))
        );
    }

    Rule NumberExpression() {
        return Sequence(
                Sequence(
                        Optional(
                                ZeroOrMore(CharRange('0', '9')),
                                String(".")
                        ),
                        OneOrMore(CharRange('0', '9'))
                ),
                push(getParserContext().formatter().numberLiteral(match()))
        );
    }

    Rule StringExpression() {
        return Sequence(
                FirstOf(
                        StringWith("'"),
                        StringWith("\"")
                ),
                push(getParserContext().formatter().stringLiteral(match()))
        );
    }

    Rule StringWith(String symbol) {
        return Sequence(
                String(symbol),
                ZeroOrMore(
                        Sequence(
                                TestNot(String(symbol)),
                                ANY
                        )
                ),
                String(symbol)
        );
    }

    Rule ParentsisExpression() {
        SpacingParser spacingParser = getParserContext().parsers().get(SpacingParser.class);
        Formatter formatter = getParserContext().formatter();
        return Sequence(
                String("("),
                push(formatter.startParentsis()),
                spacingParser.parse(),
                parse(),
                String(")"),
                push(formatter.endParentsis()),
                push(mergeSince(3))
        );
    }

    public Rule Identifier() {
        IdentifierParser identifierParser = getParserContext().parsers().get(IdentifierParser.class);
        return Sequence(
                identifierParser.parse(),
                push(getParserContext().formatter().variable(match()))
        );
    }
}
