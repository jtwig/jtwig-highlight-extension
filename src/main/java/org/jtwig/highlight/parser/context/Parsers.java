package org.jtwig.highlight.parser.context;

import org.jtwig.highlight.parser.base.BasicParser;

import java.util.HashMap;
import java.util.Map;

public class Parsers {
    private final Map<Class<? extends BasicParser>, BasicParser> parsers;

    public Parsers() {
        this.parsers = new HashMap<Class<? extends BasicParser>, BasicParser>();
    }

    public <T extends BasicParser> void register (Class type, T parser) {
        parsers.put(type, parser);
    }

    public <T extends BasicParser> T get (Class<T> type) {
        BasicParser basicParser = parsers.get(type);
        if (basicParser == null) throw new IllegalArgumentException(String.format("No parser instance found for %s", type.getName()));
        return (T) basicParser;
    }
}
