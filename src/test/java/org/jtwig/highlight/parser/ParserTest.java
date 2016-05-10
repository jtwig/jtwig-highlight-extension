package org.jtwig.highlight.parser;

import org.junit.Test;

public class ParserTest {

    @Test
    public void parse() throws Exception {
        HighlightParser highlightParser = HighlightParser.defaultParser();

        String result = highlightParser.parse("{% if (user.loggedIn) %}\n" +
                "<div class=\"greeting\">\n" +
                "    Hello <span class=\"usename\">{{ user.greetingName }}</span>!\n" +
                "</div>\n" +
                "{% else %}\n" +
                "<div class=\"login\">\n" +
                "    Hello there! Try to <a href=\"/login\">Login</a>\n" +
                "</div>\n" +
                "{% endif %}");

        System.out.println(result);
    }
}