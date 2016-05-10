package org.jtwig.highlight;

import org.jtwig.environment.Environment;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.extension.Extension;
import org.jtwig.highlight.addon.HighlightAddonParserProvider;
import org.jtwig.highlight.addon.HighlightNode;
import org.jtwig.highlight.addon.HighlightNodeRender;
import org.jtwig.highlight.config.DefaultHighlightConfiguration;
import org.jtwig.highlight.config.HighlightConfiguration;

public class HighlightExtension implements Extension {
    private static final String HIGHLIGHT_CONFIGURATION = "highlightConfiguration";

    public static HighlightConfiguration configuration(Environment environment) {
        return environment.parameter(HIGHLIGHT_CONFIGURATION);
    }

    public static HighlightExtension defaultHighlight () {
        return new HighlightExtension(new DefaultHighlightConfiguration());
    }

    private final HighlightConfiguration configuration;

    public HighlightExtension(HighlightConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure(EnvironmentConfigurationBuilder configurationBuilder) {
        configurationBuilder.parameters().add(HIGHLIGHT_CONFIGURATION, configuration);
        configurationBuilder.parser().addonParserProviders().add(new HighlightAddonParserProvider());
        configurationBuilder.render().nodeRenders().add(HighlightNode.class, new HighlightNodeRender());
    }
}
