package io.github.mylk.secret.spawn.service.parser;

import io.github.mylk.secret.spawn.enums.Source;
import io.github.mylk.secret.spawn.model.Settings;

public class ParserFactory {
    private Settings settings;

    public ParserFactory(Settings settings) {
        this.settings = settings;
    }

    public Parser getParser() {
        Parser parser = null;

        if (settings.getSource().toUpperCase().equals(Source.WIKIPEDIA.name())) {
            parser = new WikipediaParser();
        }

        return parser;
    }
}
