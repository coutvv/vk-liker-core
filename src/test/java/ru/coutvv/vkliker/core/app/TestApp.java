package ru.coutvv.vkliker.core.app;

import org.cactoos.io.InputStreamOf;
import org.cactoos.io.ResourceOf;
import org.cactoos.scalar.PropertiesOf;
import org.cactoos.text.TextOf;

import java.io.IOException;
import java.util.Properties;

public abstract class TestApp {

    protected Properties testProps() throws IOException {
        return new PropertiesOf(
                new TextOf(
                        new InputStreamOf(
                                new ResourceOf("app.properties")
                        )
                )
        ).value();
    }
}
