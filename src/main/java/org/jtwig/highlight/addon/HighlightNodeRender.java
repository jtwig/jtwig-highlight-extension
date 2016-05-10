package org.jtwig.highlight.addon;

import org.jtwig.escape.NoneEscapeEngine;
import org.jtwig.highlight.HighlightExtension;
import org.jtwig.highlight.config.HighlightConfiguration;
import org.jtwig.highlight.parser.HighlightParser;
import org.jtwig.highlight.parser.context.ParserContextFactory;
import org.jtwig.render.RenderRequest;
import org.jtwig.render.node.renderer.NodeRender;
import org.jtwig.renderable.Renderable;
import org.jtwig.renderable.impl.StringRenderable;

public class HighlightNodeRender implements NodeRender<HighlightNode> {
    @Override
    public Renderable render(RenderRequest renderRequest, HighlightNode node) {
        HighlightConfiguration configuration = HighlightExtension.configuration(renderRequest.getEnvironment());
        HighlightParser highlightParser = new HighlightParser(new ParserContextFactory().create(configuration));
        return new StringRenderable(highlightParser.parse(node.getContent()), NoneEscapeEngine.instance());
    }
}
