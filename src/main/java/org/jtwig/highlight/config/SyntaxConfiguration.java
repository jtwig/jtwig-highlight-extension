package org.jtwig.highlight.config;

public class SyntaxConfiguration {
    private final String startCode;
    private final String endCode;
    private final String startOutput;
    private final String endOutput;
    private final String startComment;
    private final String endComment;

    public SyntaxConfiguration(String startCode, String endCode, String startOutput, String endOutput, String startComment, String endComment) {
        this.startCode = startCode;
        this.endCode = endCode;
        this.startOutput = startOutput;
        this.endOutput = endOutput;
        this.startComment = startComment;
        this.endComment = endComment;
    }

    public String getStartCode() {
        return startCode;
    }

    public String getEndCode() {
        return endCode;
    }

    public String getStartOutput() {
        return startOutput;
    }

    public String getEndOutput() {
        return endOutput;
    }

    public String getStartComment() {
        return startComment;
    }

    public String getEndComment() {
        return endComment;
    }
}
