package org.jtwig.highlight.addon;

import org.jtwig.parser.addon.AddonParserProvider;
import org.jtwig.parser.parboiled.node.AddonParser;

import java.util.Collection;

import static java.util.Arrays.asList;

public class HighlightAddonParserProvider implements AddonParserProvider {
    @Override
    public Class<? extends AddonParser> parser() {
        return HighlightNodeParser.class;
    }

    @Override
    public Collection<String> keywords() {
        return asList(HighlightNodeParser.HIGHLIGHT, HighlightNodeParser.ENDHIGHLIGHT);
    }
}
