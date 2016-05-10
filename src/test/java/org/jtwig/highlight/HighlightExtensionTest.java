package org.jtwig.highlight;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.junit.Test;

public class HighlightExtensionTest {
    @Test
    public void test() throws Exception {

        String result = JtwigTemplate.inlineTemplate("{% highlight %}{% if (true) %}\n<html />\n{% endif %}{% endhighlight %}",
                EnvironmentConfigurationBuilder.configuration()
                        .extensions().add(HighlightExtension.defaultHighlight()).and()
                        .build()
                )
                .render(JtwigModel.newModel());

        System.out.println(result);
    }
}