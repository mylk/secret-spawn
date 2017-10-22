package io.github.mylk.secret.spawn.service.parser;

import io.github.mylk.secret.spawn.enums.Source;
import io.github.mylk.secret.spawn.model.Settings;
import org.apache.commons.lang3.NotImplementedException;

public class ParserFactory {
    private Settings settings;

    public ParserFactory(Settings settings) {
        this.settings = settings;
    }

    public Parser getParser() {
        Parser parser;

        if (settings.getSource().toUpperCase().equals(Source.WIKIPEDIA.name())) {
            parser = new WikipediaParser();
        } else {
            throw new NotImplementedException("Parser not implemented yet.");
        }

        return parser;
    }
}
