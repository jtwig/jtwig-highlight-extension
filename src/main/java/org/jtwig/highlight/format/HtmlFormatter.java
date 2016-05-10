package org.jtwig.highlight.format;

import org.apache.commons.lang3.StringEscapeUtils;

public class HtmlFormatter implements Formatter {
    private final String classPrefix;

    public HtmlFormatter(String classPrefix) {
        this.classPrefix = classPrefix;
    }

    public HtmlFormatter() {
        this("jtwig");
    }

    @Override
    public String startCode(String original) {
        return String.format("<span class=\"%s-start-code\">%s</span>", classPrefix, original);
    }

    @Override
    public String endCode(String original) {
        return String.format("<span class=\"%s-end-code\">%s</span>", classPrefix, original);
    }

    @Override
    public String startComment(String original) {
        return String.format("<span class=\"%s-start-comment\">%s</span><span class=\"%s-comment\">", classPrefix, original, classPrefix);
    }

    @Override
    public String endComment(String original) {
        return String.format("</span><span class=\"%s-end-comment\">%s</span>", classPrefix, original);
    }

    @Override
    public String startOutput(String original) {
        return String.format("<span class=\"%s-start-output\">%s</span>", classPrefix, original);
    }

    @Override
    public String endOutput(String original) {
        return String.format("<span class=\"%s-end-output\">%s</span>", classPrefix, original);
    }

    @Override
    public String tagKeyword(String original) {
        return String.format("<span class=\"%s-keyword\">%s</span>", classPrefix, original);
    }

    @Override
    public String variable(String original) {
        return String.format("<span class=\"%s-variable\">%s</span>", classPrefix, original);
    }

    @Override
    public String startParentsis() {
        return "(";
    }

    @Override
    public String endParentsis() {
        return ")";
    }

    @Override
    public String space(String match) {
        return match.replace(" ", "&nbsp;")
                .replace("\n", "<br />")
                .replace("\t", "&nbsp;&nbsp;&nbsp");
    }

    @Override
    public String operator(String match) {
        return String.format("<span class=\"%s-operator\">%s</span>", classPrefix, match);
    }

    @Override
    public String stringLiteral(String match) {
        return String.format("<span class=\"%s-literal-string\">%s</span>", classPrefix, match);
    }

    @Override
    public String numberLiteral(String match) {
        return String.format("<span class=\"%s-literal-number\">%s</span>", classPrefix, match);
    }

    @Override
    public String booleanLiteral(String match) {
        return String.format("<span class=\"%s-literal-boolean\">%s</span>", classPrefix, match);
    }

    @Override
    public String content(String match) {
        return String.format("<span class=\"%s-content\">%s</span>", classPrefix, space(StringEscapeUtils.escapeHtml4(match)));
    }
}
