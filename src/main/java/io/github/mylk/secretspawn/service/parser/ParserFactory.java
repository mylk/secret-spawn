package io.github.mylk.secretspawn.service.parser;

import io.github.mylk.secretspawn.enums.Source;
import io.github.mylk.secretspawn.model.Settings;

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
