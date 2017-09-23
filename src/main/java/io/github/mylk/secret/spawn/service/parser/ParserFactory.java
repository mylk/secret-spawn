package io.github.mylk.secret.spawn.service.parser;

import io.github.mylk.secret.spawn.enums.Source;
import org.apache.commons.lang3.NotImplementedException;

public class ParserFactory {
    public Parser getParser(String source)
    {
        Parser parser;

        if (source.toUpperCase().equals(Source.WIKIPEDIA.name())) {
            parser = new WikipediaParser();
        } else {
            throw new NotImplementedException("Parser not implemented yet.");
        }

        return parser;
    }
}
