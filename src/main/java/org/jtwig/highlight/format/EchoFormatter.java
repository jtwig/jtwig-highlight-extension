package org.jtwig.highlight.format;

public class EchoFormatter implements Formatter {
    @Override
    public String startCode(String original) {
        return original;
    }

    @Override
    public String endCode(String original) {
        return original;
    }

    @Override
    public String startComment(String original) {
        return original;
    }

    @Override
    public String endComment(String original) {
        return original;
    }

    @Override
    public String startOutput(String original) {
        return original;
    }

    @Override
    public String endOutput(String original) {
        return original;
    }

    @Override
    public String tagKeyword(String match) {
        return match;
    }

    @Override
    public String variable(String match) {
        return match;
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
        return match;
    }

    @Override
    public String operator(String match) {
        return match;
    }

    @Override
    public String stringLiteral(String match) {
        return match;
    }

    @Override
    public String numberLiteral(String match) {
        return match;
    }

    @Override
    public String booleanLiteral(String match) {
        return match;
    }

    @Override
    public String content(String match) {
        return match;
    }

    @Override
    public String startList() {
        return "[";
    }

    @Override
    public String endList() {
        return "]";
    }

    @Override
    public String startMap() {
        return "{";
    }

    @Override
    public String endMap() {
        return "}";
    }
}
