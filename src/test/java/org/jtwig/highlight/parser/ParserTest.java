package org.jtwig.highlight.parser;

import org.junit.Test;

public class ParserTest {

    @Test
    public void parse() throws Exception {
        HighlightParser highlightParser = HighlightParser.defaultParser();

        String result = highlightParser.parse("{% for var , test in test %}blah{% endfor %}");

        System.out.println(result);
    }
}