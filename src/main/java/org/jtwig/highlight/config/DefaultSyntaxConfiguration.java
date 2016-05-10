package org.jtwig.highlight.config;

public class DefaultSyntaxConfiguration extends SyntaxConfiguration {
    public DefaultSyntaxConfiguration() {
        super("{%", "%}", "{{", "}}", "{#", "#}");
    }
}
