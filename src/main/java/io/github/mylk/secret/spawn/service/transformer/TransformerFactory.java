package io.github.mylk.secret.spawn.service.transformer;

import io.github.mylk.secret.spawn.enums.Format;
import org.apache.commons.lang3.NotImplementedException;

public class TransformerFactory {
    public Transformer getTransformer(String format)
    {
        Transformer transformer;

        if (format.toUpperCase().equals(Format.SIMPLE.name())) {
            transformer = new SimpleTransformer();
        } else if (format.toUpperCase().equals(Format.HACKISH.name())) {
            transformer = new HackishTransformer();
        } else {
            throw new NotImplementedException("Format not implemented yet.");
        }

        return transformer;
    }
}
