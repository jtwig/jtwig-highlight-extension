package org.jtwig.highlight.parser.factory;

import org.jtwig.highlight.parser.context.ParserContext;
import org.parboiled.parserunners.ParseRunner;

public interface ParserRunnerFactory {
    ParseRunner<String> create (ParserContext parserContext);
}
