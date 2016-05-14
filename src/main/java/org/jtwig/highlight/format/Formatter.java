package org.jtwig.highlight.format;

public interface Formatter {
    String startCode (String original);
    String endCode (String original);

    String startComment (String original);
    String endComment (String original);

    String startOutput (String original);
    String endOutput (String original);

    String tagKeyword(String match);

    String variable(String match);

    String startParentsis();
    String endParentsis();

    String space(String match);

    String operator(String match);

    String stringLiteral(String match);
    String numberLiteral(String match);
    String booleanLiteral(String match);

    String content(String match);

    String startList();
    String endList();

    String startMap();
    String endMap();
}
