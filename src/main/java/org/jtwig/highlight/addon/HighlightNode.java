package org.jtwig.highlight.addon;

import org.jtwig.model.position.Position;
import org.jtwig.model.tree.Node;

public class HighlightNode extends Node {
    private final String content;

    public HighlightNode(Position position, String content) {
        super(position);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
