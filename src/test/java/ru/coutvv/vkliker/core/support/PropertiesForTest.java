package ru.coutvv.vkliker.core.support;

import org.cactoos.io.InputStreamOf;
import org.cactoos.io.ResourceOf;
import org.cactoos.scalar.NumberOf;
import org.cactoos.scalar.PropertiesOf;
import org.cactoos.text.TextOf;

import java.io.IOException;
import java.util.Properties;

/**
 * @author coutvv    23.04.2018
 */
public class PropertiesForTest {

    private Properties props;

    public PropertiesForTest() throws IOException {
        this("app.properties" );
    }

    public PropertiesForTest(String resourceName) throws IOException {
        this( new PropertiesOf(
                        new TextOf(
                                new InputStreamOf(
                                        new ResourceOf(resourceName)
                                )
                        )
              ).value()
        );
    }

    public PropertiesForTest(Properties props) {
        this.props = props;
    }

    public String value(String key) {
        return props.getProperty(key);
    }

    public int intValue(String key) {
        return new NumberOf(props.getProperty(key)).intValue();
    }
}
