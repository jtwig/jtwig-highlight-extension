package org.jtwig.highlight.parser;

import org.junit.Test;

public class ParserTest {

    @Test
    public void parseFor() throws Exception {
        HighlightParser highlightParser = HighlightParser.defaultParser();

        String result = highlightParser.parse("{% for var , test in test %}blah{% endfor %}");

        System.out.println(result);
    }

    @Test
    public void parseSet() throws Exception {
        HighlightParser highlightParser = HighlightParser.defaultParser();

        String result = highlightParser.parse("{% set a = 1 + 2 %}");

        System.out.println(result);
    }

    @Test
    public void parseMethodCalls() throws Exception {
        HighlightParser highlightParser = HighlightParser.defaultParser();

        String result = highlightParser.parse("{% do test.method() %}");

        System.out.println(result);
    }

    @Test
    public void parseMethodCallsWithArguments() throws Exception {
        HighlightParser highlightParser = HighlightParser.tracingParser();

        String result = highlightParser.parse("{% do test.method('hello') %}");

        System.out.println(result);
    }

    @Test
    public void parseMethodCallsWithMultipleArguments() throws Exception {
        HighlightParser highlightParser = HighlightParser.tracingParser();

        String result = highlightParser.parse("{% do test.method('hello', 1) %}");

        System.out.println(result);
    }
}